package com.codemiser.e_commerce_app.services;

import com.codemiser.e_commerce_app.modals.OrderData;
import com.codemiser.e_commerce_app.modals.Product;
import com.codemiser.e_commerce_app.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return repo.findAll();
    }

    public List<Product> updateProductStock(List<OrderData> data) {
        List<Product> updatedProducts = new ArrayList<>();
        for (OrderData item : data) {
            Optional<Product> productItem = repo.findById(item.id());
            if (productItem.isPresent()) {
                Product p = productItem.get();
                p.setStockAvailable(p.getStockAvailable() - item.count());
                updatedProducts.add(p);
            }
        }
        return repo.saveAll(updatedProducts);
    }
}
