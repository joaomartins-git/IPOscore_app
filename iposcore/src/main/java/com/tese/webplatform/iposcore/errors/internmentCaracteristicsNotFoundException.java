package com.tese.webplatform.iposcore.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class internmentCaracteristicsNotFoundException extends RuntimeException {
 
    private static final long serialVersionUID = 1L;
 
    public internmentCaracteristicsNotFoundException() {
        super("The patients internment characteristics does not exist");
    }
 
}