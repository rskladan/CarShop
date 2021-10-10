package pl.coderslab.carshop.user;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

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

    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public boolean isEnabled() {
        return enabled;
    }
}
