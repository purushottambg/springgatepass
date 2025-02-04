package com.gatepass.service;

import com.gatepass.controllers.OpsController;
import com.gatepass.dtos.PassDTO;
import com.gatepass.models.PassEntity;
import com.gatepass.repository.PassesRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassesService {
    private final PassesRepo passesRepo;
    private final ModelMapper modelMapper;
    Logger log = LoggerFactory.getLogger(OpsController.class);
    public void savePass(PassDTO passDTO) {
        log.info("Date format for intime is: {}", passDTO.getIntime());
        log.info("Date format for outtime is: {}", passDTO.getOuttime());
        PassEntity tobeSavedPass = modelMapper.map(passDTO, PassEntity.class);
        passesRepo.save(tobeSavedPass);
    }
}
