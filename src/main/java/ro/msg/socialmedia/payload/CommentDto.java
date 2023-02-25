package ro.msg.socialmedia.payload;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "Name should not contain less than 2 characters")
    private String name;

    @NotEmpty(message = "E-mail should not be empty")
    @Email(message = "Invalid e-mail format")
    private String email;

    @NotEmpty
    @Size(min = 10, message = "Comment body should not contain less than 10 characters")
    private String body;

    @JsonBackReference
    private PostDto post;

}
