package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;
import com.e_commerce_crud.e_commers.entities.Customers;

public interface CustomersServiceImpl {

    List<Customers> finAll();

    Optional<Customers> finById(long id);

    Customers save(Customers product);

    Optional<Customers> update(long id, Customers customer);

    Optional<Customers> delete(Long id);

}
