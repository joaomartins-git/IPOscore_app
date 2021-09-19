package com.tese.webplatform.iposcore.controllers;

import com.tese.webplatform.iposcore.repositories.HelloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;

public class HelloController {
 
	@Autowired
	@Qualifier("helloServicePython")
	private HelloService service;
 
	@RequestMapping("/hello")
	public String index() {
		return service.getHello();
	}
 
}