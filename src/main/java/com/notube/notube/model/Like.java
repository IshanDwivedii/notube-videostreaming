package com.notube.notube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Like {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean liked;

    @ManyToOne @JoinColumn(name = "video_id")
    @JsonIgnore
    private Video video;

    @ManyToOne @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


}
