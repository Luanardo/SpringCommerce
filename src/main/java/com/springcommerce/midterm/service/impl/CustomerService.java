package com.springcommerce.midterm.service.impl;

import com.springcommerce.midterm.dto.CustomerDTO;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.repository.CustomerRepository;
import com.springcommerce.midterm.service.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Customer getOne(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public String register(CustomerDTO customerDTO) {
        Customer customer = dtoToEntity(customerDTO);
        customer.setRoles(Collections.singletonList("ROLE_CUSTOMER"));
        customerRepository.save(customer);
        return "Đăng ký thành công";
    }

    @Override
    public int sumCartQuantity(String email) {
        return customerRepository.sumCartQuantity(email);
    }

    private Customer dtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }

    public void createDefaultAdmin() {
        if (!isDefaultAdminCreated()) {
            Customer admin = new Customer();
            admin.setEmail("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Collections.singletonList("ROLE_ADMIN"));
            customerRepository.save(admin);
        }
    }

    public boolean isDefaultAdminCreated() {
        return customerRepository.existsByEmail("admin");
    }
}
