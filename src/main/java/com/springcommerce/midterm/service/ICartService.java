package com.springcommerce.midterm.service;

import com.springcommerce.midterm.dto.CartReqDTO;
import com.springcommerce.midterm.dto.CartResDTO;

import java.util.List;

public interface ICartService {
    int save(CartReqDTO cartReqDTO);
    int remove(String customerEmail, Long productId);
    int updateQuantity(CartReqDTO cartReqDTO);
    CartReqDTO getOne(String customerEmail, Long productId);
    List<CartResDTO> getAllByCustomer(String customerEmail);
}
