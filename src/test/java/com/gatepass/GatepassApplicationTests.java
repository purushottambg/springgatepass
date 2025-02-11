package com.gatepass;

import com.gatepass.dtos.PassDTO;
import com.gatepass.service.PassesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

class GatepassApplicationTests {
	@Autowired
	PassesService passesService;
	static Logger logger = LoggerFactory.getLogger(GatepassApplicationTests.class);

	@BeforeAll
	public static void beforeEach(){
		logger.info("This is before each annotation!");
	}

	@Test
	@DisplayName("Print Greet")
	public void greetEach(){
		logger.info("Greet method");
	}
	@Test
	@DisplayName("CheckCreatedPassId")
	public void checkPassIdOfNewlyGenereatedPass(){
		logger.info("First Test using JUnit :)");
	}




}
