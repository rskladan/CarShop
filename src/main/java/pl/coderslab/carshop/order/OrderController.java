package pl.coderslab.carshop.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
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

    @PostMapping("/finaliseOrder")
    public ModelAndView saveOrder(@SessionAttribute("shoppingCart") Cart cart, @SessionAttribute("loggedUser") User user, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderService.saveOrder(cart, user);
        modelAndView.addObject("order", order);

        session.removeAttribute("shoppingCart");

        modelAndView.setViewName("/showFinalisedOrder");
        return modelAndView;
    }

    @GetMapping("/orderHistory")
    public ModelAndView showOrderHistory(@SessionAttribute("loggedUser") User user){
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orderList = orderService.getOrderList(user);
        modelAndView.addObject("orderList", orderList);
        modelAndView.setViewName("orderHistory");
        return modelAndView;
    }

    @GetMapping("/orderDetails/{id}")
    public ModelAndView addToCart(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView();
        Order orderDetails = orderRepository.getById(Long.parseLong(id));
        modelAndView.addObject("orderDetails", orderDetails);

        List<CartItem> cartItemList = cartItemRepository.findCartItemsByCartId(orderRepository.getById(Long.parseLong(id)).getCart().getId());
        modelAndView.addObject("cartItemList", cartItemList);
        modelAndView.setViewName("orderDetails");
        return modelAndView;
    }
}
