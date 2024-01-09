package com.huijin.yummy.member.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "expired_session")
public class ExpiredSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String jwt;
    @CreationTimestamp //insert시 시간 자동 적용
    private LocalDateTime expiredDate;

    public ExpiredSession() {
    }

    public ExpiredSession(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }
}
