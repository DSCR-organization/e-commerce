package com.ecom.product.service.impl;

import com.ecom.product.dto.ProductDTO;
import com.ecom.product.mapper.ProductMapper;
import com.ecom.product.model.Product;
import com.ecom.product.repo.ProductRepository;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.forEach(product -> productDTOList.add(ProductMapper.mapToProductDTO(product, new ProductDTO())));
        return productDTOList;
    }

    @Override
    public ProductDTO getProductByCode(String code) {
        Product product = productRepository.findById(code).orElseThrow(
                () -> new RuntimeException("Couldn't find product by code: " + code)
        );
        return ProductMapper.mapToProductDTO(product, new ProductDTO());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        return getProductByCode(productDTO.getCode());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        return getProductByCode(productDTO.getCode());
    }

    @Override
    public void deleteProduct(String code) {
        productRepository.findById(code).orElseThrow(
                () -> new RuntimeException("Couldn't find product by code: " + code)
        );
        productRepository.deleteById(code);
    }
}
