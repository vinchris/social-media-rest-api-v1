package ro.msg.socialmedia.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BlogAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
