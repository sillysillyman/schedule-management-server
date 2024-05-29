package io.sillysillyman.todomanagementapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
            file.transferTo(new File(uploadFilePath));
            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Could not upload file: " + fileName,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
