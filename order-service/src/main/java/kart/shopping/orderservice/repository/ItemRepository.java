package kart.shopping.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kart.shopping.orderservice.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
