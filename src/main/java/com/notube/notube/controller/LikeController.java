package com.notube.notube.controller;

import com.notube.notube.model.Like;
import com.notube.notube.model.Video;
import com.notube.notube.repository.LikeRepository;
import com.notube.notube.repository.VideoRepository;
import com.notube.notube.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("notube/videos/{videoId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;
    private final LikeRepository likeRepo;
    private final VideoRepository vidRepo;

    //add like or dislike to a video
    @PostMapping
    public ResponseEntity<?> reactToVideo(@PathVariable Long videoId,
                                          @RequestParam boolean liked){
        Optional<Video> videoOpt = vidRepo.findById(videoId);
        if(videoOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Like like = Like.builder().video(videoOpt.get())
                .liked(liked).build();

        likeService.add(like);

        return ResponseEntity.ok("Reacted");
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<?> removeReact(@PathVariable Long likedId){
        likeService.removeLike(likedId);
        return ResponseEntity.noContent().build();
    }

    // Count likes & dislikes for a video
    @GetMapping("/count")
    public ResponseEntity<?> countReactions(@PathVariable Long videoId) {
        long likes = likeService.countLikes(videoId);
        long dislikes = likeService.countDislikes(videoId);

        return ResponseEntity.ok(Map.of(
                "videoId", videoId,
                "likes", likes,
                "dislikes", dislikes
        ));
    }




}
