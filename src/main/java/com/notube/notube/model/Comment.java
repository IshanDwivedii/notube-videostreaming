package com.notube.notube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"video", "user"})
@EqualsAndHashCode(exclude = {"video", "user"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private LocalDateTime createdAt;

    @ManyToOne @JoinColumn(name = "video_id")
    @JsonIgnore
    private Video video;

    @ManyToOne @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
