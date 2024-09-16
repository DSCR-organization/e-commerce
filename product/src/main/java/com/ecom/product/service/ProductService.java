package com.ecom.product.service;

import com.ecom.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductByCode(String code);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(ProductDTO productDTO);
    boolean deleteProduct(String code);
}
