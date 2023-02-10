package ro.msg.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto){
        return new ResponseEntity<>(postService.createPost(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
}
