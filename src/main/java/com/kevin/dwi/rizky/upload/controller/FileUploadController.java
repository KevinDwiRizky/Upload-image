package com.kevin.dwi.rizky.upload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("namakelas") String namakelas) {
        try {
            // Simpan file ke direktori upload
            File destinationFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(destinationFile);

            // Kembalikan respons dengan informasi file dan namakelas
            String responseMessage = String.format("File '%s' berhasil di-upload untuk kelas: %s",
                    file.getOriginalFilename(), namakelas);
            return ResponseEntity.ok(responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gagal upload file");
        }
    }
}
