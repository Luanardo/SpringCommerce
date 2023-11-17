package com.springcommerce.midterm.repository;

import com.springcommerce.midterm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

    @Query("SELECT COALESCE(SUM(cart.quantity), 0) FROM Customer c LEFT JOIN c.carts cart WHERE c.email = :email GROUP BY c.id")
    Integer sumCartQuantity(String email);

    boolean existsByEmail(String email);
}
