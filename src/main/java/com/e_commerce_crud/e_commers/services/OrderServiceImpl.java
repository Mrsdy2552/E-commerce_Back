package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.entities.Orders;

public interface OrderServiceImpl {

    List<Orders> finAll();

    Optional<Orders> finById(long id);

    Orders save(Orders order);

    Optional<Orders> update(long id, Orders orders);

    Optional<Orders> delete(Long id);

}
