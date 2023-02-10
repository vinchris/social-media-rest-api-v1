package ro.msg.socialmedia.service;

import ro.msg.socialmedia.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);
}
