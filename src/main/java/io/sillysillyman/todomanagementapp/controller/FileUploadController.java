package io.sillysillyman.todomanagementapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/v1.0/files")
public class FileUploadController {

    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uploadFilePath = UPLOAD_DIR + fileName;

        String contentType = file.getContentType();
        if (contentType == null ||
            ((!contentType.equals("image/jpeg") && !contentType.equals("image/png")
                && !contentType.equals("image/jpg")))) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body("Only JPG, JPEG, PNG files are allowed");
        }

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            file.transferTo(new File(uploadFilePath));
            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload file: " + fileName,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(UPLOAD_DIR + fileName));
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + fileName + "\"")
                .body(fileBytes);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
