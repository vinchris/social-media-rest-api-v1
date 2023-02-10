package ro.msg.socialmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.socialmedia.dao.entities.Post;
import ro.msg.socialmedia.dao.repositories.PostRepository;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to entity
        Post post = toPost(postDto);

        // save entity to persistence context
        Post savedPost = postRepository.save(post);

        // return new postDto instance
        return toPostDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long id) {
        // convert entity by id
        Post post = postRepository.getReferenceById(id);

        // return new postDto instance
        return toPostDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> listOfPosts = postRepository.findAll();

        return listOfPosts.stream().map(post -> toPostDto(post)).collect(Collectors.toList());
    }

    private Post toPost(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());

        return post;
    }

    private PostDto toPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());

        return postDto;
    }
}
