package com.codemiser.e_commerce_app.controllers;


import com.codemiser.e_commerce_app.modals.OrderData;
import com.codemiser.e_commerce_app.modals.Product;
import com.codemiser.e_commerce_app.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductServices service;

    @PostMapping(value = "/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProducts(@RequestPart("product") Product product, @RequestPart("image") MultipartFile image) throws IOException {
        Product product1 = service.addProduct(product, image);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @PostMapping("/placeorder")
    public List<Product> placeOrder(@RequestBody List<OrderData> data) {
        return service.updateProductStock(data);
    }

}
