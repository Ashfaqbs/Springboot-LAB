package com.ashfaq.dev.snips.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ashfaq.dev.snips.FileRepository;
import com.ashfaq.dev.snips.model.FileEntity;

@RestController
public class FileDatabaseController {

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/uploadFileToDB")
    public String uploadFileToDB(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty";
        }

        try {
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setData(file.getBytes());

            fileRepository.save(fileEntity);
            return "File uploaded successfully to database: " + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file to database";
        }
    }
}
