package toproject.toy.service;

import toproject.toy.dto.CommentDto;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);

}
