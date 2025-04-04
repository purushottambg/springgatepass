package com.gatepass.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ch.qos.logback.classic.db.names.ColumnName;
import com.gatepass.service.ClerkService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clerk", uniqueConstraints = @UniqueConstraint(columnNames = {"clerkid", "username", "phone", "email"}))
public class ClerkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clerkid;

    @Column(name = "username")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dptid", nullable = false)
    @JsonBackReference("department-clerk")
    @ToString.Exclude
    private DepartmentEntity departmentEntity;
}
