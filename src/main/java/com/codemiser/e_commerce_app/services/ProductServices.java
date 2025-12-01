package com.codemiser.e_commerce_app.services;

import com.codemiser.e_commerce_app.modals.Product;
import com.codemiser.e_commerce_app.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServices {
    @Autowired
    ProductRepo repo;

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImage(image.getBytes());
        product.setImageType(image.getContentType());
        return repo.save(product);
    }

    public List<Product> getProducts() {
        return  repo.findAll();
    }
}
