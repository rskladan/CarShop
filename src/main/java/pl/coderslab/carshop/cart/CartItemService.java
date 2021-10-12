package pl.coderslab.carshop.cart;

import org.springframework.stereotype.Service;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;
import pl.coderslab.carshop.user.UserRepository;

import java.math.BigDecimal;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository, ItemRepository itemRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public CartItem saveCartItem(CartItem cartItem, Item item, Cart cart){

        cartItem.setQuantity(1);
        cartItem.setItem(item);
        cartItem.setCart(cart);
        BigDecimal totalCost = item.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setPrice(totalCost);
        return cartItemRepository.save(cartItem);
    }
}
