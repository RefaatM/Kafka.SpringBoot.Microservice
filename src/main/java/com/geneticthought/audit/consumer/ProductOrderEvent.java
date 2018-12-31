package com.geneticthought.audit.consumer;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrderEvent {

    private String userLogin;

    private String productId;

    private LocalDateTime creationTime;

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
