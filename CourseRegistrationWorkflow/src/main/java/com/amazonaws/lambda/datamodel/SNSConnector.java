package com.amazonaws.lambda.datamodel;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SNSConnector {

	private static AmazonSNSClient sns;

	public void init() {
		if (sns == null) {
			sns = (AmazonSNSClient) AmazonSNSClientBuilder.standard()
					.withCredentials(DefaultAWSCredentialsProviderChain.getInstance()).withRegion("us-west-2").build();
		}
	}

	public AmazonSNSClient getSNSClient() {
		return sns;
	}
}
