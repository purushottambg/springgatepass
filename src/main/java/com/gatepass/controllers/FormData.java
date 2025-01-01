package com.gatepass.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormData {
    private int id;
    private String name, department, subject, email, address;
    private Boolean isTeaching;


}
