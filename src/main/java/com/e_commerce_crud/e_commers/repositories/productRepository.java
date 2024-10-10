package com.e_commerce_crud.e_commers.repositories;

import org.springframework.data.repository.CrudRepository;

import com.e_commerce_crud.e_commers.entities.products;

public interface productRepository extends CrudRepository<products, Long> {

}
