package ro.msg.socialmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ro.msg.socialmedia.dao.entities.Comment;
import ro.msg.socialmedia.dao.entities.Post;
import ro.msg.socialmedia.dao.repositories.CommentRepository;
import ro.msg.socialmedia.dao.repositories.PostRepository;
import ro.msg.socialmedia.exceptions.BlogAPIException;
import ro.msg.socialmedia.exceptions.ResourceNotFoundException;
import ro.msg.socialmedia.payload.CommentDto;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // retrieve post entity by id
        Post post = Optional.of(postRepository.getReferenceById(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        // set post to comment entity
        comment.setPost(post);

        Comment savedNewComment = commentRepository.save(comment);

        return mapToDto(savedNewComment);
    }

    @Override
    public List<CommentDto> getAllComments(Long postId) {
        List<Comment> commentList = commentRepository.findByPostId(postId);
        return commentList.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = Optional.of(postRepository.getReferenceById(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        // retrieve comment by comment id
        Comment comment = Optional.of(commentRepository.getReferenceById(commentId)).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        // retrieve post entity by id
        Post post = Optional.of(postRepository.getReferenceById(postId)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId.toString()));

        // retrieve comment by comment id
        Comment comment = Optional.of(commentRepository.getReferenceById(commentId)).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId.toString()));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(comment.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    /**
     * helper method to map a post dto to a comment entity
     *
     * @param dto
     * @return
     */
    private Comment mapToEntity(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());

        return comment;
    }

    /**
     * helper method to map a comment entity to a post dto
     *
     * @param comment
     * @return
     */
    private CommentDto mapToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        dto.setEmail(dto.getEmail());
        dto.setName(dto.getName());

        return dto;
    }
}
