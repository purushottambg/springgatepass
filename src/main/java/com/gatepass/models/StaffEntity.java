package com.gatepass.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "staff",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "phone", "email"}))
public class StaffEntity implements UserDetails {

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

    //Temporary work around
    public String getRole() {
        return "Staff";
    }

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dptid", referencedColumnName = "dptid")
    @JsonBackReference("department-staff")
    private DepartmentEntity departmentEntity;

    @OneToMany(mappedBy = "staffEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<PassEntity> passes = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
