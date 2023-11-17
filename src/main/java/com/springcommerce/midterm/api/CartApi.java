package com.springcommerce.midterm.api;

import com.springcommerce.midterm.dto.CartReqDTO;
import com.springcommerce.midterm.service.ICartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartApi {
    @Autowired
    private ICartService cartService;

    @PostMapping
    public ResponseEntity<Integer> addToCart(@ModelAttribute CartReqDTO cartReqDTO) {
        try {
            CartReqDTO existingCartReqDTO = cartService.getOne(cartReqDTO.getCustomer(), cartReqDTO.getProduct());
            int newQuantity = existingCartReqDTO.getQuantity() + cartReqDTO.getQuantity();
            existingCartReqDTO.setQuantity(newQuantity);
            cartService.save(existingCartReqDTO);
            return new ResponseEntity<>(cartReqDTO.getQuantity(), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(cartService.save(cartReqDTO), HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<Integer> updateQuantity(@ModelAttribute CartReqDTO cartReqDTO) {
        CartReqDTO existingCartReqDTO = cartService.getOne(cartReqDTO.getCustomer(), cartReqDTO.getProduct());
        int newQuantity = existingCartReqDTO.getQuantity() + cartReqDTO.getQuantity();
        existingCartReqDTO.setQuantity(newQuantity);
        cartService.save(existingCartReqDTO);
        return new ResponseEntity<>(cartReqDTO.getQuantity(), HttpStatus.OK);
    }

    @DeleteMapping("/{customerEmail}/{productId}")
    public ResponseEntity<Integer> remove(
            @PathVariable("customerEmail") String customerEmail,
            @PathVariable("productId") Long productId
    ) {
        return new ResponseEntity<>(cartService.remove(customerEmail, productId), HttpStatus.OK);
    }
}
