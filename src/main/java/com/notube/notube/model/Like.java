package com.notube.notube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"video", "user"})
@EqualsAndHashCode(exclude = {"video", "user"})
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
