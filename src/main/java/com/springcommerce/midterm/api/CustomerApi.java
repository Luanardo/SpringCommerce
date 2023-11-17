package com.springcommerce.midterm.api;

import com.springcommerce.midterm.dto.CustomerDTO;
import com.springcommerce.midterm.service.ICustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerApi {
    @Autowired
    private ICustomerService customerService;

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
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@ModelAttribute CustomerDTO customerDTO, HttpSession session) {
//        try {
//            CustomerDTO existingCustomerDTO = customerService.getOne(customerDTO.getEmail());
//            if (existingCustomerDTO.getPassword().equals(customerDTO.getPassword())) {
//                session.setAttribute("email", existingCustomerDTO.getEmail());
//                return new ResponseEntity<>("Đăng nhập thành công", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Mật khẩu sai", HttpStatus.BAD_REQUEST);
//            }
//        } catch (EntityNotFoundException e) {
//            throw new EntityNotFoundException("Email chưa được đăng ký");
//        }
//    }
}
