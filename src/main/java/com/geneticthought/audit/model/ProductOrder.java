package com.geneticthought.audit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity

public class ProductOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String userLogin;

    private String productId;

    private LocalDateTime creationTime;

    protected ProductOrder(){

    }

    public ProductOrder(String userLogin, String productId, LocalDateTime creationTime) {
        this.userLogin = userLogin;
        this.productId = productId;
        this.creationTime = creationTime;
    }
    public Long getId() {
        return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
