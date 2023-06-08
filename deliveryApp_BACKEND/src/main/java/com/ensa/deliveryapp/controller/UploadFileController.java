package com.ensa.deliveryapp.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
@Data
@CrossOrigin(origins = "http://localhost:4200")
public class UploadFileController {


        @PostMapping("/uploadFile")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

            try {
                System.out.println("i am in multipart");
                String filename = file.getOriginalFilename();
                System.out.println("file uploaded");
                file.transferTo(new File("../" + filename));
                System.out.println("file transfered");

                return ResponseEntity.ok("File uploaded successfully");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
            }
        }
    }

