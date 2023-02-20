package ro.msg.socialmedia.service;

import ro.msg.socialmedia.payload.CommentDto;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.payload.PostResponse;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getAllComments(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
