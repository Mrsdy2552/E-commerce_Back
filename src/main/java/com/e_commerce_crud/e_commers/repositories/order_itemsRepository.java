package com.e_commerce_crud.e_commers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.e_commerce_crud.e_commers.entities.OrderItems;

public interface order_itemsRepository extends CrudRepository<OrderItems, Long> {

    List<OrderItems> findByOrderId(Long orderId);
}
