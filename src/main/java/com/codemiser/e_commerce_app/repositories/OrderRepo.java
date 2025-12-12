package com.codemiser.e_commerce_app.repositories;

import com.codemiser.e_commerce_app.modals.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

    Order getByOrderNumber(String orderNumber);
}
