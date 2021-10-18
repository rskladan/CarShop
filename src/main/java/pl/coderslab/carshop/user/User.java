package pl.coderslab.carshop.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name cannot be so short")
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Size(min = 2, message = "Surname cannot be so short")
    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @Size(min = 2, max = 40, message = "Username should be between 2 and 40 characters")
    @NotEmpty(message = "Username cannot be empty")
    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Wrong email format")
    private String email;

    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;

    private boolean isEnabled;

    @NotNull
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
    public boolean isEnabled(){
        return isEnabled;
    }

    @PrePersist
    public void prePersist(){
        isEnabled = true;
    }
}
