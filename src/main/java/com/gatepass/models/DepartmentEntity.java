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
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = {"dptid"}))
public class DepartmentEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dptid;

    @Column(nullable = false, length = 60)
    private String deptname;

    @Column()
    private String address;

    @Column
    private String description;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StaffEntity> staffList;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HODEntity> hodList;

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClerkEntity> clerkList;

}