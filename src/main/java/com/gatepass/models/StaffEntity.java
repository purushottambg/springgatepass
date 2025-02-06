package com.gatepass.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
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

    @OneToMany(mappedBy = "staffEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PassEntity> passes;


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
