package com.gatepass.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("department-staff")
    @ToString.Exclude
    private List<StaffEntity> staffList = new ArrayList<>();

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("department-hod")
    @ToString.Exclude
    private List<HODEntity> hodList = new ArrayList<>();

    @OneToMany(mappedBy = "departmentEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("department-clerk")
    @ToString.Exclude
    private List<ClerkEntity> clerkList = new ArrayList<>();
}