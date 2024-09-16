package com.ecom.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull(message = "Unit price can not be null")
    private long unitPrice;
    @NotNull(message = "Quantity can not be null")
    private int quantity;
    private double discount = 0;
}
