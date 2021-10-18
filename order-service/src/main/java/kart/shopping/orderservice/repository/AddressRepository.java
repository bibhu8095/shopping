package kart.shopping.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kart.shopping.orderservice.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
