package com.springcommerce.midterm.api;

import com.springcommerce.midterm.config.CustomerDetails;
import com.springcommerce.midterm.config.JwtService;
import com.springcommerce.midterm.dto.CustomerDTO;
import com.springcommerce.midterm.dto.Token;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.service.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class CustomerApi {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@ModelAttribute CustomerDTO customerDTO) {
//        try {
//            CustomerDTO existingCustomerDTO = customerService.getOne(customerDTO.getEmail());
//            return new ResponseEntity<>("Email này đã được đăng ký", HttpStatus.BAD_REQUEST);
//        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(customerService.register(customerDTO), HttpStatus.CREATED);
//        }
//    }
//
    @PostMapping
    public ResponseEntity<Token> login(@ModelAttribute CustomerDTO customerDTO, HttpSession session) {
        try {
            Customer existingCustomer = customerService.getOne(customerDTO.getEmail());
            System.out.println(existingCustomer.getEmail());
            if (bCryptPasswordEncoder.matches(
                    customerDTO.getPassword(),
                    existingCustomer.getPassword()
            )) {
                CustomerDetails customerDetails = new CustomerDetails(existingCustomer);
                String token = jwtService.generateToken(customerDetails);
                return new ResponseEntity<>(new Token(token), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Email chưa được đăng ký");
        }
    }
}
