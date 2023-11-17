package com.springcommerce.midterm.service;

import com.springcommerce.midterm.dto.CustomerDTO;
import com.springcommerce.midterm.model.Customer;

public interface ICustomerService {
    Customer getOne(String email);
    String register(CustomerDTO customerDTO);
    int sumCartQuantity(String email);
}
