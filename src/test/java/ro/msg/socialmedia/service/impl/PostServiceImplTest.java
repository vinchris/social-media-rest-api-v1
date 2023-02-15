package ro.msg.socialmedia.service.impl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.socialmedia.dao.repositories.PostRepository;
import ro.msg.socialmedia.payload.PostDto;
import ro.msg.socialmedia.service.PostService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
class PostServiceImplTest {

    @Autowired
    PostService service;

    @Spy
    PostDto dto1;

    @Spy
    PostDto dto2;

    @BeforeEach
    void beforeEachTest(){
        when(dto1.getId()).thenReturn(1L);
        when(dto1.getTitle()).thenReturn("Title 1");
        when(dto1.getDescription()).thenReturn("Description 1");
        when(dto1.getContent()).thenReturn("Content 1");
    }

    @Test
    void testCreatePost() {
        log.info("Dto: {} {} {} {}", dto1.getId(), dto1.getTitle(), dto1.getDescription(), dto1.getContent());

        PostDto returnedPostDto = service.createPost(dto1);

        log.info("Returned Dto: {} {} {} {}", returnedPostDto.getId(), returnedPostDto.getTitle(), returnedPostDto.getDescription(), returnedPostDto.getContent());

        assertEquals(1L, dto1.getId());
        assertEquals(1L, returnedPostDto.getId());
    }

    @Test
    void testGetAllPosts() {
        assertEquals(false, service.getAllPosts().isEmpty());
    }

    @Test
    @Transactional
    void testGetAllPostById() {
        assertEquals(1L, service.getPostById(1L).getId());
    }
}