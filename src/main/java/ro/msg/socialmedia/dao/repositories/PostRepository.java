package ro.msg.socialmedia.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.socialmedia.dao.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
