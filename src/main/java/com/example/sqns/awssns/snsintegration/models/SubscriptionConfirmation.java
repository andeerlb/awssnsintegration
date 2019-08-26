package com.example.sqns.awssns.snsintegration.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public final class SubscriptionConfirmation implements Serializable
{
	private static final long serialVersionUID = -3977473144711234898L;

	@JsonProperty("Type")
	private String type;
	@JsonProperty("MessageId")
	private String messageId;
	@JsonProperty("Token")
	private String token;
	@JsonProperty("TopicArn")
	private String topicArn;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("SubscribeURL")
	private String subscribeURL;
	@JsonProperty("Timestamp")
	private Date simestamp;
	@JsonProperty("SignatureVersion")
	private String signatureVersion;
	@JsonProperty("Signature")
	private String signature;
	@JsonProperty("SigningCertURL")
	private String signingCertURL;

	private SubscriptionConfirmation()
	{
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getTopicArn()
	{
		return topicArn;
	}

	public void setTopicArn(String topicArn)
	{
		this.topicArn = topicArn;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getSubscribeURL()
	{
		return subscribeURL;
	}

	public void setSubscribeURL(String subscribeURL)
	{
		this.subscribeURL = subscribeURL;
	}

	public Date getSimestamp()
	{
		return simestamp;
	}

	public void setSimestamp(Date simestamp)
	{
		this.simestamp = simestamp;
	}

	public String getSignatureVersion()
	{
		return signatureVersion;
	}

	public void setSignatureVersion(String signatureVersion)
	{
		this.signatureVersion = signatureVersion;
	}

	public String getSignature()
	{
		return signature;
	}

	public void setSignature(String signature)
	{
		this.signature = signature;
	}

	public String getSigningCertURL()
	{
		return signingCertURL;
	}

	public void setSigningCertURL(String signingCertURL)
	{
		this.signingCertURL = signingCertURL;
	}

	@Override public String toString()
	{
		return new StringBuilder("SubscriptionConfirmation{")
		.append("messageId='").append(messageId).append('\'')
		.append('}').toString();
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SubscriptionConfirmation that = (SubscriptionConfirmation) o;
		return Objects.equals(messageId, that.messageId);
	}

	@Override public int hashCode()
	{
		return Objects.hash(messageId);
	}
}
