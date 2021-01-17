package com.oelprince.locking.prototype.repository;

import com.oelprince.locking.prototype.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Customer b where b.customerNbr=:customerNbr")
    Optional<Customer> findById(@Param("customerNbr") String customerNbr);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Customer b where b.customerFilerCd=:customerFilerCd")
    Customer findCustomerFilerCdBy(@Param("customerFilerCd") String customerFilerCd);


}
