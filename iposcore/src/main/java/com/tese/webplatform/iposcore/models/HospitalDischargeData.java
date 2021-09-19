package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@Table(name = "Hospital_Discharge")
public class HospitalDischargeData implements Serializable{

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

    @Column(name="destiny_after_IPO_Discharge")
    private int destiny_after_IPO_Discharge;
    
    @Column(name="death_upto_1year")
    private int deathUpTo1year;
    
    @Column(name="death_time_elapsed_after_surgerydate")
    private int death_time_elapsed_after_surgerydate;
    
    @Column(name="death_date")
    private Date death_date;

    @Column(name="Additional_Info")
    private String Additional_Info;

    @Column(name = "Additional_Info_english")
    private String Additional_Info_english;

    public HospitalDischargeData(){}

    public HospitalDischargeData(Long id, int destiny_after_IPO_Discharge, int deathUpTo1year,
            int death_time_elapsed_after_surgerydate, Date death_date, String Additional_Info, String Additional_Info_english) {
        this.id = id;
        this.destiny_after_IPO_Discharge = destiny_after_IPO_Discharge;
        this.deathUpTo1year = deathUpTo1year;
        this.death_time_elapsed_after_surgerydate = death_time_elapsed_after_surgerydate;
        this.death_date = death_date;
        this.Additional_Info = Additional_Info;
        this.Additional_Info_english = Additional_Info_english;
    }
    
    public Long getID() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDestiny_after_IPO_Discharge() {
        return this.destiny_after_IPO_Discharge;
    }

    public void setDestiny_after_IPO_Discharge(int destiny_after_IPO_Discharge) {
        this.destiny_after_IPO_Discharge = destiny_after_IPO_Discharge;
    }

    public int getDeathUpTo1Year() {
        return this.deathUpTo1year;
    }

    public void setDeathUpTo1Year(int deathUpTo1year) {
        this.deathUpTo1year = deathUpTo1year;
    }

    public int getDeath_time_elapsed_after_surgerydate() {
        return this.death_time_elapsed_after_surgerydate;
    }

    public void setDeath_time_elapsed_after_surgerydate(int death_time_elapsed_after_surgerydate) {
        this.death_time_elapsed_after_surgerydate = death_time_elapsed_after_surgerydate;
    }

    public Date getDeath_date() {
        return this.death_date;
    }

    public void setDeath_date(Date death_date) {
        this.death_date = death_date;
    }

    public String getAdditional_Info() {
        return this.Additional_Info;
    }

    public void setAdditional_Info(String Additional_Info) {
        this.Additional_Info = Additional_Info;
    }

    public String getAdditional_Info_english(){
        return this.Additional_Info_english;
    }

    public void setAdditional_Info_english(String Additional_Info_english){
        this.Additional_Info_english = Additional_Info_english;
    }

    @Override
    public String toString() {
        return "HospitalDischargeData [Additional_Info=" + Additional_Info + ", death_date=" + death_date
                + ", death_time_elapsed_after_surgerydate=" + death_time_elapsed_after_surgerydate
                + ", death_upto_1year=" + deathUpTo1year + ", destiny_after_IPO_Discharge="
                + destiny_after_IPO_Discharge + ", id=" + id + "]";
    }


    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }




}