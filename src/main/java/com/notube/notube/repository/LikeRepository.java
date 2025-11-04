package com.notube.notube.repository;

import com.notube.notube.model.Like;
import com.notube.notube.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    long countByVideoIdAndLiked(Long videoId, boolean liked);
}
