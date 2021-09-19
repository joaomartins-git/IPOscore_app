package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Post_Surgical_complications")
public class PostSurgicalComplicationsData implements Serializable{

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
    
    @Column(name="post_surgical_complication")
    private int postSurgicalComplication;
    
    @Column(name="post_surgical_complication_description")
    private String post_surgical_complication_description;
    
    @Column(name="complication_COD")
    private String complication_COD;
    
    @Column(name="complication_principal_COD")
    private int complication_principal_COD;
    
    @Column(name="ACS_classification_general_complications")
    private int ACS_classification_general_complications;
    
    @Column(name="ACS_classification_specific_complications")
    private String ACS_classification_specific_complications;
    
    // @Column(name="Clavien_Dindo_classification")
    // private int Clavien_Dindo_classification;
    
    @ManyToOne
    @JoinColumn(name="Clavien_Dindo_classification")
    private params__Clavien_Dindo_classification Clavien_Dindo_classification;


    public PostSurgicalComplicationsData(){}


    // public PostSurgicalComplicationsData(Long id, boolean post_surgical_complication, String post_surgical_complication_description, String complication_COD, int complication_principal_COD, int ACS_classification_general_complications, String ACS_classification_specific_complications, int Clavien_Dindo_classification) {
    //     this.id = id;
    //     this.postSurgicalComplication = post_surgical_complication;
    //     this.post_surgical_complication_description = post_surgical_complication_description;
    //     this.complication_COD = complication_COD;
    //     this.complication_principal_COD = complication_principal_COD;
    //     this.ACS_classification_general_complications = ACS_classification_general_complications;
    //     this.ACS_classification_specific_complications = ACS_classification_specific_complications;
    //     this.Clavien_Dindo_classification = Clavien_Dindo_classification;
    // }

    public PostSurgicalComplicationsData(Long id, int post_surgical_complication, String post_surgical_complication_description, String complication_COD, int complication_principal_COD, int ACS_classification_general_complications, String ACS_classification_specific_complications, params__Clavien_Dindo_classification Clavien_Dindo_classification) {
        this.id = id;
        this.postSurgicalComplication = post_surgical_complication;
        this.post_surgical_complication_description = post_surgical_complication_description;
        this.complication_COD = complication_COD;
        this.complication_principal_COD = complication_principal_COD;
        this.ACS_classification_general_complications = ACS_classification_general_complications;
        this.ACS_classification_specific_complications = ACS_classification_specific_complications;
        this.Clavien_Dindo_classification = Clavien_Dindo_classification;
    }

    public Long getID() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPostSurgicalComplication() {
        return this.postSurgicalComplication;
    }

    public void setPostSurgicalComplication(int post_surgical_complication) {
        this.postSurgicalComplication = post_surgical_complication;
    }

    public String getPost_surgical_complication_description() {
        return this.post_surgical_complication_description;
    }

    public void setPost_surgical_complication_description(String post_surgical_complication_description) {
        this.post_surgical_complication_description = post_surgical_complication_description;
    }

    public String getComplication_COD() {
        return this.complication_COD;
    }

    public void setComplication_COD(String complication_COD) {
        this.complication_COD = complication_COD;
    }

    public int getComplication_principal_COD() {
        return this.complication_principal_COD;
    }

    public void setComplication_principal_COD(int complication_principal_COD) {
        this.complication_principal_COD = complication_principal_COD;
    }

    public int getACS_classification_general_complications() {
        return this.ACS_classification_general_complications;
    }

    public void setACS_classification_general_complications(int ACS_classification_general_complications) {
        this.ACS_classification_general_complications = ACS_classification_general_complications;
    }

    public String getACS_classification_specific_complications() {
        return this.ACS_classification_specific_complications;
    }

    public void setACS_classification_specific_complications(String ACS_classification_specific_complications) {
        this.ACS_classification_specific_complications = ACS_classification_specific_complications;
    }

    // public int getClavien_Dindo_classification() {
    //     return this.Clavien_Dindo_classification;
    // }

    public params__Clavien_Dindo_classification getClavien_Dindo_classification() {
        return this.Clavien_Dindo_classification;
    }

    // public void setClavien_Dindo_classification(int Clavien_Dindo_classification) {
    //     this.Clavien_Dindo_classification = Clavien_Dindo_classification;
    // }

    public void setClavien_Dindo_classification(params__Clavien_Dindo_classification Clavien_Dindo_classification) {
        this.Clavien_Dindo_classification = Clavien_Dindo_classification;
    }



    // @Override
    // public String toString() {
    //     return "PostSurgicalComplicationsData [ACS_classification_general_complications="
    //             + ACS_classification_general_complications + ", ACS_classification_specific_complications="
    //             + ACS_classification_specific_complications + ", Clavien_Dindo_classification="
    //             + Clavien_Dindo_classification + ", complication_COD=" + complication_COD
    //             + ", complication_principal_COD=" + complication_principal_COD + ", id=" + id
    //             + ", post_surgical_complication=" + postSurgicalComplication
    //             + ", post_surgical_complication_description=" + post_surgical_complication_description + "]";
    // }



    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }


    
}