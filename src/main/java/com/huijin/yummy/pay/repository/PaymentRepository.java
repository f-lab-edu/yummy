package com.huijin.yummy.pay.repository;

import com.huijin.yummy.pay.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public Payment findByTid(String tid);
}
