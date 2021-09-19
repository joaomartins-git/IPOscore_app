package com.tese.webplatform.iposcore.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CHARLSON")
public class CharlsonData implements Serializable{
    
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

    @Column(name = "CHARLSON_age")
    private int CHARLSON_age;

    @Column(name = "CHARLSON_diabetes_mellitus")
    private int CHARLSON_diabetes_mellitus;
    
    @Column(name = "CHARLSON_liver_disease")
    private int CHARLSON_liver_disease;
    
    @Column(name = "CHARLSON_malignancy_solid_tumor")
    private int CHARLSON_malignancy_solid_tumor;
    
    @Column(name = "CHARLSON_AIDS")
    private int CHARLSON_AIDS;
    
    @Column(name = "CHARLSON_chronic_kidney_disease")
    private int CHARLSON_chronic_kidney_disease;
    
    @Column(name = "CHARLSON_heart_failure")
    private int CHARLSON_heart_failure;
    
    @Column(name = "CHARLSON_myocardial_infarction")
    private int CHARLSON_myocardial_infarction;
    
    @Column(name = "CHARLSON_DPOC")
    private int CHARLSON_DPOC;
    
    @Column(name = "CHARLSON_peripheral_vascular_disease")
    private int CHARLSON_peripheral_vascular_disease;
    
    @Column(name = "CHARLSON_AVC")
    private int CHARLSON_AVC;
    
    @Column(name = "CHARLSON_dementia")
    private int CHARLSON_dementia;
    
    @Column(name = "CHARLSON_hemiplegia")
    private int CHARLSON_hemiplegia;
    
    @Column(name = "CHARLSON_connective_tissue_disease")
    private int CHARLSON_connective_tissue_disease;
    
    @Column(name = "CHARLSON_peptic_ulcer")
    private int CHARLSON_peptic_ulcer;
    
    @Column(name = "POINTS_Charlson_Comorbidity_Index")
    private int POINTS_Charlson_Comorbidity_Index;
    
    @Column(name = "CHARLSON_Estimated_10year_survival_percentage")
    private double CHARLSON_Estimated_10year_survival_percentage;


    public CharlsonData() {}


    public CharlsonData(int CHARLSON_age, int CHARLSON_diabetes_mellitus, int CHARLSON_liver_disease,
            int CHARLSON_malignancy_solid_tumor, int CHARLSON_AIDS, int CHARLSON_chronic_kidney_disease,
            int CHARLSON_heart_failure, int CHARLSON_myocardial_infarction, int CHARLSON_DPOC,
            int CHARLSON_peripheral_vascular_disease, int CHARLSON_AVC, int CHARLSON_dementia, int CHARLSON_hemiplegia,
            int CHARLSON_connective_tissue_disease, int CHARLSON_peptic_ulcer, int POINTS_Charlson_Comorbidity_Index,
            double CHARLSON_Estimated_10year_survival_percentage) {
        this.CHARLSON_age = CHARLSON_age;
        this.CHARLSON_diabetes_mellitus = CHARLSON_diabetes_mellitus;
        this.CHARLSON_liver_disease = CHARLSON_liver_disease;
        this.CHARLSON_malignancy_solid_tumor = CHARLSON_malignancy_solid_tumor;
        this.CHARLSON_AIDS = CHARLSON_AIDS;
        this.CHARLSON_chronic_kidney_disease = CHARLSON_chronic_kidney_disease;
        this.CHARLSON_heart_failure = CHARLSON_heart_failure;
        this.CHARLSON_myocardial_infarction = CHARLSON_myocardial_infarction;
        this.CHARLSON_DPOC = CHARLSON_DPOC;
        this.CHARLSON_peripheral_vascular_disease = CHARLSON_peripheral_vascular_disease;
        this.CHARLSON_AVC = CHARLSON_AVC;
        this.CHARLSON_dementia = CHARLSON_dementia;
        this.CHARLSON_hemiplegia = CHARLSON_hemiplegia;
        this.CHARLSON_connective_tissue_disease = CHARLSON_connective_tissue_disease;
        this.CHARLSON_peptic_ulcer = CHARLSON_peptic_ulcer;
        this.POINTS_Charlson_Comorbidity_Index = POINTS_Charlson_Comorbidity_Index;
        this.CHARLSON_Estimated_10year_survival_percentage = CHARLSON_Estimated_10year_survival_percentage;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }
    
    
    public int getCHARLSON_age() {
        return CHARLSON_age;
    }


    public void setCHARLSON_age(int CHARLSON_age) {
        this.CHARLSON_age = CHARLSON_age;
    }


    public int getCHARLSON_diabetes_mellitus() {
        return CHARLSON_diabetes_mellitus;
    }


    public void setCHARLSON_diabetes_mellitus(int CHARLSON_diabetes_mellitus) {
        this.CHARLSON_diabetes_mellitus = CHARLSON_diabetes_mellitus;
    }


    public int getCHARLSON_liver_disease() {
        return CHARLSON_liver_disease;
    }


    public void setCHARLSON_liver_disease(int CHARLSON_liver_disease) {
        this.CHARLSON_liver_disease = CHARLSON_liver_disease;
    }


    public int getCHARLSON_malignancy_solid_tumor() {
        return CHARLSON_malignancy_solid_tumor;
    }


    public void setCHARLSON_malignancy_solid_tumor(int CHARLSON_malignancy_solid_tumor) {
        this.CHARLSON_malignancy_solid_tumor = CHARLSON_malignancy_solid_tumor;
    }


