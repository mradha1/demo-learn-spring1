package com.example.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bo.TestBO;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TestController {
	
	private final TestBO testBo;
	
	public TestController(TestBO testBo) {
		this.testBo = testBo;
	}
	
	@GetMapping("/test")
	public String testMethod() {
		testBo.invokeProcessing();
		return "test";
	}

}