package com.springcommerce.midterm.service;

import com.springcommerce.midterm.dto.GradeDTO;

import java.util.List;

public interface IGradeService {
    List<GradeDTO> getAll();
    GradeDTO getOne(String id);
}
