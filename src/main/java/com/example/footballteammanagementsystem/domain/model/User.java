package com.example.footballteammanagementsystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    @NotNull
    private String username;
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    private Instant createdDate;
    private boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("TRAINER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
