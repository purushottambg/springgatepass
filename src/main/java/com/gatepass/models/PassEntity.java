package com.gatepass.models;

import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "passes", uniqueConstraints = @UniqueConstraint(columnNames = {"passid"}))
public class PassEntity extends AuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passid;

    private String outtime;

    private String intime;

    private String reason;

    private String subReason;

    private String description;

    @ManyToOne
    @JoinColumn(name = "staffid",  nullable = false)
    private StaffEntity staffEntity;

    @ManyToOne
    @JoinColumn(name = "dptid")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JoinColumn(name = "hodid")
    private HODEntity hodEntity;

    private String hodpassstatus="pending";

    private String hodremark="NA";

    @ManyToOne
    @JoinColumn(name = "prnid")
    private PrincipalEntity principalEntity;

    private String principalpassstatus="pending";

    private String principalremark="NA";

    @ManyToOne
    @JoinColumn(name = "clerkid")
    private ClerkEntity clerkEntity;

    @Column(updatable = false)
    private String actualreturntime;

    private String officeremark;

}
