package com.sparta.week1.service;


import com.sparta.week1.dto.PostRequestDto;
import com.sparta.week1.dto.PostResponseDto;
import com.sparta.week1.entity.Post;
import com.sparta.week1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);

    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getListPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList) {
            PostResponseDto postDto = new PostResponseDto(post);
            postResponseDto.add(postDto);
        }
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
                );
        PostResponseDto postResponseDto = new PostResponseDto(post);

        if (requestDto.getPassword().equals(post.getPassword())) {
            post.update(requestDto);
            return postResponseDto;
        } else {
            return postResponseDto;
        }
    }

    @Transactional
    public Map<String, Object> deletePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Map<String, Object> reponse = new HashMap<>();
        if(requestDto.getPassword().equals(post.getPassword())){
            postRepository.deleteById(id);
            reponse.put("success",true);
            return reponse;
            } else {
            reponse.put("success",false);
            return reponse;
        }
    }

}
