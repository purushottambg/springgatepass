package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="hod",
        uniqueConstraints = @UniqueConstraint(columnNames ={"hodid", "username", "phone", "email"})
)
public class HODEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hodid;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    private String fname;

    private String sname;

    private String lname;

    private String designation;

    private String password;

    @Column(unique = true)
    private String phone;

    private String email;

    @ManyToOne()
    @JoinColumn(name = "dptid", nullable = false)
    private DepartmentEntity departmentEntity;

}
