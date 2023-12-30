package com.huijin.yummy.member.entity;

import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String memberType;
    //@UpdateTimestamp //update시 시간 자동 적용
    @CreationTimestamp //insert시 시간 자동 적용
    @Column
    private LocalDateTime joinedDate;

    public Member() {
    }

    public Member(String email, String password, String name, String phoneNumber, String address, String memberType) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.memberType = memberType;
    }

    public long getId() {
        return id;
    }

    public void setId(long userPk) {
        this.id = userPk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String userId) {
        this.email = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String userType) {
        this.memberType = userType;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
