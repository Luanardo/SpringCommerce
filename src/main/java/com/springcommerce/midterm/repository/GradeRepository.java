package com.springcommerce.midterm.repository;

import com.springcommerce.midterm.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {

}
