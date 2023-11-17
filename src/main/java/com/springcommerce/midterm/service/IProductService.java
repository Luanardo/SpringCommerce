package com.springcommerce.midterm.service;

import com.springcommerce.midterm.dto.ProductDTO;
import com.springcommerce.midterm.model.Product;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO) throws IOException;
    void delete(Long id) throws IOException;
    List<ProductDTO> getAll();
    ProductDTO getOne(Long id);
    boolean existsById(Long id);
    List<ProductDTO> getAllByNameLike(String name);
    List<ProductDTO> getAllByGradeAndNameLike(String grade, String name);
    List<ProductDTO> getAllByPriceAndNameLike(double start, double end, String name);
    List<ProductDTO> getAllByPriceAndNameLike(double price, String name);
    List<ProductDTO> getAllByFilter(String grade, double start, double end, String name);
    List<ProductDTO> getAllByFilter(String grade, double price, String name);
}
