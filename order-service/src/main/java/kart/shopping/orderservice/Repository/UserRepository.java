package kart.shopping.orderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kart.shopping.orderservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
