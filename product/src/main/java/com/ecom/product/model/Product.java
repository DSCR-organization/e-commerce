package com.ecom.product.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "product")
public class Product {
    @Id
    private String code;
    private String productName;
    private String description;
    private String category;
    private BigDecimal unitPrice;
    private int quantity;
    private double discount;
}
