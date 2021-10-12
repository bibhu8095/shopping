
package kart.shopping.orderservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kart.shopping.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
}
