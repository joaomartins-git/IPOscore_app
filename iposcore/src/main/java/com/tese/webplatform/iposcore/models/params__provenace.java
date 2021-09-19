package com.tese.webplatform.iposcore.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "params__provenace")
public class params__provenace {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "typeof_provenace")
    private String typeof_provenace;

    @OneToMany(mappedBy = "provenace")
    private List<internmentCaracteristicsData> lista;

    public params__provenace() {}

    public params__provenace(Long id, String typOf) {
        this.id = id;
        this.typeof_provenace = typOf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeof_provenace() {
        return typeof_provenace;
    }

    public void setTypeof_provenace(String typeof_provenace) {
        this.typeof_provenace = typeof_provenace;
    }

        

}