package com.gatepass.models;

import ch.qos.logback.classic.db.names.ColumnName;
import com.gatepass.service.ClerkService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clerk", uniqueConstraints = @UniqueConstraint(columnNames = {"clerkid", "userName", "phone", "email"}))
public class ClerkEntity {

    @Id
    private Long clerkid;

    @Column(name = "userName")
    private String userName;

    @Column(name = "fname")
    private String fname;

    @Column(name = "sname")
    private String sname;

    @Column(name="lname")
    private String lname;

    @Column(name = "designation")
    private String designation;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "dptid", nullable = false)
    private DepartmentEntity departmentEntity;



}
