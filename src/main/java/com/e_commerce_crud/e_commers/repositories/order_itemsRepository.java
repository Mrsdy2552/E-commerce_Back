package com.e_commerce_crud.e_commers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.e_commerce_crud.e_commers.entities.OrderItems;

public interface order_itemsRepository extends CrudRepository<OrderItems, Long> {

    @Query("select oi from OrderItems oi where oi.order_id = ?1")
    List<OrderItems> findByOrderId(Long IdOrder);

}
