package com.hyeonho.board.service;

import com.hyeonho.board.domain.board.Post;
import com.hyeonho.board.dto.board.PostResponseDTO;
import com.hyeonho.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Post> getPosts() {
        return boardRepository.findAll();
    }

    public Post savePost(Post post) {
        return boardRepository.save(post);
    }
}
