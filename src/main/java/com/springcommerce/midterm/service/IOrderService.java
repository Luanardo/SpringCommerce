package com.springcommerce.midterm.service;

import com.springcommerce.midterm.dto.OrderDTO;
import com.springcommerce.midterm.dto.OrderDetailDTO;
import com.springcommerce.midterm.dto.OrderResDTO;

import java.util.List;

public interface IOrderService {
    OrderDTO save(OrderDTO orderDTO);
    void saveDetail(OrderDetailDTO orderDetailDTO);
    List<OrderResDTO> getAll(String customerEmail);
    void changeStatus(Long orderId, String status);
}
