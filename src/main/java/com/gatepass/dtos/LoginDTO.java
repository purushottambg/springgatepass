package com.gatepass.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    private String phone;
    private String userName;
    private String password;
}