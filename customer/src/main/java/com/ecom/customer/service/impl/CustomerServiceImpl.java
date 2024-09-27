package com.ecom.customer.service.impl;

import com.ecom.customer.dto.CustomerDTO;
import com.ecom.customer.exception.CustomerAlreadyExistsException;
import com.ecom.customer.exception.ResourceNotFoundException;
import com.ecom.customer.mapper.CustomerMapper;
import com.ecom.customer.model.Customer;
import com.ecom.customer.repo.CustomerRepository;
import com.ecom.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
       Optional<Customer> checkCustomer = customerRepository.findByCustomerEmail(customerDTO.getCustomerEmail());
        if(checkCustomer.isPresent()){
            logger.error("[CustomerServiceImpl] [createCustomer] : failed - customer email already exists");
            throw new CustomerAlreadyExistsException("Customer already exists with email: " + customerDTO.getCustomerEmail());
        }
        Customer customer = customerRepository.save(CustomerMapper.mapToCustomer(customerDTO,new Customer()));
        logger.info("[CustomerServiceImpl] [createCustomer] : success");
        return CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
    }
    @Override
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Couldn't find customer id: " + id));
        logger.info("[CustomerServiceImpl] [getCustomerId] : success");
        return CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        customerRepository.findById(customerDTO.getId()).orElseThrow(
                ()->new ResourceNotFoundException("Couldn't find customer id: " + customerDTO.getId())
        );
        customerRepository.save(CustomerMapper.mapToCustomer(customerDTO,new Customer()));
        logger.info("[CustomerServiceImpl] [updateCustomer] : success");
        return getCustomerById(customerDTO.getId());
    }

    @Override
    public boolean deleteCustomer(int id) {
        customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Couldn't find customer by id: "+ id));
        customerRepository.deleteById(id);
        logger.info("[CustomerServiceImpl] [deleteCustomer] : success");
        return true;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        if(!customerList.isEmpty()){
            customerList.forEach(customer -> customerDTOList.add(CustomerMapper.mapToCustomerDTO(customer,new CustomerDTO())));
        }
        logger.info("[CustomerServiceImpl] [getAllCustomers] : success");
        return customerDTOList;
    }


}
