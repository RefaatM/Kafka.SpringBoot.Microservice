package com.geneticthought.audit.repository;

import com.geneticthought.audit.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}
