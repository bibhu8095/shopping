
package kart.shopping.orderservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import kart.shopping.orderservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//	@Query("from Order o where o.user.userId = ?1")
	List<Order> findByUserId(Long userId);
	
}
