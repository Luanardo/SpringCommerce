package com.springcommerce.midterm.repository;

import com.springcommerce.midterm.model.Cart;
import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByCustomerAndProduct(Customer customer, Product product);
    Cart findByCustomerAndProduct(Customer customer, Product product);
    List<Cart> findAllByCustomer(Customer customer);
}
