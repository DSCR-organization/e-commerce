package com.ecom.customer.service;

import com.ecom.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(int id);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    boolean deleteCustomer(int id);
    List<CustomerDTO> getAllCustomers();

}
