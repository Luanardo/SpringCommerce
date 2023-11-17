package com.springcommerce.midterm.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile image) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static void deleteFile(String uploadDir, String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir, fileName);

        try {
            // Xóa tệp ảnh từ thư mục nếu nó tồn tại
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new IOException("Could not delete image file: " + fileName, e);
        }
    }
}