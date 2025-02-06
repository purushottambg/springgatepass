package com.gatepass.dtos;

import lombok.*;
import java.lang.Long;

@Data
@Getter
@Setter
public class LoginDTO {
    private Long id;
    private String userName;
    private String password;
}