package com.springcommerce.midterm.repository;

import com.springcommerce.midterm.model.Customer;
import com.springcommerce.midterm.model.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(Customer customer, Sort sort);
}
