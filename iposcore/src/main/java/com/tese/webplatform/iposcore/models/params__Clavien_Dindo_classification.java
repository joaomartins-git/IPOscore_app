package com.tese.webplatform.iposcore.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "params__Clavien_Dindo_classification")
public class params__Clavien_Dindo_classification {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "typeof_Clavien_Dindo_classification")
    private String typeof_Clavien_Dindo_classification;

    @OneToMany(mappedBy = "Clavien_Dindo_classification")
    private List<PostSurgicalComplicationsData> listaPSC;

    public params__Clavien_Dindo_classification() {}

    public params__Clavien_Dindo_classification(Long id, String typeof_Clavien_Dindo_classification) {
        this.id = id;
        this.typeof_Clavien_Dindo_classification = typeof_Clavien_Dindo_classification;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeof_Clavien_Dindo_classification() {
        return typeof_Clavien_Dindo_classification;
    }

    public void setTypeof_Clavien_Dindo_classification(String typeof_Clavien_Dindo_classification) {
        this.typeof_Clavien_Dindo_classification = typeof_Clavien_Dindo_classification;
    }




    
}