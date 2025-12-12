package com.codemiser.e_commerce_app.dto;

import java.time.LocalDateTime;

public record OrderResponse(String orderNumber, LocalDateTime orderedTime) {
}
