package com.sparta.week1.controller;


import com.sparta.week1.dto.PostRequestDto;
import com.sparta.week1.dto.PostResponseDto;
import com.sparta.week1.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/api/save")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/get")
    public List<PostResponseDto> getListPosts(){
        return postService.getListPosts();
    }

    @GetMapping("/api/get/{id}")
    public PostResponseDto getPosts(@PathVariable long id) {
        return postService.getPost(id);
    }

    @PutMapping("/api/update/{id}")
    public PostResponseDto updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/api/delete/{id}")
    public Map<String, Object> deletePost(@PathVariable long id, @RequestBody PostRequestDto requestDto) {
        return postService.deletePost(id, requestDto);
    }
}
