package com.codemiser.e_commerce_app.controllers;

import com.codemiser.e_commerce_app.modals.Product;
import com.codemiser.e_commerce_app.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/productsByIds")
    public List<Product> getProductsByIds(@RequestBody List<Integer> productIds){
        return service.getProductsByIds(productIds);
    }

    @GetMapping("/profile")
    public String getProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        return "Logged in user: "
                + authentication.getName();
    }

}
