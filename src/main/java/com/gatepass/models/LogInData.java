package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.xml.soap.SAAJResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInData {
    private String fname;
    private String lname;
    private String phone;
    private String userName;
    private String password;
}
