package com.hyeonho.board.dto.board;

import com.hyeonho.board.domain.board.Post;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// TODO: 이건 뭐임??
@Component
public class PostMapper {

    public PostResponseDTO toResponseDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setWriter(post.getWriter());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreateDate(post.getCreateDate());
        dto.setUpdateDate(post.getUpdateDate());
        return dto;
    }

    // TODO: 여기서 쓰는 함수들 다 뭔지 모르겠음..ㅋㅋ
    public List<PostResponseDTO> responsePostDTOList(List<Post> posts) {
        return posts
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 궁금한게, Entity에 setter 웬만하면 쓰지말라했는데, dto만드려면 써야하는거 아님?
    public Post toEntity(PostRequestDTO dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setWriter(dto.getWriter());
        return post;
    }
}
