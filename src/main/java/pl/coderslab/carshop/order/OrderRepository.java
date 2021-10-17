package pl.coderslab.carshop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.carshop.user.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);
}
