package com.e_commerce_crud.e_commers.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.entities.Orders;

public interface OrdersRepository extends CrudRepository< Orders, Long> {

}
