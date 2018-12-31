package com.geneticthought.audit.service;

import com.geneticthought.audit.consumer.ProductOrderEvent;
import com.geneticthought.audit.model.ProductOrder;
import com.geneticthought.audit.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderEventProcessor {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void processEvent(ProductOrderEvent event) {
        ProductOrder pOrder = new ProductOrder(event.getUserLogin(),event.getProductId(), event.getCreationTime());
        productOrderRepository.save(pOrder);
    }

}
