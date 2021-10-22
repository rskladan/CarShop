package pl.coderslab.carshop.cart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.coderslab.carshop.user.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    private final Logger log = LoggerFactory.getLogger((getClass()));

    public Cart saveCart(Cart cart, User user){
        LocalDateTime localDateTime = LocalDateTime.now();

        cart.setUser(user);
        cart.setTotalValue(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }

    public Cart updateValueOfCart(Cart cart){
        cart.setTotalValue(cartItemRepository.countValueOfCart(cart.getId()));
        return cartRepository.save(cart);
    }




}
