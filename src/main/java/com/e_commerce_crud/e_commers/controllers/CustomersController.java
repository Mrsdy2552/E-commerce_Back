package com.e_commerce_crud.e_commers.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

import com.e_commerce_crud.e_commers.entities.Customers;
import com.e_commerce_crud.e_commers.services.CustomersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/Customers")
public class CustomersController {

    @Autowired
    private CustomersService service;

    @GetMapping
    public List<Customers> list() {
        return service.finAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerfindById(@PathVariable long id) {
        Optional<Customers> customerOptional = service.finById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> postCreate(@Valid @RequestBody Customers customer, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        customer.setCreated_at(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUpdate(@Valid @RequestBody Customers customer, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<Customers> customerUpdate = service.update(id, customer);
        if (customerUpdate.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerUpdate.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomers(@PathVariable long id) {
        Optional<Customers> customerOptional = service.delete(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.orElseThrow());
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
