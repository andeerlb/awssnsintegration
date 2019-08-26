package com.example.sqns.awssns.snsintegration.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public final class Notification <T>
{
	@JsonProperty("Message")
	private Collection<T> message;
	@JsonProperty("MessageId")
	private String messageId;
	@JsonProperty("Signature")
	private String signature;
	@JsonProperty("SignatureVersion")
	private String signatureVersion;
	@JsonProperty("SigningCertURL")
	private String signingCertURL;
	@JsonProperty("Subject")
	private String subject;
	@JsonProperty("Timestamp")
	private Date timestamp;
	@JsonProperty("TopicArn")
	private String topicArn;
	@JsonProperty("Type")
	private String type;
	@JsonProperty("UnsubscribeURL")
	private String unsubscribeURL;

	public Notification()
	{
	}

	public Collection<T> getMessage()
	{
		return message;
	}

	public void setMessage(Collection<T> message)
	{
		this.message = message;
	}

	public String getMessageId()
	{
		return messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public String getSignature()
	{
		return signature;
	}

	public void setSignature(String signature)
	{
		this.signature = signature;
	}

	public String getSignatureVersion()
	{
		return signatureVersion;
	}

	public void setSignatureVersion(String signatureVersion)
	{
		this.signatureVersion = signatureVersion;
	}

	public String getSigningCertURL()
	{
		return signingCertURL;
	}

	public void setSigningCertURL(String signingCertURL)
	{
		this.signingCertURL = signingCertURL;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

	public String getTopicArn()
	{
		return topicArn;
	}

	public void setTopicArn(String topicArn)
	{
		this.topicArn = topicArn;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUnsubscribeURL()
	{
		return unsubscribeURL;
	}

	public void setUnsubscribeURL(String unsubscribeURL)
	{
		this.unsubscribeURL = unsubscribeURL;
	}

	@Override public String toString()
	{
		return new StringBuilder("Notification{")
		.append("messageId='")
		.append(messageId).append('\'')
		.append('}').toString();
	}

	@Override public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Notification that = (Notification) o;
		return Objects.equals(messageId, that.messageId);
	}

	@Override public int hashCode()
	{
		return Objects.hash(messageId);
	}
}
