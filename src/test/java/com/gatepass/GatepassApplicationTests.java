package com.gatepass;

import com.gatepass.dtos.PassDTO;
import com.gatepass.service.PassesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GatepassApplicationTests {
	@Autowired
	PassesService passesService;
	@Autowired
	PassDTO passDTO;
	@Test
	@DisplayName("CheckCreatedPassId")
	public void checkPassIdOfNewlyGenereatedPass(){

		Long createdPassId = passesService.savePass(passDTO);
		System.out.println(createdPassId);
	}




}
