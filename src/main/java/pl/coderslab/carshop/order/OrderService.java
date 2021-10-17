package pl.coderslab.carshop.order;

import org.springframework.stereotype.Service;
import pl.coderslab.carshop.cart.Cart;
import pl.coderslab.carshop.user.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Cart cart, User user){
        Order order = new Order();
        order.setCart(cart);
        order.setDateTime(LocalDateTime.now());
        order.setUser(user);
        return orderRepository.save(order);
    }

    public List<Order> getOrderList (User user) {
        List<Order> orderList = orderRepository.findAllByUserId(user.getId());
        return orderList;
    }

}
