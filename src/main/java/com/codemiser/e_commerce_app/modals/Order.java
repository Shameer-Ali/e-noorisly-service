package com.codemiser.e_commerce_app.modals;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderId;
    @Column(name = "order_number", nullable = false, unique = true, length = 8)
    String orderNumber; // business ID (8 digits)
    String customerName;
    String customerEmail;
    String phoneNumber;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    BigDecimal totalAmount;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime orderedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderItem> orderItems;
}
