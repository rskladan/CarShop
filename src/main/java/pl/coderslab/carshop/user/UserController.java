package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.coderslab.carshop.cart.*;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedUser", "shoppingCart", "itemsList"})
public class UserController {

    private final UserService userService;
    private final ItemRepository itemRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;
    private PasswordEncoder passwordEncoder;
    private HttpSession httpSession;
    protected final Logger log = LoggerFactory.getLogger(getClass());


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findByUserName(user.getUsername());
        User emailExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (emailExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(Model model){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        modelAndView.addObject("loggedUser", user);

        modelAndView.setViewName("/welcome");
        return modelAndView;
    }

    @GetMapping("/accountInfo")
    public ModelAndView getAccountInfo() {
        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findByUserName(auth.getName());
//        modelAndView.addObject("loggedUser", user);
        modelAndView.setViewName("accountInfo");
        return modelAndView;
    }

    @PostMapping("/accountInfo/{id}")
    public ModelAndView getAccountInfo(@Valid @ModelAttribute("loggedUser") User user, BindingResult result, @PathVariable(value="id") String id ) {
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.setViewName("accountInfo");
        } else {
            log.info("User details:" + user.toString());
            userService.updateUser(user);
            modelAndView.setViewName("/welcome");
        }
        return modelAndView;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart(Model model, @SessionAttribute("loggedUser") User user) {
        ModelAndView modelAndView = new ModelAndView();

        if(!model.containsAttribute("shoppingCart")) {
            Cart cart = new Cart();
            cartService.saveCart(cart, user);
            modelAndView.addObject("shoppingCart", cart);
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
