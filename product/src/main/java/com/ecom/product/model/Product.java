package com.ecom.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("product")
public class Product {
    @Id
    private String code;
    private String productName;
    private String description;
    private String category;
    private long unitPrice;
    private int quantity;
    private double discount;
}
