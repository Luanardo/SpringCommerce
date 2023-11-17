package com.springcommerce.midterm.service.impl;

import com.springcommerce.midterm.dto.GradeDTO;
import com.springcommerce.midterm.model.Grade;
import com.springcommerce.midterm.repository.GradeRepository;
import com.springcommerce.midterm.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradeService implements IGradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<GradeDTO> getAll() {
        List<Grade> grades = gradeRepository.findAll();
        List<GradeDTO> gradeDTOList = new ArrayList<>();
        for (Grade grade: grades) {
            gradeDTOList.add(entityToDTO(grade));
        }
        return gradeDTOList;
    }

    @Override
    public GradeDTO getOne(String id) {
        return entityToDTO(gradeRepository.getReferenceById(id));
    }

    private GradeDTO entityToDTO(Grade grade) {
        return new GradeDTO(
                grade.getId(),
                grade.getName(),
                grade.getRatio()
        );
    }
}
