package pl.coderslab.carshop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedUser", "shoppingCart", "itemsList"})
public class CartItemController {

    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    @GetMapping("/addToCart/{id}")
    public String addToCart(Model model, @PathVariable String id, @SessionAttribute("shoppingCart") Cart cart){
        CartItem cartItem = new CartItem();
        Item itemToAdd = itemRepository.getById(Long.parseLong(id));
        boolean isInCart = false;

        List<CartItem> cartItemList2 = cartItemRepository.findCartItemsByCartId(cart.getId());

        for (int i = 0; i < cartItemList2.size(); i++) {
            if(cartItemList2.get(i).getItem().equals(itemToAdd)) {
                isInCart = true;
                int amount = cartItemList2.get(i).getQuantity()+1;
                cartItemService.updateAmount(cartItemList2.get(i).getId(), amount);
            }
        }

        if(!isInCart){
            CartItem cartItemAdded = cartItemService.saveCartItem(cartItem, itemRepository.getById(Long.parseLong(id)), cart);
            model.addAttribute("cartItemAdded", cartItemAdded);
        }

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());
        model.addAttribute("cartItems", cartItemList);

        cartService.updateValueOfCart(cart);
        model.addAttribute("cartValue", cart.getTotalValue());

        return "redirect:/welcome";
    }

    @GetMapping("/shoppingCartDetails")
    public String getShoppingCartDetails(Model model, @SessionAttribute("shoppingCart") Cart cart){
        int cartItemsAmount = 0;

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());
        model.addAttribute("cartItems", cartItemList);

        for (int i = 0; i < cartItemList.size(); i++) {
            cartItemsAmount += cartItemList.get(i).getQuantity();
        }
        model.addAttribute("cartItemsAmount", cartItemsAmount);

        cartService.updateValueOfCart(cart);
        model.addAttribute("cartValue", cart.getTotalValue());

        return "shoppingCartDetails";
    }

    @GetMapping("/increaseAmount/{id}")
    public String increaseAmount(@PathVariable String id){
        int amount = cartItemRepository.findCartItemById(Long.parseLong(id)).getQuantity()+1;
        cartItemService.updateAmount(Long.parseLong(id), amount);
        return "redirect:/shoppingCartDetails";
    }

    @GetMapping("/decreaseAmount/{id}")
    public String decreaseAmount(@PathVariable String id){
        int amount = cartItemRepository.findCartItemById(Long.parseLong(id)).getQuantity()-1;
        cartItemService.updateAmount(Long.parseLong(id), amount);
        return "redirect:/shoppingCartDetails";
    }

    @GetMapping("/removeItemFromCart/{id}")
    public String removeItemFromCart(@PathVariable String id){
        cartItemService.deleteItem(Long.parseLong(id));
        return "redirect:/shoppingCartDetails";
    }
}
