package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import com.e_commerce_crud.e_commers.entities.OrderItems;

public interface order_itemsServiceImpl {

    List<OrderItems> finAll();

    List<OrderItems> finAllByIdOrder(Long IdOrder);

    Optional<OrderItems> finById(long id);

    OrderItems save(OrderItems product);

    Optional<OrderItems> update(long id, OrderItems product);

    Optional<OrderItems> delete(Long id);
}
