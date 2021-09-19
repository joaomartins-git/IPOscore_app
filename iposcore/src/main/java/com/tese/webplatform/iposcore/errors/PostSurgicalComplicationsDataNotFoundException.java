package com.tese.webplatform.iposcore.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostSurgicalComplicationsDataNotFoundException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;
 
    public PostSurgicalComplicationsDataNotFoundException() {
        super("Patient Post Surgical Complications Data does not exist");
    }
 
}