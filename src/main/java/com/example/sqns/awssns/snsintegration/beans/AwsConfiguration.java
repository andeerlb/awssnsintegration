package com.example.sqns.awssns.snsintegration.beans;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsConfiguration
{
	@Value("${aws.region}")
	private String awsRegion;

	@Value("${aws.access-key}")
	private String awsAccessKey;

	@Value("${aws.secret-key}")
	private String awsSecretKey;

	@Bean(name = "amazonSnsClient")
	public AmazonSNS amazonSNSClient() {
		return AmazonSNSClient.builder()
				.withCredentials(amazonAWSCredentials())
				.withRegion(awsRegion).build();
	}

	@Bean
	@Primary
	public AWSCredentialsProvider amazonAWSCredentials() {
		return new AWSCredentialsProvider() {
			public void refresh() {}
			public AWSCredentials getCredentials() {
				return new AWSCredentials() {
					public String getAWSSecretKey() {
						return awsSecretKey;
					}
					public String getAWSAccessKeyId() {
						return awsAccessKey;
					}
				};
			}
		};
	}
}
