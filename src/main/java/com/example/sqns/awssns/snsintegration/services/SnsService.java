package com.example.sqns.awssns.snsintegration.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.example.sqns.awssns.snsintegration.models.Notification;
import com.example.sqns.awssns.snsintegration.models.SubscriptionConfirmation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

@Service
public class SnsService
{
	private static final Logger LOG = LoggerFactory.getLogger(SnsService.class);

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private AmazonSNS snsClient;

	public String publish(String message) {
		final PublishRequest publishRequest = new PublishRequest("arn:topic", message);
		final PublishResult publish = snsClient.publish(publishRequest);
		return publish.getMessageId();
	}

	public <T> Collection<T> convertMessageRawSnsToPojo(String json, Class<T> c) {
		CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(Collection.class, c);
		try
		{
			return objectMapper.readValue(json, typeReference);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	public <T> Notification<T> convertMessageSnsToPojo(String json, Class<T> c) {
		try {
			final ObjectNode nodes = objectMapper.readValue(json, ObjectNode.class);
			final Notification<T> notification = new Notification<>();

			if(nodes.has("Message")){
				CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(Collection.class, c);
				final Collection<T> message = objectMapper.readValue(nodes.get("Message").asText(), typeReference);
				notification.setMessage(message);
			}

			if(nodes.has("MessageId")) {
				notification.setMessageId(nodes.get("MessageId").asText());
			}

			if(nodes.has("Subject")){
				notification.setSubject(nodes.get("Subject").asText());
			}

			if(nodes.has("Type")) {
				notification.setType(nodes.get("Type").asText());
			}
			return notification;
		} catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Param topicName - Name of your topic on amazon sns
	 * @return topic ARN
	 */
	public String createTopicSNS(String topicName) {
		final CreateTopicRequest topicRequest = new CreateTopicRequest(topicName);
		final CreateTopicResult topicResult = snsClient.createTopic(topicRequest);

		LOG.debug("Topic ARN: {}",topicResult.getTopicArn());
		return topicResult.getTopicArn();
	}

	/**
	 *
	 * @param body = Body receive from amazon sns
	 * @param header = Header received from amazons sns
	 * @return TRUE = Message to validate signature and successfully validated, FALSE = Message is not for signature
	 * @throws IOException
	 */
	public boolean checkAndConfirmSubscription(String body, String header) throws IOException
	{
		if(!"SubscriptionConfirmation".equals(header)) {
			return Boolean.FALSE;
		}

		confirmSubscription(objectMapper.readValue(body, SubscriptionConfirmation.class));
		return Boolean.TRUE;
	}

	/**
	 *
	 * @param confirmation - Model: SubscriptionConfirmation.class
	 * @return ARN Subscription
	 */
	private String confirmSubscription(SubscriptionConfirmation confirmation) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(confirmation.getSubscribeURL()).openStream());

			final NodeList nodeList = doc.getElementsByTagName("ConfirmSubscriptionResult");

			String subscriptionArn = null;
			if(nodeList != null) {
				for (int temp = 0; temp < nodeList.getLength(); temp++) {
					final Node node = nodeList.item(temp);

					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element) node;
						subscriptionArn = element.getElementsByTagName("SubscriptionArn").item(0).getTextContent();
					}
				}
			}

			if(subscriptionArn == null)
				throw new RuntimeException(String.format("Not possible subscribe to SNS ARN: %s", confirmation.getTopicArn()));

			LOG.debug("Subscription ARN: {}", subscriptionArn);
			return subscriptionArn;
		} catch (Exception e) {
			LOG.error("{}",e.getMessage(), e);
			throw new RuntimeException(String.format("Not possible subscribe to SNS ARN: %s", confirmation.getTopicArn()), e);
		}
	}
}
