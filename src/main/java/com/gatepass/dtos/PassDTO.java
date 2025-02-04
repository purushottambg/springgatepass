package com.gatepass.dtos;

import com.gatepass.models.StaffEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Data
@Getter
@Setter
public class PassDTO {

    private Date outtime;

    private Date intime;

    private String reason;

    private String subreason;

    private String description;
}
