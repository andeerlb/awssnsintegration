package com.example.sqns.awssns.controllers;

import com.example.sqns.awssns.services.BusinessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/businessrole")
public class BusinessRuleController
{
	@Autowired
	private BusinessRuleService service;

	@RequestMapping(path = "/send")
	public void send() throws URISyntaxException
	{
		service.send();
	}
}
