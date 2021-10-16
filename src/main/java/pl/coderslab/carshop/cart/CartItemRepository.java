package pl.coderslab.carshop.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.carshop.item.Item;

import java.math.BigDecimal;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findCartItemById (Long id);

    List<CartItem> findCartItemsByCartId(Long cartId);

    @Query("SELECT SUM(ci.price * ci.quantity) FROM CartItem ci WHERE ci.cart.id=?1")
    BigDecimal countValueOfCart(Long id);

}
