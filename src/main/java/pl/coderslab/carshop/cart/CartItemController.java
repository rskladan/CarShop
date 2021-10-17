package pl.coderslab.carshop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;
import pl.coderslab.carshop.user.User;
import pl.coderslab.carshop.user.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedUser", "shoppingCart", "itemsList"})
public class CartItemController {

    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart(Model model, @SessionAttribute("loggedUser") User user) {
        ModelAndView modelAndView = new ModelAndView();

        if(!model.containsAttribute("shoppingCart")) {
            Cart cart = new Cart();
            cartService.saveCart(cart, user);
            modelAndView.addObject("shoppingCart", cart);
        } else {
            Cart cart = (Cart)model.getAttribute("shoppingCart");
            List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());
            for (int i = 0; i < cartItemList.size(); i++) {
                if(cartItemList.get(i).getQuantity()==0){
                    cartItemService.deleteItem(cartItemList.get(i).getId());
                }
            }
        }

        List<Item> allitems = itemRepository.findAll();
        modelAndView.addObject("itemsList", allitems);
        modelAndView.setViewName("shoppingCart");
        return modelAndView;
    }


    @GetMapping("/addToCart/{id}")
    public ModelAndView addToCart(@PathVariable String id, @SessionAttribute("shoppingCart") Cart cart){
        ModelAndView modelAndView = new ModelAndView();
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
            modelAndView.addObject("cartItemAdded", cartItemAdded);
        }

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());
        modelAndView.addObject("cartItems", cartItemList);

        cartService.updateValueOfCart(cart);
        modelAndView.addObject("cartValue", cart.getTotalValue());

        modelAndView.setViewName("addToCart");
        return modelAndView;
    }

    @GetMapping("/shoppingCartDetails")
    public ModelAndView getShoppingCartDetails(@SessionAttribute("shoppingCart") Cart cart){
        ModelAndView modelAndView = new ModelAndView();

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());
        modelAndView.addObject("cartItems", cartItemList);

        cartService.updateValueOfCart(cart);
        modelAndView.addObject("cartValue", cart.getTotalValue());

        modelAndView.setViewName("shoppingCartDetails");
        return modelAndView;
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
