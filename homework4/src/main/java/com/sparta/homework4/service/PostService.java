package com.sparta.homework4.service;

import com.sparta.homework4.dto.PostRequestDto;
import com.sparta.homework4.dto.PostResponseDto;
import com.sparta.homework4.entity.Post;
import com.sparta.homework4.entity.User;
import com.sparta.homework4.jwt.JwtUtil;
import com.sparta.homework4.repostiory.PostRepository;
import com.sparta.homework4.repostiory.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final com.sparta.homework4.repostiory.UserRepository userRepository;
    private final JwtUtil jwtUtil;


    //대체 포스트 작성 서비스 논리 구조
    @Transactional
    public PostResponseDto createProduct(PostRequestDto requestDto, HttpServletRequest request) {
// Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
// 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
// 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
// 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
// 요청받은 DTO 로 DB에 저장할 객체 만들기
            Post post = PostRepository.saveAndFlush(new Post(requestDto, user.getId()));
            return new PostResponseDto(post);
        } else {
            return null;
        }
    }


    // 기존 포스트 작성 서비스 논리구조
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request){
        Post post = new Post(requestDto, user.getId());
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getListPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> postResponseDto = new ArrayList<>();
        for (Post post : postList){
            PostResponseDto postDto = new PostResponseDto(post);
            postResponseDto.add(postDto);
        }
        return postResponseDto;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
     return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);

        if(requestDto.getPassword().equals(post.getPassword())) {
            post.update(requestDto);
            return postResponseDto;
        } else {
            return postResponseDto;
        }
    }

    @Transactional
    public Map<String, Object> deletePost(Long id, PostRequestDto requestDto, HttpServletRequest request){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Map<String, Object> reponse = new HashMap<>();
        if(requestDto.getPassword().equals(post.getPassword())) {
            postRepository.deleteById(id);
            reponse.put("success", true);
            return reponse;
        } else {
            reponse.put("success", false);
            return reponse;

        }
    }

}
