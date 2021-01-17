package com.oelprince.locking.prototype.repository;

import com.oelprince.locking.prototype.entities.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, String> {
    


}
