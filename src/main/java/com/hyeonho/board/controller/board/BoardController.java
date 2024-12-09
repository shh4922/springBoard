package com.hyeonho.board.controller.board;

import com.hyeonho.board.domain.board.Post;
import com.hyeonho.board.dto.board.PostMapper;
import com.hyeonho.board.dto.board.PostRequestDTO;
import com.hyeonho.board.dto.board.PostResponseDTO;
import com.hyeonho.board.service.BoardService;
import com.hyeonho.board.service.UsersService;
import com.hyeonho.board.util.DefaultRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;
    private final PostMapper postMapper;

    @Autowired
    public BoardController(BoardService boardService, UsersService usersService, PostMapper postMapper) {
        this.boardService = boardService;
        this.postMapper = postMapper;
    }


    @GetMapping("/posts")
    public List<PostResponseDTO> getPosts() {
        List<Post> posts = boardService.getPosts();
        return postMapper.responsePostDTOList(posts);
    }

    @PostMapping("/create")
    public DefaultRes<PostResponseDTO> createPost(@RequestBody PostRequestDTO requestDTO) {
        Post post = postMapper.toEntity(requestDTO);
        Post savedPost = boardService.savePost(post);
        return DefaultRes.res(200,"게시글등록완료", postMapper.toResponseDTO(savedPost));
    }
}
