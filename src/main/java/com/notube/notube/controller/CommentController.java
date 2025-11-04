package com.notube.notube.controller;

import com.notube.notube.model.Comment;
import com.notube.notube.model.Video;
import com.notube.notube.repository.VideoRepository;
import com.notube.notube.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notube")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final VideoRepository vidRepo;

    //add comment to a video
    @PostMapping("/videos/{videoId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long videoId,
                                        @RequestBody Comment comment){
        Optional<Video> videoOtp = vidRepo.findById((videoId));
        if(videoOtp.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        comment.setVideo(videoOtp.get());
        return ResponseEntity.ok(commentService.add(comment));
    }

    // get all comments for a video
    @GetMapping("/videos/{videoId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long videoId){
        Optional<Video> videOpt = vidRepo.findById(videoId);
        if(videOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        List<Comment> comments = videOpt.get().getComments();
        return ResponseEntity.ok(comments);
    }

    //get all comments for a user globally
    @GetMapping("/users/{userId}/comments")
    public ResponseEntity<List<Comment>> getCommentByUser(@PathVariable Long userId){
        List<Comment> comments = commentService.getCommentByUser(userId);
        return ResponseEntity.ok(comments);
    }

}
