package pl.coderslab.carshop.order;

import lombok.Data;
import pl.coderslab.carshop.cart.Cart;
import pl.coderslab.carshop.user.User;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String dateTime;

    @OneToOne
    private Cart cart;

    @ManyToOne
    private User user;

}
