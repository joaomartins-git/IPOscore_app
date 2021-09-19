package com.tese.webplatform.iposcore.dto;

import java.io.Serializable;

public class objJsonToSpring implements Serializable {

    private static final long serialVersionUID = 1L;

    private String attribute;
    private String relation;
    private String value;

    private boolean conjunction;

    public objJsonToSpring(){}

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isConjunction() {
        return conjunction;
    }

    public void setConjunction(boolean conjunction) {
        this.conjunction = conjunction;
    }

    @Override
    public String toString() {
        return "objJsonToSpring [attribute=" + attribute + ", conjunction=" + conjunction + ", relation=" + relation
                + ", value=" + value + "]";
    }
    


    

}