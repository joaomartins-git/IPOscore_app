package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
// import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@Entity
@Table(name = "P_POSSUM")
public class ppossumData implements Serializable{

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

    @Column(name = "PP_age")
    private int PP_age;

    @Column(name = "PP_cardiac")
    private int PP_cardiac;

    @Column(name = "PP_respiratory")
    private int PP_respiratory;

    @Column(name = "PP_systolic_blood_pressure")
    private int PP_systolic_blood_pressure;

    @Column(name = "PP_ECG")
    private int PP_ECG;

    @Column(name = "PP_artirial_pulse")
    private int PP_artirial_pulse;

    @Column(name = "PP_hemoglobin")
    private int PP_hemoglobin;

    @Column(name = "PP_leukocytes")
    private int PP_leukocytes;

    @Column(name = "PP_urea")
    private int PP_urea;

    @Column(name = "PP_sodium")
    private int PP_sodium;

    @Column(name = "PP_potassium")
    private int PP_potassium;

    @Column(name = "PP_Glasgow_scale")
    private int PP_Glasgow_scale;

    @Column(name = "PP_operation_type")
    private int PP_operation_type;

    @Column(name = "PP_number_of_procedures")
    private int PP_number_of_procedures;

    @Column(name = "PP_blood_loss")
    private int PP_blood_loss;

    @Column(name = "PP_peritoneal_contamination")
    private int PP_peritoneal_contamination;

    @Column(name = "PP_state_of_malignancy")
    private int PP_state_of_malignancy;

    @Column(name = "PP_CEPOD_grading_operation")
    private int PP_CEPOD_grading_operation;

    @Column(name = "P_Possum_Physiological_Score")
    private int P_Possum_Physiological_Score;

    @Column(name = "P_Possum_Surgical_Severity_Score")
    private int P_Possum_Surgical_Severity_Score;

    @Column(name = "P_Possum_Morbidity_Percentage")
    private Double P_Possum_Morbidity_Percentage;

    @Column(name = "P_Possum_Mortality_Percentage")
    private Double P_Possum_Mortality_Percentage;

    public ppossumData(){}

    public ppossumData(Long id, int PP_age, int PP_cardiac, int PP_respiratory, int PP_systolic_blood_pressure, int PP_ECG, int PP_artirial_pulse, int PP_hemoglobin, int PP_leukocytes, int PP_urea, int PP_sodium, int PP_potassium, int PP_Glasgow_scale, int PP_operation_type, int PP_number_of_procedures, int PP_blood_loss, int PP_peritoneal_contamination, int PP_state_of_malignancy, int PP_CEPOD_grading_operation, int P_Possum_Physiological_Score, int P_Possum_Surgical_Severity_Score, Double P_Possum_Morbidity_Percentage, Double P_Possum_Mortality_Percentage){
        this.id = id;
        this.PP_age = PP_age;
        this.PP_cardiac = PP_cardiac;
        this.PP_respiratory = PP_respiratory;
        this.PP_systolic_blood_pressure = PP_systolic_blood_pressure;
        this.PP_ECG = PP_ECG;
        this.PP_artirial_pulse = PP_artirial_pulse;
        this.PP_hemoglobin = PP_hemoglobin;
        this.PP_leukocytes = PP_leukocytes;
        this.PP_urea = PP_urea;
        this.PP_sodium = PP_sodium;
        this.PP_potassium = PP_potassium;
        this.PP_Glasgow_scale = PP_Glasgow_scale;
        this.PP_operation_type = PP_operation_type;
        this.PP_number_of_procedures = PP_number_of_procedures;
        this.PP_blood_loss = PP_blood_loss;
        this.PP_peritoneal_contamination = PP_peritoneal_contamination;
        this.PP_state_of_malignancy = PP_state_of_malignancy;
        this.PP_CEPOD_grading_operation = PP_CEPOD_grading_operation;
        this.P_Possum_Physiological_Score = P_Possum_Physiological_Score;
        this.P_Possum_Surgical_Severity_Score = P_Possum_Surgical_Severity_Score;
        this.P_Possum_Morbidity_Percentage = P_Possum_Morbidity_Percentage;
        this.P_Possum_Mortality_Percentage = P_Possum_Mortality_Percentage;
    }

