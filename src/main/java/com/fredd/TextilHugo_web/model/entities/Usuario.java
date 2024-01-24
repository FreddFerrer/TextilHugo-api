package com.fredd.TextilHugo_web.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "campo obligatorio")
    @Column(nullable = false)
    private String username;

    @NotEmpty(message = "campo obligatorio")
    private String password;

    @Email
    @NotEmpty(message = "campo obligatorio")
    private String email;

    @Enumerated(EnumType.STRING)
    private RolEnum role;

    @NotEmpty(message = "campo obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "campo obligatorio")
    @Column(nullable = false)
    private String apellido;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
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
