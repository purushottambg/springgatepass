package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dptid;

    @Column(nullable = false, length = 60)
    private String deptname;

    @Column()
    private String address;

    @Column
    private String description;

    @OneToMany(mappedBy = "staffid", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffEntity> staffList;

}