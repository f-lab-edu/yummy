package com.huijin.yummy.member.repository;

import com.huijin.yummy.member.entity.ExpiredSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpiredSessionRepository extends JpaRepository<ExpiredSession, Long> {
    Boolean existsByJwt(String jwt);
}
