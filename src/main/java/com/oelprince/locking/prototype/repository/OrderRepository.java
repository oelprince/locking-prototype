package com.oelprince.locking.prototype.repository;

import com.oelprince.locking.prototype.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {


}
