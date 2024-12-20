package edu.campus.numerique.users.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {
    public UserNotFound() {

        super("USER NOT FOUND");
    }
}
