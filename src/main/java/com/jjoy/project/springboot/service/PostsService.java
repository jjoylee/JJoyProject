package com.jjoy.project.springboot.service;

import com.jjoy.project.springboot.domain.posts.Posts;
import com.jjoy.project.springboot.domain.posts.PostsRepository;
import com.jjoy.project.springboot.web.dto.PostsResponseDto;
import com.jjoy.project.springboot.web.dto.PostsSaveRequestDto;
import com.jjoy.project.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 계시글이 없습니다. id =" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 계시글이 없습니다. id =" + id));
        return new PostsResponseDto(entity);
    }
}
