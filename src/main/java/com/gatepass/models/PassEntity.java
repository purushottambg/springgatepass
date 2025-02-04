package com.gatepass.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

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

    private Date outtime;

    private Date intime;

    private String reason;

    private String subReason;

    @ManyToOne
    @JoinTable(name = "staffid")
    private StaffEntity staffEntity;

    private String description;

}
