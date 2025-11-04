package com.notube.notube.repository;

import com.notube.notube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}

/*
* by doing this we don’t need to write SQL or JPQL for basic operations.
Spring automatically implements these methods for you:
videoRepository.findAll();          // Get all videos
videoRepository.findById(1L);       // Get video with id = 1
videoRepository.save(video);        // Insert or update
videoRepository.delete(video);      // Delete a record
videoRepository.count();            // Count all videos
*
*
*
* */