package com.sparta.homework4.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private String username;
    private String password;
    private String content;
    private String title;
    private long userid;
}