    public Long getID(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public int getPP_Age(){
        return PP_age;
    }

    public void setPP_Age(int PP_age){
        this.PP_age = PP_age;
    }

    public int getPP_cardiac(){
        return PP_cardiac;
    }

    public void setPP_cardiac(int PP_cardiac){
        this.PP_cardiac = PP_cardiac;
    }

    public int getPP_respiratory(){
        return PP_respiratory;
    }

    public void setPP_respiratory(int PP_respiratory){
        this.PP_respiratory = PP_respiratory;
    }    

    public int getPP_systolic_blood_pressure(){
        return PP_systolic_blood_pressure;
    }

    public void setPP_systolicBloodPressure(int PP_systolic_blood_pressure){
        this.PP_systolic_blood_pressure = PP_systolic_blood_pressure;
    }    

    public int getPP_ECG(){
        return PP_ECG;
    }

    public void setPPECG(int PP_ECG){
        this.PP_ECG = PP_ECG;
    }    

    public int getPP_artirial_pulse(){
        return PP_artirial_pulse;
    }

    public void setPP_artirialPulse(int PP_artirial_pulse){
        this.PP_artirial_pulse = PP_artirial_pulse;
    }    

    public int getPP_hemoglobin(){
        return PP_hemoglobin;
    }

    public void setPPHemoglobin( int PP_hemoglobin){
        this.PP_hemoglobin = PP_hemoglobin;
    }    

    public int getPP_leukocytes(){
        return PP_leukocytes;
    }

    public void setPPLeukocytes(int PP_leukocytes){
        this.PP_leukocytes = PP_leukocytes;
    }    

    public int getPP_urea(){
        return PP_urea;
    }

    public void setPPUrea(int PP_urea){
        this.PP_urea = PP_urea;
    }    

    public int getPP_sodium(){
        return PP_sodium;
    }

    public void setPPSodium(int PP_sodium){
        this.PP_sodium = PP_sodium;
    }    

    public int getPP_potassium(){
        return PP_potassium;
    }

    public void setPPPotassium(int PP_potassium){
        this.PP_potassium = PP_potassium;
    }    


    public int getPP_Glasgow_scale(){
        return PP_Glasgow_scale;
    }

    public void setPPGlasgowScale(int PP_Glasgow_scale){
        this.PP_Glasgow_scale = PP_Glasgow_scale;
    }    


    public int getPP_operation_type(){
        return PP_operation_type;
    }

    public void setPPOperationType(int PP_operation_type){
        this.PP_operation_type = PP_operation_type;
    }    


    public int getPP_number_of_procedures(){
        return PP_number_of_procedures;
    }

    public void setPPNumberOfProcedures(int PP_number_of_procedures){
        this.PP_number_of_procedures = PP_number_of_procedures;
    }    


    public int getPP_blood_loss(){
        return PP_blood_loss;
    }

    public void setPPBloodLoss( int PP_blood_loss){
        this.PP_blood_loss = PP_blood_loss;
    }    


    public int getPP_peritoneal_contamination(){
        return PP_peritoneal_contamination;
    }

    public void setPPPeritonealContamination(int PP_peritoneal_contamination){
        this.PP_peritoneal_contamination = PP_peritoneal_contamination;
    }    


    public int getPP_state_of_malignancy(){
        return PP_state_of_malignancy;
    }

    public void setPPStateOfMalignancy(int PP_state_of_malignancy){
        this.PP_state_of_malignancy = PP_state_of_malignancy;
    }    


    public int getPP_CEPOD_grading_operation(){
        return PP_CEPOD_grading_operation;
    }

    public void setPPCEPOD_gradingOperation(int PP_CEPOD_grading_operation){
        this.PP_CEPOD_grading_operation = PP_CEPOD_grading_operation;
    }    


    public int getP_Possum_Physiological_Score(){
        return P_Possum_Physiological_Score;
    }

    public void setPPossumPhysiologicalScore(int P_Possum_Physiological_Score){
        this.P_Possum_Physiological_Score = P_Possum_Physiological_Score;
    }    


    public int getP_Possum_Surgical_Severity_Score(){
        return P_Possum_Surgical_Severity_Score;
    }

    public void setPPossumSurgicalSeverityScore(int P_Possum_Surgical_Severity_Score){
        this.P_Possum_Surgical_Severity_Score = P_Possum_Surgical_Severity_Score;
    }    


    public Double getP_Possum_Morbidity_Percentage(){
        return P_Possum_Morbidity_Percentage;
    }

    public void setPPossumMorbidityPercentage(Double P_Possum_Morbidity_Percentage){
        this.P_Possum_Morbidity_Percentage = P_Possum_Morbidity_Percentage;
    }    


    public Double getP_Possum_Mortality_Percentage(){
        return P_Possum_Mortality_Percentage;
    }

    public void setPPossumMortalityPercentage(Double P_Possum_Mortality_Percentage){
        this.P_Possum_Mortality_Percentage = P_Possum_Mortality_Percentage;
    }    

    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }

    @Override
    public String toString() {
        return "ppossumData [PP_CEPOD_grading_operation=" + PP_CEPOD_grading_operation + ", PP_ECG=" + PP_ECG
                + ", PP_Glasgow_scale=" + PP_Glasgow_scale + ", PP_age=" + PP_age + ", PP_artirial_pulse="
                + PP_artirial_pulse + ", PP_blood_loss=" + PP_blood_loss + ", PP_cardiac=" + PP_cardiac
                + ", PP_hemoglobin=" + PP_hemoglobin + ", PP_leukocytes=" + PP_leukocytes + ", PP_number_of_procedures="
                + PP_number_of_procedures + ", PP_operation_type=" + PP_operation_type
                + ", PP_peritoneal_contamination=" + PP_peritoneal_contamination + ", PP_potassium=" + PP_potassium
                + ", PP_respiratory=" + PP_respiratory + ", PP_sodium=" + PP_sodium + ", PP_state_of_malignancy="
                + PP_state_of_malignancy + ", PP_systolic_blood_pressure=" + PP_systolic_blood_pressure + ", PP_urea="
                + PP_urea + ", P_Possum_Morbidity_Percentage=" + P_Possum_Morbidity_Percentage
                + ", P_Possum_Mortality_Percentage=" + P_Possum_Mortality_Percentage + ", P_Possum_Physiological_Score="
                + P_Possum_Physiological_Score + ", P_Possum_Surgical_Severity_Score="
                + P_Possum_Surgical_Severity_Score + ", id=" + id + "]";
    }


}