package com.springcommerce.midterm.api;

import com.springcommerce.midterm.dto.ProductDTO;
import com.springcommerce.midterm.exception.BadRequestException;
import com.springcommerce.midterm.service.IProductService;
import com.springcommerce.midterm.util.FileUploadUtil;
import com.springcommerce.midterm.util.ImageUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApi {
    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @ModelAttribute ProductDTO productDTO,
            @RequestParam("boxImg") MultipartFile boxImg,
            @RequestParam("frontImg") MultipartFile frontImg,
            @RequestParam("images") List<MultipartFile> images
    ) throws IOException {
        validateProductDTO(productDTO, images, boxImg, frontImg);

        // Xử lý hình ảnh
        String boxImage = saveImage(boxImg);
        String frontImage = saveImage(frontImg);
        List<String> savedImagePaths = saveImages(images);

        // Lưu vào cơ sở dữ liệu
        productDTO.setBoxImage(boxImage);
        productDTO.setFrontImage(frontImage);
        productDTO.setImagePaths(savedImagePaths);

        // Lưu vào cơ sở dữ liệu
        return new ResponseEntity<>(productService.save(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProductDTO>> getAllFilterProducts(
            @RequestParam("grade") String grade,
            @RequestParam("price") String price,
            @RequestParam("name") String name
    ) {
        List<ProductDTO> productDTOList;
        if (grade.equals("All") && price.equals("All")) {
            productDTOList = productService.getAllByNameLike(name);
        } else if (!grade.equals("All") && price.equals("All")) {
            productDTOList = productService.getAllByGradeAndNameLike(grade, name);
        } else if (grade.equals("All")) {
            String[] priceBetween = price.split("-");
            if (priceBetween.length > 2) {
                throw new BadRequestException("Giá sản phẩm sai format");
            } else if (priceBetween.length == 2) {
                double start = Double.parseDouble(priceBetween[0]);
                double end = Double.parseDouble(priceBetween[1]);
                productDTOList = productService.getAllByPriceAndNameLike(start, end, name);
            } else {
                double start = Double.parseDouble(priceBetween[0]);
                productDTOList = productService.getAllByPriceAndNameLike(start, name);
            }
        } else {
            String[] priceBetween = price.split("-");
            if (priceBetween.length > 2) {
                throw new BadRequestException("Giá sản phẩm sai format");
            } else if (priceBetween.length == 2) {
                double start = Double.parseDouble(priceBetween[0]);
                double end = Double.parseDouble(priceBetween[1]);
                productDTOList = productService.getAllByFilter(grade, start, end, name);
            } else {
                double start = Double.parseDouble(priceBetween[0]);
                productDTOList = productService.getAllByFilter(grade, start, name);
            }

        }
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(productService.getOne(id), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Không tìm thấy sản phẩm có id = "+id+".");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable("id") Long id,
            @ModelAttribute ProductDTO productDTO,
            @RequestParam("boxImg") MultipartFile boxImg,
            @RequestParam("frontImg") MultipartFile frontImg,
            @RequestParam("images") List<MultipartFile> images
    ) throws IOException {
        validateProductDTO(productDTO, images, boxImg, frontImg);

        // Xử lý hình ảnh
        String boxImage = saveImage(boxImg);
        String frontImage = saveImage(frontImg);
        List<String> savedImagePaths = saveImages(images);

        // Lưu vào cơ sở dữ liệu
        productDTO.setBoxImage(boxImage);
        productDTO.setFrontImage(frontImage);
        productDTO.setImagePaths(savedImagePaths);

        // Lưu vào cơ sở dữ liệu
        return new ResponseEntity<>(productService.update(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) throws IOException {
        productService.delete(id);
        return new ResponseEntity<>("Xóa sản phẩm thành công.", HttpStatus.OK);
    }

    private String saveImage(MultipartFile image) throws IOException {
        String uploadDir = "public/productImages";
        String imageName = ImageUtil.generateUniqueFileName()+".jpg";
        FileUploadUtil.saveFile(uploadDir, imageName, image);
        return imageName;
    }

    private List<String> saveImages(List<MultipartFile> images) throws IOException {
        List<String> savedImagePaths = new ArrayList<>();
        String uploadDir = "public/productImages";
        String name = ImageUtil.generateUniqueFileName();
        for (int i = 0; i < images.size(); i++) {
            // Lưu hình vào thư mục public
            String imageName = name+"_"+i+".jpg";
            FileUploadUtil.saveFile(uploadDir, imageName, images.get(i));
            // Thêm đường dẫn hình vào danh sách
            savedImagePaths.add(imageName);
        }

        return savedImagePaths;
    }

    private void validateProductDTO(ProductDTO productDTO, List<MultipartFile> images, MultipartFile boxImg, MultipartFile frontImg) {
        if (productDTO.getName() == null || productDTO.getGrade() == null || boxImg == null || frontImg == null || images == null
                || productDTO.getDescription() == null || productDTO.getSeries() == null
                || productDTO.getEquipments() == null) {
            throw new BadRequestException("Các trường yêu cầu chưa được nhập đủ.");
        }
        // Add additional validations as needed
    }
}
