package com.tese.webplatform.iposcore.models;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "params__admission_Motive")
public class params__admission_Motive {
    
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "typeof_motive")
    private String typeof_motive;

    @OneToMany(mappedBy = "admissionMotive")
    private List<internmentCaracteristicsData> listaAdmissionMotive;

    public params__admission_Motive() {}

    public params__admission_Motive(Long id, String typeof_motive) {
        this.id = id;
        this.typeof_motive = typeof_motive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeof_motive() {
        return typeof_motive;
    }

    public void setTypeof_motive(String typeof_motive) {
        this.typeof_motive = typeof_motive;
    }

    
}