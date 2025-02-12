package com.gatepass;


import com.gatepass.service.PassesService;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



class GatepassApplicationTests {
	@Autowired
	PassesService passesService;
	static Logger logger = LoggerFactory.getLogger(GatepassApplicationTests.class);

	@Test
	public void addTwoNumbers(){
		logger.info("No tests for now");
	}
}
