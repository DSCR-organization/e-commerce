package com.ecom.customer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private int id;
    @NotEmpty(message = "Customer name can not be empty")
    private String name;
    @NotEmpty(message = "Customer address can not be empty")
    private String address;
    @NotEmpty(message = "Customer mobile number can not be empty")
    private String mobileNumber;
    @NotEmpty(message = "Customer email can not be empty")
    private String customerEmail;
}
