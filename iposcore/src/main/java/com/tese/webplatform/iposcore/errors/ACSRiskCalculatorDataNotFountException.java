package com.tese.webplatform.iposcore.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ACSRiskCalculatorDataNotFountException extends RuntimeException {
 
    private static final long serialVersionUID = 1245L;
 
    public ACSRiskCalculatorDataNotFountException() {
        super("Patient ACS Risk Calculator Data does not exist");
    }
 
}