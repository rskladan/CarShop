package pl.coderslab.carshop.cart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final CartService cartService;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository, ItemRepository itemRepository, CartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.cartService = cartService;
    }

    public CartItem saveCartItem(CartItem cartItem, Item item, Cart cart){

        cartItem.setQuantity(1);
        cartItem.setItem(item);
        cartItem.setCart(cart);
        cartItem.setPrice(item.getPrice());
        BigDecimal totalCost = item.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setTotalPrice(totalCost);
        return cartItemRepository.save(cartItem);
    }

    public void increaseAmount(Long id) {
        CartItem cartItem = cartItemRepository.findCartItemById(id);
        cartItem.setQuantity(cartItem.getQuantity()+1);
        BigDecimal totalCost = cartItem.getItem().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setTotalPrice(totalCost);
        cartItemRepository.save(cartItem);
    }

    public void decreaseAmount(Long id) {
        CartItem cartItem = cartItemRepository.findCartItemById(id);
        cartItem.setQuantity(cartItem.getQuantity()-1);
        BigDecimal totalCost = cartItem.getItem().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
        cartItem.setTotalPrice(totalCost);
        cartItemRepository.save(cartItem);
    }



}
