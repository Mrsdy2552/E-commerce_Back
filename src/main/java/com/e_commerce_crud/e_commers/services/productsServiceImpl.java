package com.e_commerce_crud.e_commers.services;

import java.util.List;
import java.util.Optional;

import com.e_commerce_crud.e_commers.entities.products;

public interface productsServiceImpl {

    List<products> finAll();

    Optional<products> finById(long id);

    products save(products product);

    Optional<products> update(long id, products product);

    Optional<products> delete(Long id);

}
