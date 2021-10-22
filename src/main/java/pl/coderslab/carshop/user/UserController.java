package pl.coderslab.carshop.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carshop.cart.*;
import pl.coderslab.carshop.item.Item;
import pl.coderslab.carshop.item.ItemRepository;
import pl.coderslab.carshop.order.Order;
import pl.coderslab.carshop.order.OrderRepository;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"loggedUser", "shoppingCart", "itemsList"})
public class UserController {

    private final UserService userService;

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final CartService cartService;
    private final CartItemRepository cartItemRepository;
    private final CartItemService cartItemService;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/registration")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "/registration";
    }

    @PostMapping("/registration")
    public String add(@Valid User user, BindingResult bindingResult){

        User emailExists = userService.findUserByEmail(user.getEmail());

        if (emailExists != null && !emailExists.getId().equals(user.getId())) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
        }

        if(bindingResult.hasErrors()){
            return "/registration";
        } else {
            userService.saveUser(user);
            return "/login";
        }
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/login")
    public String login(Model model){
        if(isAuthenticated()){
            return "welcome";
        } else {
            model.addAttribute("user", new User());
            return "login";
        }
    }

    @GetMapping("/welcome")
    public String welcome(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(auth.getName());
        model.addAttribute("loggedUser", user);

        int cartItemsAmount = 0;

        if(!model.containsAttribute("shoppingCart")) {
            Cart cart = new Cart();
            cartService.saveCart(cart, user);
            model.addAttribute("shoppingCart", cart);

        } else {
            Cart cart = (Cart) model.getAttribute("shoppingCart");
            List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(cart.getId());

            for (int i = 0; i < cartItemList.size(); i++) {
                cartItemsAmount += cartItemList.get(i).getQuantity();
                if (cartItemList.get(i).getQuantity() == 0) {
                    cartItemService.deleteItem(cartItemList.get(i).getId());
                }
            }
        }

        List<Order> orderList = orderRepository.findAllByUserId(user.getId());
        model.addAttribute("orderList", orderList);
        model.addAttribute("cartItemsAmount", cartItemsAmount);

        if(!orderList.isEmpty()) {
            String lastOrderDate =
                    orderList.stream()
                            .map(Order::getDateTime)
                            .sorted(Comparator.reverseOrder())
                            .findFirst()
                            .get();

            model.addAttribute("lastOrderDate", lastOrderDate);
        } else {
            model.addAttribute("lastOrderDate", "-");
        }
        List<Item> allitems = itemRepository.findAll();
        model.addAttribute("itemsList", allitems);

        return "welcome";
    }

    @GetMapping("/accountInfo")
    public String getAccountInfo() {
        return "accountInfo";
    }

    @PostMapping("/accountInfo/{id}")
    public String getAccountInfo(@Valid @ModelAttribute("loggedUser") User user, BindingResult bindingResult, @PathVariable(value="id") String id, Model model ) {
        User emailExists = userService.findUserByEmail(user.getEmail());

        if (emailExists != null && !emailExists.getId().equals(user.getId())) {
            bindingResult.rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if(bindingResult.hasErrors()){
            return "accountInfo";
        } else {
            userService.updateUser(user);
            return "welcome";
        }
    }

    @GetMapping("/accessDenied")
    public String getDenied(){
        return "403";
    }

    @ModelAttribute("userRoles")
    public List<String> userRoleList(){
        return Stream.of(UserRole.values())
                .map(UserRole::name)
                .collect(Collectors.toList());
    }


    @GetMapping("/admin/secret")
    public String secretPage(){
            return "/admin/secret";
    }

}
