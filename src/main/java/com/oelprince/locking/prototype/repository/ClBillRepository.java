package com.oelprince.locking.prototype.repository;

import com.oelprince.locking.prototype.entities.ClBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface ClBillRepository extends JpaRepository<ClBill, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from ClBill b where b.billNbr=:billNbr")
    Optional<ClBill> findById(@Param("billNbr") String billNbr);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from ClBill b where b.filerCd=:filerCd")
    ClBill findClBillBy(@Param("filerCd") String filerCd);


}
