package com.sparta.homework4.controller;

import com.sparta.homework4.dto.PostRequestDto;
import com.sparta.homework4.dto.PostResponseDto;
import com.sparta.homework4.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController //
@RequiredArgsConstructor //(롬복)
@RequestMapping("/api")

public class PostController {
    private final PostService postService;

//    @PostMapping("/api/auth/signup") //회원가입 APi
//    public PostResponseDto signup(){
//        return postService.login
//    }
//
//    @PostMapping("/api/auth/login")  //로그인 APi
//    public PostResponseDto login(){
//        return postService.
//    }


    // 관심 상품 등록하기
    @PostMapping("/post") //게시글 작성 APi 토큰 추가함
    public PostResponseDto createProduct(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
    // 응답 보내기
        return postService.createPost(requestDto, request);
    }

    @GetMapping("/api/posts") //게시글 조회 API 토큰 필요 없음
    public List<PostResponseDto> getListPosts(){
        return postService.getListPosts();
    }

    @GetMapping("/api/post/{id}") //선택한 게시글 조회 API 토큰 필요 없음
    public PostResponseDto getPosts(@PathVariable long id){
        return postService.getPost(id);
    }

    @PutMapping("/api/post/{id}") //게시글 수정 API 토큰 필요함
    public PostResponseDto updatePost(@PathVariable long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request){
        return postService.update(id, requestDto, request);
    }

    @DeleteMapping("/api/post/{id}")  //게시글 삭제 API 토큰 필요함
    public Map<String, Object> deletePost(@PathVariable long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return  postService.deletePost(id, requestDto, request);
    }

}