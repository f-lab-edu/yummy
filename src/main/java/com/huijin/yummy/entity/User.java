package com.huijin.yummy.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userPk;
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private String userName;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String userType;
    @Column
    private LocalDateTime joinedDate;

    public User() {
    }

    public User(long userPk, String userId, String password, String userName, String phoneNumber, String address, String userType, LocalDateTime joinedDate) {
        this.userPk = userPk;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userType = userType;
        this.joinedDate = joinedDate;
    }

    public long getUserPk() {
        return userPk;
    }

    public void setUserPk(long userPk) {
        this.userPk = userPk;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
