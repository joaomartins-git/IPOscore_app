package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@Table(name = "Application_for_UCI_Admission")
public class UCIAdmissionApplicationData implements Serializable{

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


    @Column(name= "UCI_Admission_Date")
    private Date UCI_Admission_Date;

    @Column(name= "Anesthesia_request_type") 
    private int Anesthesia_request_type;

    @Column(name= "Anesthesia_request_Date") 
    private Date Anesthesia_request_Date;


    public UCIAdmissionApplicationData(){}
    
    public UCIAdmissionApplicationData(Date UCI_Admission_Date, int Anesthesia_request_type, Date Anesthesia_request_Date,long id){
        this.UCI_Admission_Date = UCI_Admission_Date;
        this.Anesthesia_request_type = Anesthesia_request_type;
        this.Anesthesia_request_Date = Anesthesia_request_Date;
        this.id=id;
    }

    public Date getUCI_Admission_Date(){
        return UCI_Admission_Date;
    }

    public int getAnesthesia_request_type(){
        return Anesthesia_request_type;
    }

    public Date getAnesthesia_request_Date(){
        return Anesthesia_request_Date;
    }

    public Long getID(){
        return id;
    }

    public void setUCIApplicationDate(Date UCI_Admission_Date){
        this.UCI_Admission_Date = UCI_Admission_Date;
    }

    public void setAnesthesiaRequestType(int Anesthesia_request_type){
        this.Anesthesia_request_type = Anesthesia_request_type;
    }

    public void setAnesthesiaRequestDate(Date Anesthesia_request_Date){
        this.Anesthesia_request_Date = Anesthesia_request_Date;
    }

    public void setId(Long id){
        this.id = id;
    }
  
    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }

    @Override
    public String toString() {
        return "UCIAdmissionApplicationData [Anesthesia_request_Date=" + Anesthesia_request_Date
                + ", Anesthesia_request_type=" + Anesthesia_request_type + ", UCI_Admission_Date=" + UCI_Admission_Date
                + ", id=" + id + "]";
    }




}