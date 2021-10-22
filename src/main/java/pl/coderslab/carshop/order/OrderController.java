package pl.coderslab.carshop.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carshop.cart.*;
import pl.coderslab.carshop.user.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    public OrderController(OrderService orderService, OrderRepository orderRepository, CartItemRepository cartItemRepository, CartService cartService) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
    }

    @GetMapping("/finaliseOrder")
    public String saveOrder(Model model, @SessionAttribute("shoppingCart") Cart cart, @SessionAttribute("loggedUser") User user, HttpSession session){
        Order order = orderService.saveOrder(cart, user);
        model.addAttribute("order", order);

        session.removeAttribute("shoppingCart");

        return "showFinalisedOrder";
    }

    @GetMapping("/orderHistory")
    public String showOrderHistory(Model model, @SessionAttribute("loggedUser") User user){
        List<Order> orderList = orderService.getOrderList(user);
        model.addAttribute("orderList", orderList);
        return "orderHistory";
    }

    @GetMapping("/orderDetails/{id}")
    public String addToCart(@PathVariable String id, Model model){
        Order orderDetails = orderRepository.getById(Long.parseLong(id));
        model.addAttribute("orderDetails", orderDetails);

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(orderRepository.getById(Long.parseLong(id)).getCart().getId());
        model.addAttribute("cartItemList", cartItemList);
        return "orderDetails";
    }

}
