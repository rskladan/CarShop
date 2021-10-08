package pl.coderslab.carshop.user;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Wrong email format")
    private String email;

    @Size(min = 8, message = "Password should contain at least 8 characters")
    private String password;
}
