package pl.coderslab.carshop.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.carshop.item.Item;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "cartItems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "cartItem_cart", joinColumns = @JoinColumn(name = "cartItem_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
//    private Set<CartItem> carts;
//
//    @ManyToMany
//    @JoinTable(name = "cartItem_item", joinColumns = @JoinColumn(name = "cartItem_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
//    private Set<Item> items;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Item item;
}
