package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.entities.Orders;
import com.e_commerce_crud.e_commers.repositories.OrdersRepository;

@Service
public class OrderService implements OrderServiceImpl {

    @Autowired
    private OrdersRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Orders> finAll() {
        return (List<Orders>) repository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Orders> finById(long id) {
        return repository.findById(id);
    }

    @Override
    public Orders save(Orders order) {
        return repository.save(order);
    }

    @Override
    public Optional<Orders> update(long id, Orders orders) {

        Optional<Orders> Ordersdb = repository.findById(id);
        if (Ordersdb.isPresent()) {
            Orders ordens = Ordersdb.orElseThrow();
            ordens.setCustomer_id(orders.getCustomer_id());
            ordens.setStatus(orders.getStatus());
            ordens.setTotal(orders.getTotal());

            return Optional.of(repository.save(ordens));
        }
        return Ordersdb;
    }

    @Override
    public Optional<Orders> delete(Long id) {
        Optional<Orders> ordersdb = repository.findById(id);
        ordersdb.ifPresent(orders -> {
            repository.delete(orders);
        });

        return ordersdb;
    }

}
