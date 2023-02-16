package ro.msg.socialmedia.service;

import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int PageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto dto, Long id);

    void deletePostById(Long id);
}
