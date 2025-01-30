package com.gatepass.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "membershiprequest", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username", "phone", "email"}) })
public class MembershipEntity  extends AuditableEntity  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long appid;

    @Column(nullable = false, length = 20, unique = true, updatable = false)
    private String username;

    @Column(nullable = false, length = 40)
    private String fname;

    @Column(nullable = false, length = 40)
    private String sname;

    @Column(nullable = false, length = 40)
    private String lname;

    @Column(nullable = false)
    private Long dept;

    @Column(nullable = false, length = 40)
    private String designation;

    @Column(nullable = false, length = 12)
    private String phone;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false)
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
