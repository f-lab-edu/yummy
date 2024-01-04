package com.huijin.yummy.member.entity;

import com.huijin.yummy.pay.entity.Payment;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
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

    // ✨ 빌더 패턴을 위한 빌더 클래스!
    static public class Builder {
        private String email;
        private String password;
        private String name;
        private String phoneNumber;
        private String address;
        private String memberType;

        public Builder() {
        }

        public Builder(Member member) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.memberType = memberType;
        }

        public Member.Builder email(String email) {
            this.email = email;
            return this;
        }

        public Member.Builder password(String password) {
            this.password = password;
            return this;
        }

        public Member.Builder name(String name) {
            this.name = name;
            return this;
        }

        public Member.Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Member.Builder address(String address) {
            this.address = address;
            return this;
        }

        public Member.Builder memberType(String memberType) {
            this.memberType = memberType;
            return this;
        }

        public Member build() {
            return new Member(email, password, name, phoneNumber, address, memberType);
        }
    }
}
