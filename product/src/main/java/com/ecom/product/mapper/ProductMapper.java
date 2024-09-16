package com.ecom.product.mapper;

import com.ecom.product.dto.ProductDTO;
import com.ecom.product.model.Product;

public class ProductMapper {

    public static ProductDTO mapToProductDTO(Product product, ProductDTO productDTO) {
        productDTO.setCode(product.getCode());
        productDTO.setProductName(product.getProductName());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(product.getCategory());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDiscount(product.getDiscount());
        return productDTO;
    }

    public static Product mapToProduct(ProductDTO productDTO, Product product) {
        product.setCode(productDTO.getCode());
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(productDTO.getCategory());
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDiscount(productDTO.getDiscount());
        return product;
    }

}
