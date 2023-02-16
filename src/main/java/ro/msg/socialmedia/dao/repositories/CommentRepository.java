package ro.msg.socialmedia.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.socialmedia.dao.entities.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long id);


}
