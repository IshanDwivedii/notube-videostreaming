package com.notube.notube.service;

import com.notube.notube.model.Like;
import com.notube.notube.model.Video;
import com.notube.notube.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository repo;


    public long countLikes(Long videoId){
        return repo.countByVideoIdAndLiked(videoId, true);
    }

    public long countDislikes(Long videoId){
        return repo.countByVideoIdAndLiked(videoId, false);
    }

    public Like add(Like like){
        return repo.save(like);
    }

    public void removeLike(Long likeId){
         repo.deleteById(likeId);
    }
}
