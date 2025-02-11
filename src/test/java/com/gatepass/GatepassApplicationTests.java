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

	@BeforeEach
	public void beforeEach(){
		logger.info("This is before each annotation!");
	}
	@AfterEach
	public void afterEach(){
		logger.info("Executing AfterEach");
	}

	@BeforeAll
	public static void beforeAll(){
		logger.info("This should be executing before all the methods");
	}
	@AfterAll
	public static void afterAll(){
		logger.info("After each should be executing");
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
