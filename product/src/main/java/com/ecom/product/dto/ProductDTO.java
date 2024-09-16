package com.ecom.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String code;
    private String productName;
    private String description;
    private String category;
    private BigDecimal unitPrice;
    private int quantity;
    private double discount;
}
