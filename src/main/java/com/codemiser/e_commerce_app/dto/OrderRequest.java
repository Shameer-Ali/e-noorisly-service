package com.codemiser.e_commerce_app.dto;

import com.codemiser.e_commerce_app.modals.OrderItem;

import java.util.List;

public record OrderRequest(String customerName, String customerEmail, String phoneNumber, List<OrderItem> orderItems) {
}
