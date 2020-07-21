package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RefreshScope

public class HelloController {
	
	private static int i = 5;
	
	@Value("${NAME:default}")
	String name;
	
	@GetMapping("/")
	public String test() {
		return "You are doing great, " + name;
	}
	
	@RequestMapping(value = "/create")
	@HystrixCommand(fallbackMethod = "sendErrorResponse")
	public String testFile() {
		
		if(i > 0) i--;
		
		System.out.println(10/i);
		
		return (String.valueOf(10/i));
		
	}

	public String sendErrorResponse() {
		return "In fallback method";
}
	
}
