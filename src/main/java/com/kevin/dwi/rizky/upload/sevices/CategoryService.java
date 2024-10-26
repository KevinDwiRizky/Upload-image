package com.kevin.dwi.rizky.upload.sevices;

import com.kevin.dwi.rizky.upload.entity.Category;
import com.kevin.dwi.rizky.upload.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category saveCategory(Category category, MultipartFile foto) throws IOException {
        if (foto != null && !foto.isEmpty()) {
            String fileName = foto.getOriginalFilename();
            File uploadDirectory = new File(uploadDir);

            // Cek apakah direktori `uploads` ada, jika tidak, buat baru
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs(); // Membuat direktori beserta subdirektori yang diperlukan
            }

            // Tentukan lokasi file tujuan
            File destinationFile = new File(uploadDirectory, fileName);
            foto.transferTo(destinationFile); // Simpan file di lokasi tujuan
            category.setFoto(fileName); // Simpan nama file ke field `foto` dalam database
        }
        return categoryRepository.save(category);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
