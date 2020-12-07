package com.oelprince.locking.prototype.repository;

import com.oelprince.locking.prototype.entities.ClBillDetl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClBillDetlRepository extends JpaRepository<ClBillDetl, String> {


}
