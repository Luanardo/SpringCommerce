package com.springcommerce.midterm.service.impl;

import com.springcommerce.midterm.dto.ProductDTO;
import com.springcommerce.midterm.model.Grade;
import com.springcommerce.midterm.model.Product;
import com.springcommerce.midterm.repository.GradeRepository;
import com.springcommerce.midterm.repository.ProductRepository;
import com.springcommerce.midterm.service.IProductService;
import com.springcommerce.midterm.util.FileUploadUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = dtoToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return entityToDTO(savedProduct);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) throws IOException {
        try {
            Product existingProduct = productRepository.getReferenceById(id);
            deleteImagesFromDirectory(existingProduct.getImagePaths(), existingProduct.getBoxImage(), existingProduct.getFrontImage());
            Grade grade = gradeRepository.getReferenceById(productDTO.getGrade());
            existingProduct.setName(productDTO.getName());
            existingProduct.setGrade(grade);
            existingProduct.setBoxImage(productDTO.getBoxImage());
            existingProduct.setFrontImage(productDTO.getFrontImage());
            existingProduct.setImagePaths(productDTO.getImagePaths());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setSeries(productDTO.getSeries());
            existingProduct.setEquipments(productDTO.getEquipments());
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setRemainingQuantity(productDTO.getRemainingQuantity());
            Product updatedProduct = productRepository.save(existingProduct);
            return entityToDTO(updatedProduct);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Không tìm thấy sản phẩm có id = " + id + ".");
        }
    }

    @Override
    public void delete(Long id) throws IOException {
        try {
            Product product = productRepository.getReferenceById(id);
            deleteImagesFromDirectory(product.getImagePaths(), product.getBoxImage(), product.getFrontImage());
            productRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Không tìm thấy sản phẩm có id = " + id + ".");
        }
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public ProductDTO getOne(Long id) {
        return entityToDTO(productRepository.getReferenceById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<ProductDTO> getAllByNameLike(String name) {
        List<Product> products = productRepository.findAllByNameLike("%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllByGradeAndNameLike(String grade, String name) {
        Grade gradeEntity = gradeRepository.getReferenceById(grade);
        List<Product> products = productRepository.findAllByGradeAndNameLike(gradeEntity, "%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllByPriceAndNameLike(double start, double end, String name) {
        List<Product> products = productRepository.findAllByPriceBetweenAndNameLike(start, end, "%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllByPriceAndNameLike(double price, String name) {
        List<Product> products = productRepository.findAllByPriceGreaterThanAndNameLike(price, "%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllByFilter(String grade, double start, double end, String name) {
        Grade gradeEntity = gradeRepository.getReferenceById(grade);
        List<Product> products = productRepository.findAllByGradeAndPriceBetweenAndNameLike(gradeEntity, start, end, "%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllByFilter(String grade, double price, String name) {
        Grade gradeEntity = gradeRepository.getReferenceById(grade);
        List<Product> products = productRepository.findAllByGradeAndPriceGreaterThanAndNameLike(gradeEntity, price, "%"+name+"%");
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product: products) {
            productDTOList.add(entityToDTO(product));
        }
        return productDTOList;
    }

    private ProductDTO entityToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getGrade().getId(),
                product.getBoxImage(),
                product.getFrontImage(),
                product.getImagePaths(),
                product.getDescription(),
                product.getSeries(),
                product.getEquipments(),
                product.getPrice(),
                product.getRemainingQuantity()
        );
    }

    private Product dtoToEntity(ProductDTO productDTO) {
        Product product = new Product();
        Grade grade = gradeRepository.getReferenceById(productDTO.getGrade());
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setGrade(grade);
        product.setBoxImage(productDTO.getBoxImage());
        product.setFrontImage(productDTO.getFrontImage());
        product.setImagePaths(productDTO.getImagePaths());
        product.setDescription(productDTO.getDescription());
        product.setSeries(productDTO.getSeries());
        product.setEquipments(productDTO.getEquipments());
        product.setPrice(productDTO.getPrice());
        product.setRemainingQuantity(productDTO.getRemainingQuantity());
        return product;
    }

    private void deleteImagesFromDirectory(List<String> imagePaths, String boxImage, String frontImage) throws IOException {
        String uploadDir = "public/productImages";
        if (boxImage != null) {
            FileUploadUtil.deleteFile(uploadDir, boxImage);
        }
        if (frontImage != null) {
            FileUploadUtil.deleteFile(uploadDir, frontImage);
        }
        if (imagePaths != null) {
            for (String imagePath : imagePaths) {
                FileUploadUtil.deleteFile(uploadDir, imagePath);
            }
        }
    }
}
