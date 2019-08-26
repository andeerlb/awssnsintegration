package com.example.sqns.awssns.services;

import com.example.sqns.awssns.ModelTest;
import com.example.sqns.awssns.snsintegration.services.SnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

@Service
public class BusinessRuleService
{
	@Autowired
	private SnsService snsService;

	@Async
	public void send() throws URISyntaxException
	{
		snsService.publish("message");
	}

	public void receive(String body, String header) {
		final boolean verifyConfirm;
		try
		{
			verifyConfirm = snsService.checkAndConfirmSubscription(body, header);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		if(verifyConfirm){
			return;
		}
		final Collection<ModelTest> modelTests = snsService.convertMessageRawSnsToPojo(body, ModelTest.class);
		System.out.println(modelTests);
	}
}
