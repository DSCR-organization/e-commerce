package com.ecom.customer.controller;

import com.ecom.customer.dto.CustomerDTO;
import com.ecom.customer.dto.ErrorResponseDTO;
import com.ecom.customer.dto.ResponseDTO;
import com.ecom.customer.service.CustomerService;
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
@Tag(name = "REST API: Customer")
@RequestMapping(path = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;


    @Operation(summary = "Create Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500",description = "HTTP Status BAD_REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<ResponseDTO<CustomerDTO>> createCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        logger.info("[CustomerController] [createCustomer] : success");
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage(HttpStatus.CREATED.getReasonPhrase());
        response.setData(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get Customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "HTTP Status NOT_FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<ResponseDTO<CustomerDTO>> getCustomerId(@RequestParam int id){
        CustomerDTO customer = customerService.getCustomerById(id);
        logger.info("[CustomerController] [getCustomerId] : success");
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(customer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Update Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "HTTP Status NOT_FOUND",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @PutMapping
    public ResponseEntity<ResponseDTO<CustomerDTO>> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        CustomerDTO customer = customerService.updateCustomer(customerDTO);
        logger.info("[CustomerController] [updateCustomer] : success");
        ResponseDTO<CustomerDTO> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(customer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete Customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500",description = "HTTP Status NOT_FOUND",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })

    @DeleteMapping
    public ResponseEntity<ResponseDTO<Boolean>> deleteCustomer(@RequestParam int id){
        boolean res = customerService.deleteCustomer(id);
        logger.info("[CustomerController] [deleteCustomer] : success");
        ResponseDTO<Boolean> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(res);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get All Customers")
    @ApiResponse(responseCode = "200",description = "HTTP Status OK")
    @GetMapping("/all")
    public ResponseEntity<ResponseDTO<List<CustomerDTO>>> getAllCustomers(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        logger.info("[CustomerController] [getAllCustomers] : success");
        ResponseDTO<List<CustomerDTO>> response = new ResponseDTO<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
        response.setData(customers);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
