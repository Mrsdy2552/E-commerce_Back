package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e_commerce_crud.e_commers.entities.products;
import com.e_commerce_crud.e_commers.repositories.productRepository;

@Service
public class productsService implements productsServiceImpl {

    @Autowired
    private productRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<products> finAll() {

        return (List<products>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<products> finById(long id) {

        return repository.findById(id);
    }

    @Transactional
    @Override
    public products save(products product) {

        return repository.save(product);
    }

    @Transactional
    @Override
    public Optional<products> update(long id, products product) {
        Optional<products> productdb = repository.findById(id);
        if (productdb.isPresent()) {
            products prod = productdb.orElseThrow();
            prod.setName(product.getName());
            prod.setDescription(product.getDescription());
            prod.setPrice(product.getPrice());
            prod.setStock(product.getStock());
            return Optional.of(repository.save(prod));
        }
        return productdb;

    }

    @Transactional
    @Override
    public Optional<products> delete(Long id) {
        Optional<products> productdb = repository.findById(id);
        productdb.ifPresent(prod -> {
            repository.delete(prod);
        });

        return productdb;
    }

}
