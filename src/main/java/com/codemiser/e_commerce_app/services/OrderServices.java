package com.codemiser.e_commerce_app.services;

import com.codemiser.e_commerce_app.dto.OrderRequest;
import com.codemiser.e_commerce_app.modals.Order;
import com.codemiser.e_commerce_app.modals.OrderItem;
import com.codemiser.e_commerce_app.modals.OrderStatus;
import com.codemiser.e_commerce_app.modals.Product;
import com.codemiser.e_commerce_app.repositories.OrderRepo;
import com.codemiser.e_commerce_app.repositories.ProductRepo;
import com.codemiser.e_commerce_app.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderServices {
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    ProductRepo productRepo;
//    public List<Product> updateProductStock(List<OrderItem> data) {
//        List<Product> updatedProducts = new ArrayList<>();
//        for (OrderItem item : data) {
//            Optional<Product> productItem = repo.findById(item.id());
//            if (productItem.isPresent()) {
//                Product p = productItem.get();
//                p.setStockAvailable(p.getStockAvailable() - item.count());
//                updatedProducts.add(p);
//            }
//        }
//        return repo.saveAll(updatedProducts);
//    }

    private String generateOrderNumber() {
        int number = ThreadLocalRandom.current().nextInt(Constant.MIN, Constant.MAX + 1);
        return String.valueOf(number);
    }


    public Order addOrder(OrderRequest order) {
        HashMap<Integer, Integer> itemsIds = new HashMap<>();
        Order orderData = new Order();
        orderData.setCustomerEmail(order.customerEmail());
        orderData.setCustomerName(order.customerName());
        orderData.setPhoneNumber(order.phoneNumber());
        orderData.setStatus(OrderStatus.PENDING);
        orderData.setOrderedTime(LocalDateTime.now());
        orderData.setOrderNumber(generateOrderNumber());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItem itemReq : order.orderItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setCount(itemReq.getCount());
            item.setOrder(orderData);
            items.add(item);
            itemsIds.put(item.getProductId(), item.getCount());
        }
        List<Product> products = productRepo.findAllById(itemsIds.keySet());
        BigDecimal totalValue = BigDecimal.valueOf(0);
        for (Product product : products) {
            Integer count = itemsIds.get(product.getProductId());
            if (count > 0) {
                product.setStockAvailable(product.getStockAvailable() - count);
                totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(count)));
            }
        }
        productRepo.saveAll(products);
        orderData.setTotalAmount(totalValue);
        orderData.setOrderItems(items);
        return orderRepo.save(orderData);
    }

    public ResponseEntity<Order> getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepo.getByOrderNumber(orderNumber);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