    public int getCHARLSON_AIDS() {
        return CHARLSON_AIDS;
    }


    public void setCHARLSON_AIDS(int CHARLSON_AIDS) {
        this.CHARLSON_AIDS = CHARLSON_AIDS;
    }


    public int getCHARLSON_chronic_kidney_disease() {
        return CHARLSON_chronic_kidney_disease;
    }


    public void setCHARLSON_chronic_kidney_disease(int CHARLSON_chronic_kidney_disease) {
        this.CHARLSON_chronic_kidney_disease = CHARLSON_chronic_kidney_disease;
    }


    public int getCHARLSON_heart_failure() {
        return CHARLSON_heart_failure;
    }


    public void setCHARLSON_heart_failure(int CHARLSON_heart_failure) {
        this.CHARLSON_heart_failure = CHARLSON_heart_failure;
    }


    public int getCHARLSON_myocardial_infarction() {
        return CHARLSON_myocardial_infarction;
    }


    public void setCHARLSON_myocardial_infarction(int CHARLSON_myocardial_infarction) {
        this.CHARLSON_myocardial_infarction = CHARLSON_myocardial_infarction;
    }


    public int getCHARLSON_DPOC() {
        return CHARLSON_DPOC;
    }


    public void setCHARLSON_DPOC(int CHARLSON_DPOC) {
        this.CHARLSON_DPOC = CHARLSON_DPOC;
    }


    public int getCHARLSON_peripheral_vascular_disease() {
        return CHARLSON_peripheral_vascular_disease;
    }


    public void setCHARLSON_peripheral_vascular_disease(int CHARLSON_peripheral_vascular_disease) {
        this.CHARLSON_peripheral_vascular_disease = CHARLSON_peripheral_vascular_disease;
    }


    public int getCHARLSON_AVC() {
        return CHARLSON_AVC;
    }


    public void setCHARLSON_AVC(int CHARLSON_AVC) {
        this.CHARLSON_AVC = CHARLSON_AVC;
    }


    public int getCHARLSON_dementia() {
        return CHARLSON_dementia;
    }


    public void setCHARLSON_dementia(int CHARLSON_dementia) {
        this.CHARLSON_dementia = CHARLSON_dementia;
    }


    public int getCHARLSON_hemiplegia() {
        return CHARLSON_hemiplegia;
    }


    public void setCHARLSON_hemiplegia(int CHARLSON_hemiplegia) {
        this.CHARLSON_hemiplegia = CHARLSON_hemiplegia;
    }


    public int getCHARLSON_connective_tissue_disease() {
        return CHARLSON_connective_tissue_disease;
    }


    public void setCHARLSON_connective_tissue_disease(int CHARLSON_connective_tissue_disease) {
        this.CHARLSON_connective_tissue_disease = CHARLSON_connective_tissue_disease;
    }


    public int getCHARLSON_peptic_ulcer() {
        return CHARLSON_peptic_ulcer;
    }


    public void setCHARLSON_peptic_ulcer(int CHARLSON_peptic_ulcer) {
        this.CHARLSON_peptic_ulcer = CHARLSON_peptic_ulcer;
    }


    public int getPOINTS_Charlson_Comorbidity_Index() {
        return POINTS_Charlson_Comorbidity_Index;
    }


    public void setPOINTS_Charlson_Comorbidity_Index(int POINTS_Charlson_Comorbidity_Index) {
        this.POINTS_Charlson_Comorbidity_Index = POINTS_Charlson_Comorbidity_Index;
    }


    public double getCHARLSON_Estimated_10year_survival_percentage() {
        return CHARLSON_Estimated_10year_survival_percentage;
    }


    public void setCHARLSON_Estimated_10year_survival_percentage(double CHARLSON_Estimated_10year_survival_percentage) {
        this.CHARLSON_Estimated_10year_survival_percentage = CHARLSON_Estimated_10year_survival_percentage;
    }

    
    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }


    @Override
    public String toString() {
        return "CharlsonData [CHARLSON_AIDS=" + CHARLSON_AIDS + ", CHARLSON_AVC=" + CHARLSON_AVC + ", CHARLSON_DPOC="
                + CHARLSON_DPOC + ", CHARLSON_Estimated_10year_survival_percentage="
                + CHARLSON_Estimated_10year_survival_percentage + ", CHARLSON_age=" + CHARLSON_age
                + ", CHARLSON_chronic_kidney_disease=" + CHARLSON_chronic_kidney_disease
                + ", CHARLSON_connective_tissue_disease=" + CHARLSON_connective_tissue_disease + ", CHARLSON_dementia="
                + CHARLSON_dementia + ", CHARLSON_diabetes_mellitus=" + CHARLSON_diabetes_mellitus
                + ", CHARLSON_heart_failure=" + CHARLSON_heart_failure + ", CHARLSON_hemiplegia=" + CHARLSON_hemiplegia
                + ", CHARLSON_liver_disease=" + CHARLSON_liver_disease + ", CHARLSON_malignancy_solid_tumor="
                + CHARLSON_malignancy_solid_tumor + ", CHARLSON_myocardial_infarction=" + CHARLSON_myocardial_infarction
                + ", CHARLSON_peptic_ulcer=" + CHARLSON_peptic_ulcer + ", CHARLSON_peripheral_vascular_disease="
                + CHARLSON_peripheral_vascular_disease + ", POINTS_Charlson_Comorbidity_Index="
                + POINTS_Charlson_Comorbidity_Index + "]";
    }



    
}
