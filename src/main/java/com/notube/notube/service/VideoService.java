package com.notube.notube.service;


import com.notube.notube.model.Video;
import com.notube.notube.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoRepository repo;

    public Video upload(Video video) {
        return repo.save(video);
    }

    public List<Video> findAll(){
        return repo.findAll();
    }

    public Video findById(Long id){
        return repo.findById(id).orElseThrow();
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
