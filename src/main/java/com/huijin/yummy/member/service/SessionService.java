package com.huijin.yummy.member.service;

import com.huijin.yummy.member.entity.ExpiredSession;
import com.huijin.yummy.member.repository.ExpiredSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final ExpiredSessionRepository expiredSessionRepository;

    @Autowired
    public SessionService(ExpiredSessionRepository expiredSessionRepository) {
        this.expiredSessionRepository = expiredSessionRepository;
    }

    //만료 테이블에 jwt 저장
    public void expireSession(String jwt) {
        expiredSessionRepository.save(new ExpiredSession(jwt));
    }

    public boolean verify(String jwt) {
        if (expiredSessionRepository.existsByJwt(jwt)) {
            throw new IllegalStateException("Expired token");
        }
        return true;
    }
}
