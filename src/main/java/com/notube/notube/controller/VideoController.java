package com.notube.notube.controller;


import com.notube.notube.model.Video;
import com.notube.notube.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notube/videos")
@RequiredArgsConstructor
public class VideoController {

    public final VideoRepository repo;


    //upload/create video
    @PostMapping
    public ResponseEntity<Video> uploadVideo(@RequestBody Video video){
        Video saved = repo.save(video);
        return ResponseEntity.ok(saved);
    }

    //get all vids
    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id){
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id){
        if(!repo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repo.deleteById((id));
        return ResponseEntity.noContent().build();
    }


}
