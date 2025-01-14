package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "phone", "email"}))
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long staffid;

    @Column(nullable = false, length = 40, unique = true, updatable = false)
    private String username;

    @Column(nullable = false, length = 40)
    private String fname;

    @Column(nullable = false, length = 40)
    private String sname;

    @Column(nullable = false, length = 40)
    private String lname;

    @Column(nullable = false, length = 40)
    private String designation;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "dptid", referencedColumnName = "dptid")
    private Department department;

}
