package pl.coderslab.carshop.item;

import lombok.Data;
import pl.coderslab.carshop.manufacturer.Manufacturer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    private String name;

    @Size(max = 500)
    private String description;

    @Min(1)
    private BigDecimal price;

    @ManyToOne
    @NotNull
    private Manufacturer manufacturer;

    private String productCode;
}
