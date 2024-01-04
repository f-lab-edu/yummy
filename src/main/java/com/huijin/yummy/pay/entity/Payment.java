package com.huijin.yummy.pay.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long storeId;
    private Long productId;
    private String tid;
    private String payerName;
    private String productName;
    private int quantity;
    private int paymentAmount;
    private String paymentMethodType;
    private String pgCompany;
    private String paymentStatus;
    @CreationTimestamp //insert시 시간 자동 적용
    private LocalDateTime paymentDate;

    public Payment() {
    }

    public Payment(Long memberId, Long storeId, Long productId, String tid, String payerName, String productName, int quantity, int paymentAmount, String paymentMethodType, String pgCompany, String paymentStatus) {
        this.memberId = memberId;
        this.storeId = storeId;
        this.productId = productId;
        this.tid = tid;
        this.payerName = payerName;
        this.productName = productName;
        this.quantity = quantity;
        this.paymentAmount = paymentAmount;
        this.paymentMethodType = paymentMethodType;
        this.pgCompany = pgCompany;
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(int paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public String getPgCompany() {
        return pgCompany;
    }

    public void setPgCompany(String pgCompany) {
        this.pgCompany = pgCompany;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    // ✨ 빌더 패턴을 위한 빌더 클래스!
    static public class Builder {
        private Long memberId;
        private Long storeId;
        private Long productId;
        private String tid;
        private String payerName;
        private String productName;
        private int quantity;
        private int paymentAmount;
        private String paymentMethodType;
        private String pgCompany;
        private String paymentStatus;

        public Builder() {
        }

        public Builder(Payment payment) {
            this.memberId = memberId;
            this.storeId = storeId;
            this.productId = productId;
            this.tid = tid;
            this.payerName = payerName;
            this.productName = productName;
            this.quantity = quantity;
            this.paymentAmount = paymentAmount;
            this.paymentMethodType = paymentMethodType;
            this.pgCompany = pgCompany;
            this.paymentStatus = paymentStatus;
        }

        public Builder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder storeId(Long storeId) {
            this.storeId = storeId;
            return this;
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder tid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder payerName(String payerName) {
            this.payerName = payerName;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder paymentAmount(int paymentAmount) {
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder paymentMethodType(String paymentMethodType) {
            this.paymentMethodType = paymentMethodType;
            return this;
        }

        public Builder pgCompany(String pgCompany) {
            this.pgCompany = pgCompany;
            return this;
        }

        public Builder paymentStatus(String paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }
        
        public Payment build() {
            return new Payment(memberId, storeId, productId, tid, payerName, productName, quantity, paymentAmount, paymentMethodType, pgCompany, paymentStatus);
        }
    }
}
