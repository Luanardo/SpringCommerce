package com.springcommerce.midterm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String grade;
    private String boxImage;
    private String frontImage;
    private List<String> imagePaths;
    private String description;
    private String series;
    private String equipments;
    private double price;
    private int remainingQuantity;
}
