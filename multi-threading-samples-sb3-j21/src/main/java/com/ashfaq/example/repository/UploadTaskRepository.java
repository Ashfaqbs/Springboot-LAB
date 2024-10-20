package com.ashfaq.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.entity.UploadTask;

public interface UploadTaskRepository extends JpaRepository<UploadTask, Long> {
}
