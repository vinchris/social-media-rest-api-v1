package ro.msg.socialmedia.payload;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;

    @JsonManagedReference
    private Set<CommentDto> comments;

}
