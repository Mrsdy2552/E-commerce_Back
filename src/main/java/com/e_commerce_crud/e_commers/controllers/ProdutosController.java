package com.e_commerce_crud.e_commers.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce_crud.e_commers.entities.products;
import com.e_commerce_crud.e_commers.services.productsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProdutosController {

    @Autowired
    private productsService service;

    @GetMapping
    public List<products> list() {
        return service.finAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductsfindById(@PathVariable long id) {

        Optional<products> productOptional = service.finById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> postCreate(@Valid @RequestBody products product, BindingResult result) {
        if (result.hasFieldErrors()) {

            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUpdate(@Valid @RequestBody products product, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<products> productUpdate = service.update(id, product);
        if (productUpdate.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(productUpdate.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducts(@PathVariable long id) {
        Optional<products> productOptional = service.delete(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
