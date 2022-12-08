package com.sparta.homework4.dto;

import com.sparta.homework4.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostResponseDto {
    private String username;
    private String content;
    private String title;
    private LocalDateTime modifiedAT;  //수정시간
    private LocalDateTime createdAt;   //최초작성시간
    private Long id;

    public PostResponseDto(Post post) {
        this.username = post.getUsername();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.modifiedAT = post.getModifiedAt();
        this.id = post.getId();

    }

}