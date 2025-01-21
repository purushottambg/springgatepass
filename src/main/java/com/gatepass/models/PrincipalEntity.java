package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "principal", uniqueConstraints = @UniqueConstraint(columnNames = {"prnid", "username", "phone", "email"}))
public class PrincipalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prnid;

    @Column(name = "username", unique = true)
    private String userName;

    private String fname;
    private String sname;
    private String lname;

    private String designation;
    private String phone;
    private String email;
    private String password;

}
