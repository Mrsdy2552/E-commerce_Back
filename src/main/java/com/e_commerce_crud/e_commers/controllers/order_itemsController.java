package com.e_commerce_crud.e_commers.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce_crud.e_commers.entities.OrderItems;
import com.e_commerce_crud.e_commers.services.order_itemsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/OrderItem")
@CrossOrigin(origins = "*")
public class order_itemsController {

    @Autowired
    private order_itemsService service;

    @GetMapping
    public List<OrderItems> list() {
        return service.finAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderfindById(@PathVariable long id) {
        Optional<OrderItems> OrderOptional = service.finById(id);
        if (OrderOptional.isPresent()) {
            return ResponseEntity.ok(OrderOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<?> postCreate(@Valid @RequestBody OrderItems Order, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(Order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUpdate(@Valid @RequestBody OrderItems Order, BindingResult result, @PathVariable Long id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        Optional<OrderItems> OrderUpdate = service.update(id, Order);
        if (OrderUpdate.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(OrderUpdate.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteorder_items(@PathVariable long id) {
        Optional<OrderItems> OrderOptional = service.delete(id);
        if (OrderOptional.isPresent()) {
            return ResponseEntity.ok(OrderOptional.orElseThrow());
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

    @GetMapping("/orderfull/{id}")
    public ResponseEntity<?> getfindByOrderId(@PathVariable long id) {
        List<OrderItems> orderItems = service.finAllByIdOrder(id);

        if (orderItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderItems);
    }
}
