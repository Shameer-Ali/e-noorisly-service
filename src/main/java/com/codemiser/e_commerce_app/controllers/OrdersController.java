package com.codemiser.e_commerce_app.controllers;

import com.codemiser.e_commerce_app.dto.OrderRequest;
import com.codemiser.e_commerce_app.dto.OrderResponse;
import com.codemiser.e_commerce_app.modals.Order;

import com.codemiser.e_commerce_app.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OrdersController {
    @Autowired
    OrderServices service;

//    @PostMapping("/placeorder")
//    public List<Product> placeOrder(@RequestBody List<OrderItem> data) {
//        return service.updateProductStock(data);
//    }

    @PostMapping("/placeorder")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest order) {
        Order orderItem = service.addOrder(order);
//        return new ResponseEntity<>(new OrderResponse(orderItem.getOrderId(), orderItem.getOrderedTime()), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(orderItem.getOrderNumber(), orderItem.getOrderedTime()));
    }

    @GetMapping("/order-details")
    public ResponseEntity<Order> getOrderByOrderNumber(@RequestParam("orderNumber") String orderNumber) {
        return service.getOrderByOrderNumber(orderNumber);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return  service.getAllOrders();
    }
}
