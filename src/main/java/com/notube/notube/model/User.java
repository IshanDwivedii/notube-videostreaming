package com.notube.notube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"videos", "likes", "comments"})
@EqualsAndHashCode(exclude = {"videos", "likes", "comments"})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "uploader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Video> videos;
}
