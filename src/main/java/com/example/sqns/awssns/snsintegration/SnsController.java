package com.example.sqns.awssns.snsintegration;

import com.example.sqns.awssns.services.BusinessRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/sns")
public class SnsController
{
	@Autowired
	private BusinessRuleService businessRoleService;

	@RequestMapping(path = "/receive", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8", consumes = "text/plain;charset=UTF-8")
	public void receiveSnsMessage(@RequestBody(required = true) String body, @RequestHeader(name = "x-amz-sns-message-type", required = true) String header)
	{
		businessRoleService.receive(body, header);
	}
}
