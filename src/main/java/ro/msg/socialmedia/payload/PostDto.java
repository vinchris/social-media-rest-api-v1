package ro.msg.socialmedia.payload;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private Long id;

    @NotEmpty // title should not be null
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty // description should not be null
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;

    @JsonManagedReference
    private Set<CommentDto> comments;

}
