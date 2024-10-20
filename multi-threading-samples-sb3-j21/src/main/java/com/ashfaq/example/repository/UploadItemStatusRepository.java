package com.ashfaq.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.entity.UploadItemStatus;

public interface UploadItemStatusRepository extends JpaRepository<UploadItemStatus, Long> {
}
