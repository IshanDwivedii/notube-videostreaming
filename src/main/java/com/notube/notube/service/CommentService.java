package com.notube.notube.service;

import com.notube.notube.model.Comment;
import com.notube.notube.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repo;

    public Comment add(Comment comment){
        return repo.save(comment);
    }

    public List<Comment> getAll(){
        return repo.findAll();
    }

    //get all comments of a specific user
    public List<Comment> getCommentByUser(Long userId){
        return repo.findByUserId(userId);
    }
}
