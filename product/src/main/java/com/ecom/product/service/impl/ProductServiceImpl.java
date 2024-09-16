package com.ecom.product.service.impl;

import com.ecom.product.dto.ProductDTO;
import com.ecom.product.exception.ProductAlreadyExistsException;
import com.ecom.product.exception.ResourceNotFoundException;
import com.ecom.product.mapper.ProductMapper;
import com.ecom.product.model.Product;
import com.ecom.product.repo.ProductRepository;
import com.ecom.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (!productList.isEmpty()) {
            productList.forEach(product -> productDTOList.add(ProductMapper.mapToProductDTO(product, new ProductDTO())));
        }
        logger.info("[ProductServiceImpl] [getAllProducts] : success");
        return productDTOList;
    }

    @Override
    public ProductDTO getProductByCode(String code) {
        Product product = productRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + code)
        );
        logger.info("[ProductServiceImpl] [getProductByCode] : success");
        return ProductMapper.mapToProductDTO(product, new ProductDTO());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Optional<Product> checkProduct = productRepository.findByProductName(productDTO.getProductName());
        if (checkProduct.isPresent()) {
            logger.error("[ProductServiceImpl] [addProduct] : failed - product already exists");
            throw new ProductAlreadyExistsException("Product already exists with code: " + productDTO.getCode());
        }
        Product product = productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        logger.info("[ProductServiceImpl] [addProduct] : success");
        return ProductMapper.mapToProductDTO(product, new ProductDTO());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepository.findById(productDTO.getCode()).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + productDTO.getCode())
        );
        productRepository.save(ProductMapper.mapToProduct(productDTO, new Product()));
        logger.info("[ProductServiceImpl] [updateProduct] : success");
        return getProductByCode(productDTO.getCode());
    }

    @Override
    public boolean deleteProduct(String code) {
        productRepository.findById(code).orElseThrow(
                () -> new ResourceNotFoundException("Couldn't find product by code: " + code)
        );
        productRepository.deleteById(code);
        logger.info("[ProductServiceImpl] [deleteProduct] : success");
        return true;
    }
}
