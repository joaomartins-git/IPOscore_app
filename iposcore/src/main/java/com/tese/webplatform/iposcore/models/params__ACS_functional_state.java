package com.tese.webplatform.iposcore.models;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "params__ACS_functional_state")
public class params__ACS_functional_state {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "typeof_acs_functional_state")
    private String typeof_acs_functional_state;


    @OneToMany(mappedBy = "ACS_functional_state")
    private List<ACSRiskCalculatorData> listaFunctionalSt;

    public params__ACS_functional_state() {
    }

    public params__ACS_functional_state(Long id, String typeof_acs_functional_state) {
        this.id = id;
        this.typeof_acs_functional_state = typeof_acs_functional_state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeof_acs_functional_state() {
        return typeof_acs_functional_state;
    }

    public void setTypeof_acs_functional_state(String typeof_acs_functional_state) {
        this.typeof_acs_functional_state = typeof_acs_functional_state;
    }



    
}