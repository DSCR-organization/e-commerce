package com.ecom.product.service.impl;

import com.ecom.product.dto.ProductDTO;
import com.ecom.product.exception.ProductAlreadyExistsException;
import com.ecom.product.exception.ResourceNotFoundException;
import com.ecom.product.mapper.ProductMapper;
import com.ecom.product.model.Product;
import com.ecom.product.repo.ProductRepository;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (!productList.isEmpty()) {
            productList.forEach(product -> productDTOList.add(ProductMapper.mapToProductDTO(product, new ProductDTO())));
        }
        return productDTOList;
    }

    @Override
    public ProductDTO getProductByCode(String code) {
        Product product = productRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + code)
        );
        return ProductMapper.mapToProductDTO(product, new ProductDTO());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Optional<Product> checkProduct = productRepository.findById(productDTO.getCode());
        if (checkProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product already exists with code: " + productDTO.getCode());
        }
        productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        return getProductByCode(productDTO.getCode());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepository.findById(productDTO.getCode()).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + productDTO.getCode())
        );
        productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        return getProductByCode(productDTO.getCode());
    }

    @Override
    public boolean deleteProduct(String code) {
        productRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + code)
        );
        productRepository.deleteById(code);
        return true;
    }
}
