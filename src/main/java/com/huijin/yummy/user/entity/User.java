package com.huijin.yummy.user.entity;

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

    public User(String userId, String password, String userName, String phoneNumber, String address, String userType, LocalDateTime joinedDate) {
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

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getUserType() {
        return userType;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDateTime joinedDate) {
        this.joinedDate = joinedDate;
    }
}
