package com.ecom.product.controller;

import com.ecom.product.dto.ProductDTO;
import com.ecom.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        logger.info("[ProductController] [getAllProducts] : success");
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping
    public ResponseEntity<ProductDTO> getProductByCode(@RequestParam String code) {
        ProductDTO product = productService.getProductByCode(code);
        logger.info("[ProductController] [getProductByCode] : success");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product = productService.addProduct(productDTO);
        logger.info("[ProductController] [createProduct] : success");
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO product = productService.updateProduct(productDTO);
        logger.info("[ProductController] [updateProduct] : success");
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteProduct(@RequestParam String code) {
        boolean res = productService.deleteProduct(code);
        logger.info("[ProductController] [deleteProduct] : success");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
