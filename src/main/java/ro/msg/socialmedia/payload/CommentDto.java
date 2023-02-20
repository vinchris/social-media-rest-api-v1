package ro.msg.socialmedia.payload;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String name;
    private String email;
    private String body;

    @JsonBackReference
    private PostDto post;

}
