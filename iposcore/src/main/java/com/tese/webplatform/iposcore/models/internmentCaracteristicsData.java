package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Internment_caracteristics")
public class internmentCaracteristicsData implements Serializable{

    private static final long serialVersionUID = 876688928410084519L;

    @Id
    @Column(name="surgerydata_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "surgerydata_id")
    // @JsonIgnoreProperties({"ariscatData","acsRiskCalculatorData"})
    @JsonIgnore
    private SurgeryData surgerydata;

    @ManyToOne
    @JoinColumn(name="Provenace")
    private params__provenace provenace;

    // @Column(name = "Admission_Motive")
    // private int admissionMotive;

    @ManyToOne
    @JoinColumn(name = "Admission_Motive")
    private params__admission_Motive admissionMotive;

    @Column(name = "Surgery_Type")
    private int surgeryType;

    @Column(name = "Specialty")
    private String specialty;
    
    @Column(name = "Specialty_COD")
    private int specialtyCOD;

    @Column(name = "UCI_days")
    private Double uciDays;

    @Column(name = "IPOP_days")
    private Double ipopDays;

    @Column(name = "firstsurgery_in_IPO")
    private int firstSurgeryInIPO;
    
    @Column(name = "NAS_totalpoints")
    private Double totalPointsNAS;
    
    @Column(name = "NAS_pointsperday")
    private Double pointsPerDayNAS;
    
    @Column(name = "Preoperative_QT")
    private int preoperativeQT;
    
    @Column(name = "Readmission_UCI")
    private int uciReadmission;

    @Column(name = "destiny_after_Uci")
    private Integer destiny_after_Uci;
    

    @Column(name = "Underwent_SCI_During_Internment")
    private Integer underwentSCI;

    @Column(name = "Motive_For_UCI_Readmission")
    private String uciReadmission_Motive;

    @Column(name = "Performed_surgery")
    private int performedSurgery;

    
    public internmentCaracteristicsData(){}

    // public internmentCaracteristicsData(Long id, params__provenace provenace, int admissionMotive, int surgeryType, String specialty, int specialtyCOD, double uciDays, Double ipopDays,  firstSurgeryInIPO, Double totalPointsNAS, Double pointsPerDayNAS,  preoperativeQT,  uciReadmission){
    //     this.id=id;
    //     this.provenace=provenace;
    //     this.admissionMotive=admissionMotive;
    //     this.surgeryType = surgeryType;
    //     this.specialty = specialty;
    //     this.specialtyCOD = specialtyCOD;
    //     this.uciDays = uciDays;
    //     this.ipopDays = ipopDays;
    //     this.firstSurgeryInIPO = firstSurgeryInIPO;
    //     this.totalPointsNAS = totalPointsNAS;
    //     this.pointsPerDayNAS = pointsPerDayNAS;
    //     this.preoperativeQT = preoperativeQT;
    //     this.uciReadmission = uciReadmission;
    // }

    public internmentCaracteristicsData(Long id, params__provenace provenace, params__admission_Motive admissionMotive, int surgeryType, String specialty, int specialtyCOD, double uciDays, Double ipopDays, int firstSurgeryInIPO, Double totalPointsNAS, Double pointsPerDayNAS, int preoperativeQT, int uciReadmission, Integer destiny_after_Uci, Integer underwentSCI, String uciReadmission_Motive, int performedSurgery){
        this.id=id;
        this.provenace=provenace;
        this.admissionMotive=admissionMotive;
        this.surgeryType = surgeryType;
        this.specialty = specialty;
        this.specialtyCOD = specialtyCOD;
        this.uciDays = uciDays;
        this.ipopDays = ipopDays;
        this.firstSurgeryInIPO = firstSurgeryInIPO;
        this.totalPointsNAS = totalPointsNAS;
        this.pointsPerDayNAS = pointsPerDayNAS;
        this.preoperativeQT = preoperativeQT;
        this.uciReadmission = uciReadmission;
        this.destiny_after_Uci = destiny_after_Uci;
        this.underwentSCI = underwentSCI;
        this.uciReadmission_Motive = uciReadmission_Motive;
        this.performedSurgery = performedSurgery;
    }

    public Long getID(){
        return id;
    }

    public params__provenace getProvenace(){
        return provenace;
    }

    // public int getAdmissionMotive(){
    //     return admissionMotive;
    // }

    public params__admission_Motive getAdmissionMotive(){
        return admissionMotive;
    }

    public int getSurgeryType(){
        return surgeryType;
    }

    public String getSpecialty(){
        return specialty;
    }

    public int getSpecialtyCOD(){
        return specialtyCOD;
    }

    public Double getUciDays(){
        return uciDays;
    }
    public Double getIpopDays(){
        return ipopDays;
    }

    public int getFirstSurgeryInIPO(){
        return firstSurgeryInIPO;
    }

    public Double getNASTotalPoints(){
        return totalPointsNAS;
    }

    public Double getNASPointsPerDay(){
        return pointsPerDayNAS;
    }

    public int getPreoperativeQT(){
        return preoperativeQT;
    }
    public int getUciReadmission(){
        return uciReadmission;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setProvenace(params__provenace provenace){
        this.provenace = provenace;
    }

    // public void setAdmissionMotive (int admissionMotive){
    //     this.admissionMotive = admissionMotive;
    // }

    public void setAdmissionMotive (params__admission_Motive admissionMotive){
        this.admissionMotive = admissionMotive;
    }

    public void setSurgeryType(int surgeryType){
        this.surgeryType = surgeryType;
    }

    public void setSpecialty(String specialty){
        this.specialty = specialty;
    }

    public void setSpecialtyCOD(int specialtyCOD){
        this.specialtyCOD = specialtyCOD;
    }

    public void setUciDays(Double uciDays){
        this.uciDays = uciDays;
    }

    public void setIpopDays(Double ipopDays){
        this.ipopDays = ipopDays;
    }

    public void setFirstSurgeryInIPO(int firstSurgeryInIPO){
        this.firstSurgeryInIPO = firstSurgeryInIPO;
    }

    public void setTotalPointsNAS(Double totalPointsNAS){
        this.totalPointsNAS = totalPointsNAS;
    }

    public void setPointsPerDayNAS(Double pointsPerDayNAS){
        this.pointsPerDayNAS = pointsPerDayNAS;
    }

    public void setPreoperativeQT( int preoperativeQT){
        this.preoperativeQT = preoperativeQT;
    }

    public void setUciReadmission(int uciReadmission){
        this.uciReadmission = uciReadmission;
    }

    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }

    public Integer getDestiny_after_Uci() {
        return destiny_after_Uci;
    }

    public void setDestiny_after_Uci(Integer destiny_after_Uci) {
        this.destiny_after_Uci = destiny_after_Uci;
    }



    public Integer getUnderwentSCI() {
        return underwentSCI;
    }

    public void setUnderwentSCI(Integer underwentSCI) {
        this.underwentSCI = underwentSCI;
    }

    public String getUciReadmission_Motive() {
        return uciReadmission_Motive;
    }

    public void setUciReadmission_Motive(String uciReadmission_Motive) {
        this.uciReadmission_Motive = uciReadmission_Motive;
    }

    public int getPerformedSurgery() {
        return performedSurgery;
    }

    public void setPerformedSurgery(int performedSurgery) {
        this.performedSurgery = performedSurgery;
    }


    // @Override
    // public String toString() {
    //     return "internmentCaracteristicsData [admissionMotive=" + admissionMotive + ", firstSurgeryInIPO="
    //             + firstSurgeryInIPO + ", id=" + id + ", ipopDays=" + ipopDays + ", pointsPerDayNAS=" + pointsPerDayNAS
    //             + ", preoperativeQT=" + preoperativeQT + ", provenace=" + provenace + ", specialty=" + specialty
    //             + ", specialtyCOD=" + specialtyCOD + ", surgeryType=" + surgeryType + ", totalPointsNAS="
    //             + totalPointsNAS + ", uciDays=" + uciDays + ", uciReadmission=" + uciReadmission + "]";
    // }


    
}