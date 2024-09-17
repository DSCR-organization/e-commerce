package com.ecom.product.controller;

import com.ecom.product.dto.ErrorResponseDTO;
import com.ecom.product.dto.ProductDTO;
import com.ecom.product.dto.ResponseDTO;
import com.ecom.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "REST API: Product")
@RequestMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Operation(summary = "Get All Products")
    @ApiResponse(responseCode = "200", description = "HTTP Status OK")
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        logger.info("[ProductController] [getAllProducts] : success");
        ResponseDTO<List<ProductDTO>> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(products);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get Product by Code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status NOT_FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<ProductDTO>> getProductByCode(@RequestParam String code) {
        ProductDTO product = productService.getProductByCode(code);
        logger.info("[ProductController] [getProductByCode] : success");
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(product);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Add Product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status BAD_REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<ProductDTO>> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.addProduct(productDTO);
        logger.info("[ProductController] [createProduct] : success");
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        response.setData(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update Product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status NOT_FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PutMapping
    public ResponseEntity<ResponseDTO<ProductDTO>> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO product = productService.updateProduct(productDTO);
        logger.info("[ProductController] [updateProduct] : success");
        ResponseDTO<ProductDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(product);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status NOT_FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @DeleteMapping
    public ResponseEntity<ResponseDTO<Boolean>> deleteProduct(@RequestParam String code) {
        boolean res = productService.deleteProduct(code);
        logger.info("[ProductController] [deleteProduct] : success");
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(res);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
