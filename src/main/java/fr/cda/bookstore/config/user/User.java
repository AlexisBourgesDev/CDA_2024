package fr.cda.bookstore.config.user;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "token")
    private String token;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Roles role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    // List des rôles utilisateur
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    // Récupération du mot de passe (HASHE !!)
    public String getPassword() {
        return password;
    }

    // Récupération du login
    @Override
    public String getUsername() {
        return email;
    }

    // Permet de savoir si l'utilisateur n'a pas expiré
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Permet de savoir si le compte de l'utilisateur n'a pas été bloqué
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Permet de savoir si l'utilisateur doit renouveller son mot de passe
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Permet de savoir si l'utilisateur est autorisé à s'authentifier (confirmation du compte)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
