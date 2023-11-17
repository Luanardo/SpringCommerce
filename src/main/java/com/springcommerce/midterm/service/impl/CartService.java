package com.springcommerce.midterm.service.impl;

import com.springcommerce.midterm.dto.CartReqDTO;
import com.springcommerce.midterm.dto.CartResDTO;
import com.springcommerce.midterm.model.Cart;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.model.Product;
import com.springcommerce.midterm.repository.CartRepository;
import com.springcommerce.midterm.repository.CustomerRepository;
import com.springcommerce.midterm.repository.ProductRepository;
import com.springcommerce.midterm.service.ICartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

//    @Override
//    public boolean exists(String customerEmail, Long productId) {
//        Customer customer = customerRepository.findByEmail(customerEmail);
//        Product product = productRepository.getReferenceById(productId);
//        return cartRepository.existsByCustomerAndProduct(customer, product);
//    }

    @Override
    public int save(CartReqDTO cartReqDTO) {
        Cart cart = reqDtoToEntity(cartReqDTO);
        Cart savedCart = cartRepository.save(cart);
        return savedCart.getQuantity();
    }

    @Override
    public int remove(String customerEmail, Long productId) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        Product product = productRepository.getReferenceById(productId);
        Cart cart = cartRepository.findByCustomerAndProduct(customer, product);
        int quantity = cart.getQuantity();
        cartRepository.delete(cart);
        return quantity;
    }

    @Override
    public int updateQuantity(CartReqDTO cartReqDTO) {
        return 0;
    }

    @Override
    public CartReqDTO getOne(String customerEmail, Long productId) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        Product product = productRepository.getReferenceById(productId);
        Cart cart = cartRepository.findByCustomerAndProduct(customer, product);
        if (cart == null) {
            throw new EntityNotFoundException();
        } else {
            return entityToReqDTO(cart);
        }
    }

    @Override
    public List<CartResDTO> getAllByCustomer(String customerEmail) {
        Customer customer = customerRepository.findByEmail(customerEmail);
        List<Cart> carts = cartRepository.findAllByCustomer(customer);
        List<CartResDTO> cartResDTOList = new ArrayList<>();
        for (Cart cart: carts) {
            cartResDTOList.add(entityToResDTO(cart));
        }
        return cartResDTOList;
    }

    private CartResDTO entityToResDTO(Cart cart) {
        return new CartResDTO(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getProduct().getId(),
                cart.getProduct().getBoxImage(),
                cart.getProduct().getName(),
                cart.getProduct().getPrice(),
                cart.getQuantity()
        );
    }

    private CartReqDTO entityToReqDTO(Cart cart) {
        return new CartReqDTO(
                cart.getId(),
                cart.getCustomer().getEmail(),
                cart.getProduct().getId(),
                cart.getQuantity()
        );
    }

    private Cart reqDtoToEntity(CartReqDTO cartReqDTO) {
        Customer customer = customerRepository.findByEmail(cartReqDTO.getCustomer());
        Product product = productRepository.getReferenceById(cartReqDTO.getProduct());
        Cart cart = new Cart();
        cart.setId(cartReqDTO.getId());
        cart.setCustomer(customer);
        cart.setProduct(product);
        cart.setQuantity(cartReqDTO.getQuantity());
        return cart;
    }
}
