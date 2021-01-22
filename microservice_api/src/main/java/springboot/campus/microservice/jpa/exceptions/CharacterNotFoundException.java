package springboot.campus.microservice.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CharacterNotFoundException() {
        super("Character does not exist");
    }

}