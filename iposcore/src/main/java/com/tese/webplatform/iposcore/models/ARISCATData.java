package com.tese.webplatform.iposcore.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ARISCAT")
public class ARISCATData implements Serializable{

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

    @Column(name = "ARISCAT_age")
    private int ARISCATage;

    @Column(name = "ARISCAT_SpO2")
    private int ARISCAT_SpO2;

    @Column(name = "ARISCAT_respiratory_infection_last_month")
    private int ARISCAT_respiratory_infection_last_month;
    
    @Column(name = "ARISCAT_preoperative_anemia")
    private int ARISCAT_preoperative_anemia;

    @Column(name = "ARISCAT_surgical_incision")
    private int ARISCAT_surgical_incision;
    
    @Column(name = "ARISCAT_surgery_duration")
    private int ARISCAT_surgery_duration;
   
    @Column(name = "ARISCAT_emerging_Procedure")
    private int ARISCAT_emerging_Procedure;
    
    @Column(name = "ARISCAT_Total_points")
    private int ARISCAT_Total_points;

    @Column(name = "ARISCAT_Score")
    private int ARISCAT_Score;
    


    public ARISCATData(){}

    public ARISCATData(Long id, int ARISCAT_age, int ARISCAT_SpO2, int ARISCAT_respiratory_infection_last_month, int ARISCAT_preoperative_anemia, int ARISCAT_surgical_incision, int ARISCAT_surgery_duration, int ARISCAT_emerging_Procedure, int ARISCAT_Total_points, int ARISCAT_Score){
        this.id = id;
        this.ARISCATage = ARISCAT_age;
        this.ARISCAT_SpO2 = ARISCAT_SpO2;
        this.ARISCAT_respiratory_infection_last_month = ARISCAT_respiratory_infection_last_month;
        this.ARISCAT_preoperative_anemia = ARISCAT_preoperative_anemia;
        this.ARISCAT_surgical_incision = ARISCAT_surgical_incision;
        this.ARISCAT_surgery_duration = ARISCAT_surgery_duration;
        this.ARISCAT_emerging_Procedure = ARISCAT_emerging_Procedure;
        this.ARISCAT_Total_points = ARISCAT_Total_points;
        this.ARISCAT_Score = ARISCAT_Score;
    }

    public Long getID(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public int getARISCATage(){
        return ARISCATage;
    }

    public void setARISCATage(int ARISCAT_age){
        this.ARISCATage = ARISCAT_age;
    }

    
    public int getARISCAT_SpO2(){
        return ARISCAT_SpO2;
    }

    public void setARISCAT_SpO2(int ARISCAT_SpO2){
        this.ARISCAT_SpO2 = ARISCAT_SpO2;
    }

    public int getARISCAT_respiratory_infection_last_month(){
        return ARISCAT_respiratory_infection_last_month;
    }

    public void setARISCAT_respiratory_infection_last_month(int ARISCAT_respiratory_infection_last_month){
        this.ARISCAT_respiratory_infection_last_month = ARISCAT_respiratory_infection_last_month;
    }

    public int getARISCAT_preoperative_anemia(){
        return ARISCAT_preoperative_anemia;
    }

    public void setARISCAT_preoperative_anemia(int ARISCAT_preoperative_anemia){
        this.ARISCAT_preoperative_anemia = ARISCAT_preoperative_anemia;
    }

    public int getARISCAT_surgical_incision(){
        return ARISCAT_surgical_incision;
    }

    public void setARISCAT_surgical_incision(int ARISCAT_surgical_incision){
        this.ARISCAT_surgical_incision = ARISCAT_surgical_incision;
    }

    public int getARISCAT_surgery_duration(){
        return ARISCAT_surgery_duration;
    }

    public void setARISCAT_surgery_duration(int ARISCAT_surgery_duration){
        this.ARISCAT_surgery_duration = ARISCAT_surgery_duration;
    }

    public int getARISCAT_emerging_Procedure(){
        return ARISCAT_emerging_Procedure;
    }

    public void setARISCAT_emerging_Procedure(int ARISCAT_emerging_Procedure){
        this.ARISCAT_emerging_Procedure = ARISCAT_emerging_Procedure;
    }

    public int getARISCAT_Total_points(){
        return ARISCAT_Total_points;
    }

    public void setARISCAT_Total_points(int ARISCAT_Total_points){
        this.ARISCAT_Total_points = ARISCAT_Total_points;
    }

    public int getARISCAT_Score(){
        return ARISCAT_Score;
    }

    public void setARISCAT_Score(int ARISCAT_Score){
        this.ARISCAT_Score = ARISCAT_Score;
    }

    @Override
    public String toString() {
        return "ARISCATData [ARISCAT_Score=" + getARISCATScore() + ", ARISCAT_SpO2=" + ARISCAT_SpO2
                + ", ARISCAT_Total_points=" + getTotalARISCATSum() + ", ARISCAT_emerging_Procedure="
                + ARISCAT_emerging_Procedure + ", ARISCAT_preoperative_anemia=" + ARISCAT_preoperative_anemia
                + ", ARISCAT_respiratory_infection_last_month=" + ARISCAT_respiratory_infection_last_month
                + ", ARISCAT_surgery_duration=" + ARISCAT_surgery_duration + ", ARISCAT_surgical_incision="
                + ARISCAT_surgical_incision + ", ARISCATage=" + ARISCATage + ", id=" + id + "]";
    }

    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }


    private int getTotalARISCATSum(){

        return (ARISCATage + ARISCAT_SpO2 + ARISCAT_respiratory_infection_last_month + ARISCAT_preoperative_anemia + ARISCAT_surgical_incision + ARISCAT_surgery_duration + ARISCAT_emerging_Procedure);
    }


    private int getARISCATScore(){
        int x = 0;
        if(getTotalARISCATSum() < 26){
            x = 1;
        }
        else if (getTotalARISCATSum() > 44){
            x = 3;
        }
        else x = 2;

        return x;
    }
}