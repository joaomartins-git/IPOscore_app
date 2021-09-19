package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
// import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@Table(name = "Preoperative_comorbidities")
public class PreoperativeComorbiditiesData implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="surgerydata_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "surgerydata_id")
    // @JsonIgnoreProperties({"ariscatData","acsRiskCalculatorData"})
    @JsonIgnore
    private SurgeryData surgerydata;

    @Column(name="comorbidities_info")
    private String comorbiditiesInfo;


    public PreoperativeComorbiditiesData() {}

    public PreoperativeComorbiditiesData(Long id, String comorbidities_info) {
        this.id = id;
        this.comorbiditiesInfo = comorbidities_info;
    }

    public Long getID() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComorbiditiesInfo() {
        return this.comorbiditiesInfo;
    }

    public void setComorbiditiesInfo(String comorbidities_info) {
        this.comorbiditiesInfo = comorbidities_info;
    }

    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }

    @Override
    public String toString() {
        return "PreoperativeComorbiditiesData [comorbidities_info=" + comorbiditiesInfo + ", id=" + id + "]";
    }




    

}