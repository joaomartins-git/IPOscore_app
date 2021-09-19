package com.tese.webplatform.iposcore.errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HospitalDischargeDataNotFoundException extends RuntimeException {
 
    private static final long serialVersionUID = 1245L;
 
    public HospitalDischargeDataNotFoundException() {
        super("Patient Hospital Discharge Data does not exist");
    }
 
}