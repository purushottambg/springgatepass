package com.gatepass.service;

import com.gatepass.dtos.PassDTO;
import com.gatepass.exceptions.FailedToSavePassException;
import com.gatepass.models.PassEntity;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.PassesRepo;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassesService {

    private final PassesRepo passesRepo;
    private final StaffRepo staffRepo;
    private final ModelMapper modelMapper;

    Logger log = LoggerFactory.getLogger(PassesService.class);
    public Long savePass(PassDTO passDTO) {

        /*
        modelmapper.typeMap is a kinda mapper that defines how mapping should take place
         */

        log.info("staff userName in service layer is: {}", passDTO.getUserName().toLowerCase());
        Optional<StaffEntity> queryStaffByUserName = staffRepo.findByUsername(passDTO.getUserName().toLowerCase());
        queryStaffByUserName.ifPresent(staffEntity -> {
            passDTO.setStaffId(queryStaffByUserName.get().getStaffid());
            log.info("Pass Staff id: {}, Entity Staff ID: {}",passDTO.getStaffId(), staffEntity.getStaffid());
        });

         queryStaffByUserName.ifPresent(staffEntity -> {
             log.info("User ID after querying based on username: {}",staffEntity.getStaffid());
         });

        log.info("Staff id for the username {} is: {}",passDTO.getUserName(), passDTO.getStaffId());
        modelMapper.typeMap(PassDTO.class, PassEntity.class)
                .setConverter(
                        context -> {
                            PassDTO src = context.getSource();
                            PassEntity dest = new PassEntity();
                            dest.setStaffEntity(staffRepo.getById(src.getStaffId()));
                            dest.setReason(src.getReason());
                            dest.setSubReason(src.getSubreason());
                            dest.setIntime(src.getIntime());
                            dest.setOuttime(src.getOuttime());
                            dest.setDescription(src.getDescription());

                        return dest;
                    });

        PassEntity tobeSavedPass = modelMapper.map(passDTO, PassEntity.class);
        log.info("To be saved pass staff id: {}",tobeSavedPass.getStaffEntity().getFname());
        PassEntity savedPass = passesRepo.save(tobeSavedPass);

        if ( savedPass!=null && savedPass.getPassid()!=null ) {
            log.info("Pass saved in the Database and pass id is : {}", savedPass.getPassid());
            return savedPass.getPassid();
        }else {
            log.info("Pass not saved");
            throw new FailedToSavePassException("Pass couldn't saved, Try again");
        }
    }
}