package com.tese.webplatform.iposcore.dto;

import java.io.Serializable;

import com.tese.webplatform.iposcore.models.params__gender;


public class updateDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;


    private Long ipoNumber;

    private Long age;

    private params__gender gender;

    public updateDTO(){}

    public Long getIpoNumber() {
        return ipoNumber;
    }

    public void setIpoNumber(Long ipoNumber) {
        this.ipoNumber = ipoNumber;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public params__gender getGender() {
        return gender;
    }

    public void setGender(params__gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "updateDTO [age=" + age + ", gender=" + gender + ", ipoNumber=" + ipoNumber + "]";
    }


    
}
