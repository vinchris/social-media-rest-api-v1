package ro.msg.socialmedia.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.msg.socialmedia.dao.entities.Post;
import ro.msg.socialmedia.dao.repositories.PostRepository;
import ro.msg.socialmedia.exceptions.ResourceNotFoundException;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.payload.PostResponse;
import ro.msg.socialmedia.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert dto to entity
        Post post = mapToEntity(postDto);

        // save entity to persistence context
        Post savedPost = postRepository.save(post);

        // return new postDto instance
        return mapToDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long id) {
        // get entity by id or else throw exception
        Post post = Optional.ofNullable(postRepository.getReferenceById(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        // return new postDto instance
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto dto, Long id) {
        // get entity by id or else throw exception
        Post post = Optional.ofNullable(postRepository.getReferenceById(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setDescription(dto.getDescription());

        Post savedUpdatedPost = postRepository.save(post);

        return mapToDto(savedUpdatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        // get entity by id or else throw exception
        Post post = Optional.of(postRepository.getReferenceById(id)).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        // create Sort instance
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    /**
     * helper method to map a post dto to a post entity
     *
     * @param dto
     * @return
     */
    private Post mapToEntity(PostDto dto) {
        // mapping using ModelMapper
        Post post = mapper.map(dto, Post.class);

        // hardcoded mapping
//        Post post = new Post();
//        post.setId(dto.getId());
//        post.setTitle(dto.getTitle());
//        post.setContent(dto.getContent());
//        post.setDescription(dto.getDescription());

        return post;
    }

    /**
     * helper method to map a post entity to a post dto
     *
     * @param post
     * @return
     */
    private PostDto mapToDto(Post post) {
        // mapping using ModelMapper
        PostDto postDto = mapper.map(post, PostDto.class);

        // hardcoded mapping
//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setContent(post.getContent());
//        postDto.setDescription(post.getDescription());

        return postDto;
    }
}
