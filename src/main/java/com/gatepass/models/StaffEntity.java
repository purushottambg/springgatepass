package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff",
        uniqueConstraints = @UniqueConstraint(columnNames = {"username", "phone", "email"}))
public class StaffEntity  implements UserDetails {

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
    private DepartmentEntity departmentEntity;


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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
