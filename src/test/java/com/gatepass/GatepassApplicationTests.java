package com.gatepass;

import com.gatepass.models.DepartmentEntity;
import com.gatepass.models.StaffEntity;
import com.gatepass.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatepassApplicationTests {

	@Autowired
	private JWTService jwtService;
	@Test
	void contextLoads() {
		StaffEntity staffEntity = new StaffEntity(Long.valueOf(2434),"puru123","puru","bharat","gutthe",
				"businessman","8734765623","prur@gmai.com","pass", DepartmentEntity.builder()
						.dptid(Long.valueOf(1))
								.address("NA")
										.description("ds").build());
		String generatedToken = jwtService.generateToken(staffEntity);
		System.out.println("generated Token is: "+generatedToken);
	}

}
