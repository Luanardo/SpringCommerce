package com.springcommerce.midterm.repository;

import com.springcommerce.midterm.model.Grade;
import com.springcommerce.midterm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameLike(String name);
    List<Product> findAllByGradeAndNameLike(Grade grade, String name);
    List<Product> findAllByPriceBetweenAndNameLike(double min, double max, String name);
    List<Product> findAllByPriceGreaterThanAndNameLike(double price, String Name);
    List<Product> findAllByGradeAndPriceBetweenAndNameLike(Grade grade, double min, double max, String name);
    List<Product> findAllByGradeAndPriceGreaterThanAndNameLike(Grade grade, double price, String name);
}
