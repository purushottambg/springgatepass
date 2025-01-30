package com.gatepass;

import com.gatepass.models.MembershipEntity;
import com.gatepass.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatepassApplicationTests {

	@Autowired
	private JWTService jwtService;
	@Test
	void contextLoads() throws Exception{
		MembershipEntity membershipEntity = new MembershipEntity(Long.valueOf(2434),"puru123","puru","bharat","gutthe",
				Long.valueOf(1),"AsstProfessor","7743623432","Purushottam@gmail.com","Pass");
		String generatedToken = jwtService.generateToken(membershipEntity);
		String usernameInToken = jwtService.getUsernameFromToken(generatedToken);
		System.out.println("Token has user: "+generatedToken);
		System.out.println("generated Token is: "+generatedToken);
	}

}
