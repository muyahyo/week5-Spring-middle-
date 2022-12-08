package com.sparta.homework4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.homework4.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity  //DB 테이블 역할을 함
@Setter
@Getter
@NoArgsConstructor

public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String content;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long userId;

    public Post(PostRequestDto requestDto, Long id){
        this.username = requestDto.getUsername();
        this.content = requestDto.getContent();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
        this.userId = Userid();
    }

    private Long Userid() {
    }

    public void update(PostRequestDto postRequestDto){
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
        this.title = postRequestDto.getTitle();
        this.password = postRequestDto.getPassword();
    }
}
