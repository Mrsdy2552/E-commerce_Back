package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.repositories.order_itemsRepository;

@Service
public class order_itemsService implements order_itemsServiceImpl {

    @Autowired
    private order_itemsRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<OrderItems> finAll() {
        return (List<OrderItems>) repository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public Optional<OrderItems> finById(long id) {
        return repository.findById(id);
    }

    @Override
    public OrderItems save(OrderItems order) {
        return repository.save(order);
    }

    @Override
    public Optional<OrderItems> update(long id, OrderItems order_items) {

        Optional<OrderItems> order_itemsdb = repository.findById(id);
        if (order_itemsdb.isPresent()) {
            OrderItems ordens = order_itemsdb.orElseThrow();
            ordens.setOrder(order_items.getOrder());
            ordens.setPrice(order_items.getPrice());
            ordens.setProduct_id(order_items.getProduct_id());
            ordens.setQuantity(order_items.getQuantity());

            return Optional.of(repository.save(ordens));
        }
        return order_itemsdb;

    }

    @Override
    public Optional<OrderItems> delete(Long id) {
        Optional<OrderItems> order_itemsdb = repository.findById(id);
        order_itemsdb.ifPresent(order_items -> {
            repository.delete(order_items);
        });

        return order_itemsdb;
    }

    @Override
    public List<OrderItems> finAllByIdOrder(Long IdOrder) {
        return repository.findByOrderId(IdOrder);
    }

    // @Override
    // public List<OrderItems> findByOrderId(Long orderId) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
}
