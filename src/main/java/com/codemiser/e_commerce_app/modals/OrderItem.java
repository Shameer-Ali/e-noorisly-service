package com.codemiser.e_commerce_app.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    Integer productId;
    Integer count;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    Order order;
}
