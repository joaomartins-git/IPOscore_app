package com.tese.webplatform.iposcore.models;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "params__gender")
public class params__gender {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "typeof_gender")
    private String typeof_gender;

    @OneToMany(mappedBy = "gender")
    private List<PatientData> listaP;

    public params__gender() {}

    public params__gender(Long id, String typeof_gender) {
        this.id = id;
        this.typeof_gender = typeof_gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeof_gender() {
        return typeof_gender;
    }

    public void setTypeof_gender(String typeof_gender) {
        this.typeof_gender = typeof_gender;
    }



    
}