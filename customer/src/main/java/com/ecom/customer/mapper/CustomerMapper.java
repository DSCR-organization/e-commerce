package com.ecom.customer.mapper;

import com.ecom.customer.dto.CustomerDTO;
import com.ecom.customer.model.Customer;

public class CustomerMapper {

    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setId(customer.getId());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO,Customer customer){
        customer.setId(customerDTO.getId());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;

    }
}
