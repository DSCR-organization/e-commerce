package com.ecom.product.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Product name can not be empty")
    private String productName;
    @NotEmpty(message = "Product description can not be empty")
    private String description;
    @NotEmpty(message = "Product category can not be empty")
    private String category;
    @NotEmpty(message = "Unit price can not be empty")
    private BigDecimal unitPrice;
    @NotEmpty(message = "quantity can not be empty")
    private int quantity;
    private double discount = 0;
}
