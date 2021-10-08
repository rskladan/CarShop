package pl.coderslab.carshop.manufacturer;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "manufacturers")
@Data
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
