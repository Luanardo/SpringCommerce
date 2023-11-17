package com.springcommerce.midterm.service.impl;

import com.springcommerce.midterm.dto.OrderDTO;
import com.springcommerce.midterm.dto.OrderDetailDTO;
import com.springcommerce.midterm.dto.OrderDetailResDTO;
import com.springcommerce.midterm.dto.OrderResDTO;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.model.Order;
import com.springcommerce.midterm.model.OrderDetail;
import com.springcommerce.midterm.model.Product;
import com.springcommerce.midterm.repository.CustomerRepository;
import com.springcommerce.midterm.repository.OrderDetailRepository;
import com.springcommerce.midterm.repository.OrderRepository;
import com.springcommerce.midterm.repository.ProductRepository;
import com.springcommerce.midterm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = dtoToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return entityToDTO(savedOrder);
    }

    @Override
    public void saveDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = detailDtoToEntity(orderDetailDTO);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderResDTO> getAll(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        List<OrderResDTO> orderDTOList = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByCustomer(customer, sort);
        for (Order order: orders) {
            orderDTOList.add(entityToResDTO(order));
        }
        return orderDTOList;
    }

    @Override
    public void changeStatus(Long orderId, String status) {
        Order order = orderRepository.getReferenceById(orderId);
        order.setStatus(status);
        if (status.equals("Đã hủy")) {
            for (OrderDetail orderDetail: order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                int remainingQuantity = product.getRemainingQuantity();
                product.setRemainingQuantity(remainingQuantity + orderDetail.getQuantity());
            }
        }
        orderRepository.save(order);
    }

    private Order dtoToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        Customer customer = customerRepository.findByEmail(orderDTO.getCustomer());
        order.setCustomer(customer);
        order.setPhoneNumber(orderDTO.getPhoneNumber());
        order.setAddress(orderDTO.getAddress());
        order.setCreatedAt(orderDTO.getCreatedAt());
        order.setTotal(orderDTO.getTotal());
        order.setStatus(orderDTO.getStatus());
        return order;
    }

    private OrderDTO entityToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomer().getEmail(),
                order.getPhoneNumber(),
                order.getAddress(),
                order.getCreatedAt(),
                order.getTotal(),
                order.getStatus()
        );
    }

    private OrderResDTO entityToResDTO(Order order) {
        List<OrderDetailResDTO> orderDetailResDTOList = new ArrayList<>();
        for (OrderDetail orderDetail: order.getOrderDetails()) {
            OrderDetailResDTO orderDetailResDTO = new OrderDetailResDTO(
                    orderDetail.getId(),
                    orderDetail.getOrder().getId(),
                    orderDetail.getProduct().getName(),
                    orderDetail.getProduct().getPrice(),
                    orderDetail.getQuantity()
            );
            orderDetailResDTOList.add(orderDetailResDTO);
        }
        return new OrderResDTO(
                order.getId(),
                order.getCustomer().getEmail(),
                order.getPhoneNumber(),
                order.getAddress(),
                order.getCreatedAt(),
                order.getTotal(),
                order.getStatus(),
                orderDetailResDTOList
        );
    }

    private OrderDetail detailDtoToEntity(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
        Order order = orderRepository.getReferenceById(orderDetailDTO.getOrder());
        orderDetail.setOrder(order);
        Product product = productRepository.getReferenceById(orderDetailDTO.getProduct());
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDTO.getQuantity());
        return orderDetail;
    }
}
