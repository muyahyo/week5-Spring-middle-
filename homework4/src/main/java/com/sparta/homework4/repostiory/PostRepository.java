package com.sparta.homework4.repostiory;

import com.sparta.homework4.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    List<Post> findAllByUserId(Long userId);
    Optional<Post> findByIdAndUserId(Long id, Long userId);
}
