package com.ashfaq.dev.snips;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.dev.snips.model.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}

