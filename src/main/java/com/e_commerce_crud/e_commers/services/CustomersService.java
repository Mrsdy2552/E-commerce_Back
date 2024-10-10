package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce_crud.e_commers.entities.Customers;
import com.e_commerce_crud.e_commers.entities.products;
import com.e_commerce_crud.e_commers.repositories.customersRespository;
import com.e_commerce_crud.e_commers.repositories.productRepository;

@Service
public class CustomersService implements CustomersServiceImpl {

    @Autowired
    private customersRespository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Customers> finAll() {
        return (List<Customers>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customers> finById(long id) {
        return repository.findById(id);
    }

    @Override
    public Customers save(Customers customer) {
        return repository.save(customer);
    }

    @Override
    public Optional<Customers> update(long id, Customers customer) {
        Optional<Customers> customersdb = repository.findById(id);
        if (customersdb.isPresent()) {
            Customers custom = customersdb.orElseThrow();
            custom.setFirst_name(customer.getFirst_name());
            custom.setLast_name(customer.getLast_name());
            custom.setEmail(customer.getEmail());
            custom.setPhone(customer.getPhone());
            custom.setAddress(customer.getAddress());
            custom.setCity(customer.getCity());
            custom.setPostal_code(customer.getPostal_code());
            custom.setCountry(customer.getCountry());
            custom.setCreated_at(customer.getCreated_at());
            return Optional.of(repository.save(custom));
        }
        return customersdb;
    }

    @Override
    public Optional<Customers> delete(Long id) {
        Optional<Customers> customersdb = repository.findById(id);
        customersdb.ifPresent(customer -> {
            repository.delete(customer);
        });

        return customersdb;
    }

}
