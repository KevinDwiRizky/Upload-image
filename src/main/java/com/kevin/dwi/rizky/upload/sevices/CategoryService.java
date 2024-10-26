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
            File destinationFile = new File(uploadDir + fileName);
            foto.transferTo(destinationFile);
            category.setFoto(fileName);
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
