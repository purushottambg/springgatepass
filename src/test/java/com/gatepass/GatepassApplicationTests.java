package com.gatepass;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@ActiveProfiles("test")
//@TestPropertySource("classpath:application-test.properties")
class GatepassApplicationTests {
//	@Autowired
//	PassesService passesService;
//	static Logger logger = LoggerFactory.getLogger(GatepassApplicationTests.class);
//
//	@Test
//	public void addTwoNumbers(){
//		logger.info("No tests for now");
//	}

	@Test
	public void testActiveProfile() {
		System.out.println("test is an Active Profile");
	}
}
