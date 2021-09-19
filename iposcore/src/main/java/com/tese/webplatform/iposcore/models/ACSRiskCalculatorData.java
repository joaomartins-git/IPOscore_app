package com.tese.webplatform.iposcore.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ACS_Risk_Calculator")
public class ACSRiskCalculatorData implements Serializable{

    private static final long serialVersionUID = 123456789L;

    @Id
    @Column(name = "surgerydata_id")
    private Long id;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "surgerydata_id")
    // @JsonIgnoreProperties({"ariscatData","acsRiskCalculatorData"})
    @JsonIgnore
    private SurgeryData surgerydata;

    @Column(name="ACS_procedure")
    private String ACS_procedure;
    
    @Column(name="ACS_age")
    private int ACS_age;

    @Column(name="ACS_gender")
    private int ACS_gender;

    // @Column(name="ACS_functional_state")
    // private int ACS_functional_state;
    
    @ManyToOne
    @JoinColumn(name="ACS_functional_state")
    private params__ACS_functional_state ACS_functional_state;



    @Column(name="ACS_emergency")
    private int ACS_emergency;

    @Column(name="ACS_ASA")
    private int ACS_ASA;

    @Column(name="ACS_steroids")
    private int ACS_steroids;

    @Column(name="ACS_ascites")
    private int ACS_ascites;

    @Column(name="ACS_systemic_sepsis")
    private int ACS_systemic_sepsis;

    @Column(name="ACS_ventilator_dependent")
    private int ACS_ventilator_dependent;

    @Column(name="ACS_disseminated_cancer")
    private int ACS_disseminated_cancer;

    @Column(name="ACS_diabetes")
    private int ACS_diabetes;

    @Column(name="ACS_hypertension")
    private int ACS_hypertension;

    @Column(name="ACS_ICC")
    private int ACS_ICC;

    @Column(name="ACS_dyspnea")
    private int ACS_dyspnea;

    @Column(name="ACS_smoker")
    private int ACS_smoker;

    @Column(name="ACS_DPOC")
    private int ACS_DPOC;

    @Column(name="ACS_dialysis")
    private int ACS_dialysis;

    @Column(name="ACS_acute_renal_failure")
    private int ACS_acute_renal_failure;

    @Column(name="ACS_height")
    private int ACS_height;

    @Column(name="ACS_weight")
    private Double ACS_weight;

    @Column(name="Serious_complications_percentage")
    private Double Serious_complications_percentage;

    @Column(name="Average_risk_serious_complications")
    private Double Average_risk_serious_complications;

    @Column(name="Any_complications_percentage")
    private Double Any_complications_percentage;

    @Column(name="Average_risk_any_complications")
    private Double Average_risk_any_complications;

    @Column(name="Pneumonia_percentage")
    private Double Pneumonia_percentage;

    @Column(name="Average_risk_pneumonia")
    private Double Average_risk_pneumonia;

    @Column(name="Cardiac_complications_percentage")
    private Double Cardiac_complications_percentage;

    @Column(name="Average_risk_cardiac_complications")
    private Double Average_risk_cardiac_complications;

    @Column(name="Surgical_infection_percentage")
    private Double Surgical_infection_percentage;

    @Column(name="Average_risk_surgical_infection")
    private Double Average_risk_surgical_infection;

    @Column(name="ITU_percentage")
    private Double ITU_percentage;

    @Column(name="Average_risk_ITU")
    private Double Average_risk_ITU;

    @Column(name="Venous_thromboembolism_percentage")
    private Double Venous_thromboembolism_percentage;

    @Column(name="Average_risk_venous_thromboembolism")
    private Double Average_risk_venous_thromboembolism;

    @Column(name="Kidney_failure_percentage")
    private Double Kidney_failure_percentage;

    @Column(name="Average_risk_kidney_failure")
    private Double Average_risk_kidney_failure;

    @Column(name="Ileus_percentage")
    private Double Ileus_percentage;

    @Column(name="Average_risk_ileus")
    private Double Average_risk_ileus;

    @Column(name="Anastomotic_leakage_percentage")
    private Double Anastomotic_leakage_percentage;

    @Column(name="Average_risk_Anastomotic_leakage")
    private Double Average_risk_Anastomotic_leakage;

    @Column(name="Readmission_percentage")
    private Double Readmission_percentage;

    @Column(name="Average_risk_readmission")
    private Double Average_risk_readmission;

    @Column(name="Reoperation_percentage")
    private Double Reoperation_percentage;

    @Column(name="Average_risk_reoperation")
    private Double Average_risk_reoperation;

    @Column(name="Death_percentage")
    private Double Death_percentage;

    @Column(name="Average_risk_death")
    private Double Average_risk_death;

    @Column(name="Discharge_To_Nursing_or_Rehab_Facility_percentage")
    private Double Discharge_To_Nursing_or_Rehab_Facility_percentage;

    @Column(name="Average_risk_discharge_nursing_rehab")
    private Double Average_risk_discharge_nursing_rehab;

    @Column(name="ACS_days_prevision")
    private Double ACS_days_prevision;


    public ACSRiskCalculatorData(){}

    // public ACSRiskCalculatorData(Long id, String ACS_procedure,int ACS_age,int ACS_gender, int ACS_functional_state, boolean ACS_emergency, int ACS_ASA, int ACS_steroids, boolean ACS_ascites, int ACS_systemic_sepsis, boolean ACS_ventilator_dependent, boolean ACS_disseminated_cancer, int ACS_diabetes, boolean ACS_hypertension, boolean ACS_ICC,int ACS_dyspnea, boolean ACS_smoker, boolean ACS_DPOC, boolean ACS_dialysis, boolean ACS_acute_renal_failure, int ACS_height, Double ACS_weight,  Double Serious_complications_percentage,  Double Average_risk_serious_complications, Double Any_complications_percentage, Double Average_risk_any_complications, Double Pneumonia_percentage, Double Average_risk_pneumonia, Double Cardiac_complications_percentage, Double Average_risk_cardiac_complications, Double Surgical_infection_percentage, Double Average_risk_surgical_infection, Double ITU_percentage, Double Average_risk_ITU, Double Venous_thromboembolism_percentage, Double Average_risk_venous_thromboembolism, Double Kidney_failure_percentage, Double Average_risk_kidney_failure, Double Ileus_percentage, Double Average_risk_ileus, Double Anastomotic_leakage_percentage, Double Average_risk_Anastomotic_leakage, Double Readmission_percentage, Double Average_risk_readmission, Double Reoperation_percentage, Double Average_risk_reoperation, Double Death_percentage, Double Average_risk_death, Double Discharge_To_Nursing_or_Rehab_Facility_percentage, Double Average_risk_discharge_nursing_rehab, Double ACS_days_prevision){

    //     this.id = id;
    //     this.ACS_procedure = ACS_procedure;
    //     this.ACS_age = ACS_age;
    //     this.ACS_gender = ACS_gender;
    //     this.ACS_functional_state = ACS_functional_state;
    //     this.ACS_emergency = ACS_emergency;
    //     this.ACS_ASA = ACS_ASA;
    //     this.ACS_ascites = ACS_ascites;
    //     this.ACS_systemic_sepsis = ACS_systemic_sepsis;
    //     this.ACS_ventilator_dependent = ACS_ventilator_dependent;
    //     this.ACS_disseminated_cancer = ACS_disseminated_cancer;
    //     this.ACS_diabetes = ACS_diabetes;
    //     this.ACS_hypertension = ACS_hypertension;
    //     this.ACS_ICC = ACS_ICC;
    //     this.ACS_dyspnea = ACS_dyspnea;
    //     this.ACS_smoker = ACS_smoker;
    //     this.ACS_DPOC = ACS_DPOC;
    //     this.ACS_dialysis = ACS_dialysis;
    //     this.ACS_acute_renal_failure = ACS_acute_renal_failure;
    //     this.ACS_height = ACS_height;
    //     this.ACS_weight = ACS_weight;
    //     this.Serious_complications_percentage = Serious_complications_percentage;
    //     this.Average_risk_serious_complications = Average_risk_serious_complications;
    //     this.Any_complications_percentage = Any_complications_percentage;
    //     this.Average_risk_any_complications = Average_risk_any_complications;
    //     this.Pneumonia_percentage = Pneumonia_percentage;
    //     this.Average_risk_pneumonia = Average_risk_pneumonia;
    //     this.Cardiac_complications_percentage = Cardiac_complications_percentage;
    //     this.Average_risk_cardiac_complications = Average_risk_cardiac_complications;
    //     this.Surgical_infection_percentage = Surgical_infection_percentage;
    //     this.Average_risk_surgical_infection = Average_risk_surgical_infection;
    //     this.ITU_percentage = ITU_percentage;
    //     this.Average_risk_ITU = Average_risk_ITU;
    //     this.Venous_thromboembolism_percentage = Venous_thromboembolism_percentage;
    //     this.Average_risk_venous_thromboembolism = Average_risk_venous_thromboembolism;
    //     this.Kidney_failure_percentage = Kidney_failure_percentage;
    //     this.Average_risk_kidney_failure = Average_risk_kidney_failure;
    //     this.Ileus_percentage = Ileus_percentage;
    //     this.Average_risk_ileus = Average_risk_ileus;
    //     this.Anastomotic_leakage_percentage = Anastomotic_leakage_percentage;
    //     this.Average_risk_Anastomotic_leakage = Average_risk_Anastomotic_leakage;
    //     this.Readmission_percentage = Readmission_percentage;
    //     this.Average_risk_readmission = Average_risk_readmission;
    //     this.Reoperation_percentage = Reoperation_percentage;
    //     this.Average_risk_reoperation = Average_risk_reoperation;
    //     this.Death_percentage = Death_percentage;
    //     this.Average_risk_death = Average_risk_death;
    //     this.Discharge_To_Nursing_or_Rehab_Facility_percentage = Discharge_To_Nursing_or_Rehab_Facility_percentage;
    //     this.Average_risk_discharge_nursing_rehab = Average_risk_discharge_nursing_rehab;
    //     this.ACS_days_prevision = ACS_days_prevision;

    // }

    public ACSRiskCalculatorData(Long id, String ACS_procedure,int ACS_age,int ACS_gender, params__ACS_functional_state ACS_functional_state, int ACS_emergency, int ACS_ASA, int ACS_steroids, int ACS_ascites, int ACS_systemic_sepsis, int ACS_ventilator_dependent, int ACS_disseminated_cancer, int ACS_diabetes, int ACS_hypertension, int ACS_ICC, int ACS_dyspnea, int ACS_smoker, int ACS_DPOC, int ACS_dialysis, int ACS_acute_renal_failure, int ACS_height, Double ACS_weight,  Double Serious_complications_percentage,  Double Average_risk_serious_complications, Double Any_complications_percentage, Double Average_risk_any_complications, Double Pneumonia_percentage, Double Average_risk_pneumonia, Double Cardiac_complications_percentage, Double Average_risk_cardiac_complications, Double Surgical_infection_percentage, Double Average_risk_surgical_infection, Double ITU_percentage, Double Average_risk_ITU, Double Venous_thromboembolism_percentage, Double Average_risk_venous_thromboembolism, Double Kidney_failure_percentage, Double Average_risk_kidney_failure, Double Ileus_percentage, Double Average_risk_ileus, Double Anastomotic_leakage_percentage, Double Average_risk_Anastomotic_leakage, Double Readmission_percentage, Double Average_risk_readmission, Double Reoperation_percentage, Double Average_risk_reoperation, Double Death_percentage, Double Average_risk_death, Double Discharge_To_Nursing_or_Rehab_Facility_percentage, Double Average_risk_discharge_nursing_rehab, Double ACS_days_prevision){

        this.id = id;
        this.ACS_procedure = ACS_procedure;
        this.ACS_age = ACS_age;
        this.ACS_gender = ACS_gender;
        this.ACS_functional_state = ACS_functional_state;
        this.ACS_emergency = ACS_emergency;
        this.ACS_ASA = ACS_ASA;
        this.ACS_ascites = ACS_ascites;
        this.ACS_systemic_sepsis = ACS_systemic_sepsis;
        this.ACS_ventilator_dependent = ACS_ventilator_dependent;
        this.ACS_disseminated_cancer = ACS_disseminated_cancer;
        this.ACS_diabetes = ACS_diabetes;
        this.ACS_hypertension = ACS_hypertension;
        this.ACS_ICC = ACS_ICC;
        this.ACS_dyspnea = ACS_dyspnea;
        this.ACS_smoker = ACS_smoker;
        this.ACS_DPOC = ACS_DPOC;
        this.ACS_dialysis = ACS_dialysis;
        this.ACS_acute_renal_failure = ACS_acute_renal_failure;
        this.ACS_height = ACS_height;
        this.ACS_weight = ACS_weight;
        this.Serious_complications_percentage = Serious_complications_percentage;
        this.Average_risk_serious_complications = Average_risk_serious_complications;
        this.Any_complications_percentage = Any_complications_percentage;
        this.Average_risk_any_complications = Average_risk_any_complications;
        this.Pneumonia_percentage = Pneumonia_percentage;
        this.Average_risk_pneumonia = Average_risk_pneumonia;
        this.Cardiac_complications_percentage = Cardiac_complications_percentage;
        this.Average_risk_cardiac_complications = Average_risk_cardiac_complications;
        this.Surgical_infection_percentage = Surgical_infection_percentage;
        this.Average_risk_surgical_infection = Average_risk_surgical_infection;
        this.ITU_percentage = ITU_percentage;
        this.Average_risk_ITU = Average_risk_ITU;
        this.Venous_thromboembolism_percentage = Venous_thromboembolism_percentage;
        this.Average_risk_venous_thromboembolism = Average_risk_venous_thromboembolism;
        this.Kidney_failure_percentage = Kidney_failure_percentage;
        this.Average_risk_kidney_failure = Average_risk_kidney_failure;
        this.Ileus_percentage = Ileus_percentage;
        this.Average_risk_ileus = Average_risk_ileus;
        this.Anastomotic_leakage_percentage = Anastomotic_leakage_percentage;
        this.Average_risk_Anastomotic_leakage = Average_risk_Anastomotic_leakage;
        this.Readmission_percentage = Readmission_percentage;
        this.Average_risk_readmission = Average_risk_readmission;
        this.Reoperation_percentage = Reoperation_percentage;
        this.Average_risk_reoperation = Average_risk_reoperation;
        this.Death_percentage = Death_percentage;
        this.Average_risk_death = Average_risk_death;
        this.Discharge_To_Nursing_or_Rehab_Facility_percentage = Discharge_To_Nursing_or_Rehab_Facility_percentage;
        this.Average_risk_discharge_nursing_rehab = Average_risk_discharge_nursing_rehab;
        this.ACS_days_prevision = ACS_days_prevision;

    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
    public String getACS_Procedure(){
        return ACS_procedure;
    }

    public void setACS_Procedure(String ACS_procedure){
        this.ACS_procedure = ACS_procedure;
    }
    
    public int getACS_age(){
        return ACS_age;
    }

    public void setACS_Age(int ACS_age){
        this.ACS_age = ACS_age;
    }
    
    public int getACS_gender(){
        return ACS_gender;
    }

    public void setACS_gender(int ACS_gender){
        this.ACS_gender = ACS_gender;
    }
    
    public params__ACS_functional_state getACS_functional_state(){
        return ACS_functional_state;
    }

    public void setACS_functional_state( params__ACS_functional_state ACS_functional_state){
        this.ACS_functional_state = ACS_functional_state;
    }
    
    // public int getACS_functionalState(){
    //     return ACS_functional_state;
    // }

    // public void setACS_functionalState( int ACS_functional_state){
    //     this.ACS_functional_state = ACS_functional_state;
    // }
    
    public int getACS_emergency(){
        return ACS_emergency;
    }

    public void setACS_emergency(int ACS_emergency){
        this.ACS_emergency = ACS_emergency;
    }
    
    public int getACS_ASA(){
        return ACS_ASA;
    }

    public void setACS_ASA(int ACS_ASA){
        this.ACS_ASA = ACS_ASA;
    }
    
    public int getACS_steroids(){
        return ACS_steroids;
    }

    public void setACS_steroids(int ACS_steroids){
        this.ACS_steroids = ACS_steroids;
    }
    
    public int getACS_ascites(){
        return ACS_ascites;
    }

    public void setACS_ascites(int ACS_ascites){
        this.ACS_ascites = ACS_ascites;
    }
    
    public int getACS_systemic_sepsis(){
        return ACS_systemic_sepsis;
    }

    public void setACS_systemic_sepsis(int ACS_systemic_sepsis){
        this.ACS_systemic_sepsis = ACS_systemic_sepsis;
    }
    
    public int getACS_ventilator_dependent(){
        return ACS_ventilator_dependent;
    }

    public void setACS_ventilator_dependent(int ACS_ventilator_dependent){
        this.ACS_ventilator_dependent = ACS_ventilator_dependent;
    }
    
    public int getACS_disseminated_cancer(){
        return ACS_disseminated_cancer;
    }

    public void setACS_disseminated_cancer(int ACS_disseminated_cancer){
        this.ACS_disseminated_cancer = ACS_disseminated_cancer;
    }
    
    public int getACS_diabetes(){
        return ACS_diabetes;
    }

    public void setACS_diabetes(int ACS_diabetes){
        this.ACS_diabetes = ACS_diabetes;
    }
    
    public int getACS_hypertension(){
        return ACS_hypertension;
    }

    public void setACS_hypertension(int ACS_hypertension){
        this.ACS_hypertension = ACS_hypertension;
    }
    
    public int getACS_ICC(){
        return ACS_ICC;
    }

    public void setACS_ICC(int ACS_ICC){
        this.ACS_ICC = ACS_ICC;
    }
    
    public int getACS_dyspnea(){
        return ACS_dyspnea;
    }

    public void setACS_dyspnea(int ACS_dyspnea){
        this.ACS_dyspnea = ACS_dyspnea;
    }
    
    public int getACS_smoker(){
        return ACS_smoker;
    }

    public void setACS_smoker(int ACS_smoker){
        this.ACS_smoker = ACS_smoker;
    }
    
    public int getACS_DPOC(){
        return ACS_DPOC;
    }

    public void setACS_DPOC(int ACS_DPOC){
        this.ACS_DPOC = ACS_DPOC;
    }
    
    public int getACS_dialysis(){
        return ACS_dialysis;
    }

    public void setACS_dialysis(int ACS_dialysis){
        this.ACS_dialysis = ACS_dialysis;
    }
    
    public int getACS_acute_renal_failure(){
        return ACS_acute_renal_failure;
    }

    public void setACS_acute_renal_failure(int ACS_acute_renal_failure){
        this.ACS_acute_renal_failure = ACS_acute_renal_failure;
    }
    
    public int getACS_height(){
        return ACS_height;
    }

    public void setACS_height(int ACS_height){
        this.ACS_height = ACS_height;
    }
    
    public Double getACS_weight(){
        return ACS_weight;
    }

    public void setACS_weight(Double ACS_weight){
        this.ACS_weight = ACS_weight;
    }
    
    public Double getSerious_complicationsPercentage(){
        return Serious_complications_percentage;
    }

    public void setSerious_complicationsPercentage(Double Serious_complications_percentage){
        this.Serious_complications_percentage = Serious_complications_percentage;
    }
    
    public Double getAverage_risk_seriousComplications(){
        return Average_risk_serious_complications;
    }

    public void setAverage_risk_seriousComplications(Double Average_risk_serious_complications){
        this.Average_risk_serious_complications = Average_risk_serious_complications;
    }
    
    public Double getAny_complicationsPercentage(){
        return Any_complications_percentage;
    }

    public void setAny_complicationsPercentage(Double Any_complications_percentage){
        this.Any_complications_percentage = Any_complications_percentage;
    }
    
    public Double getAverage_risk_anyComplications(){
        return Average_risk_any_complications;
    }

    public void setAverage_risk_anyComplications(Double Average_risk_any_complications){
        this.Average_risk_any_complications = Average_risk_any_complications;
    }
    
    public Double getPneumonia_percentage(){
        return Pneumonia_percentage;
    }

    public void setPneumonia_percentage(Double Pneumonia_percentage){
        this.Pneumonia_percentage = Pneumonia_percentage;
    }
    
    public Double getAverage_riskPneumonia(){
        return Average_risk_pneumonia;
    }

    public void setAverage_riskPneumonia(Double Average_risk_pneumonia){
        this.Average_risk_pneumonia = Average_risk_pneumonia;
    }
    
    public Double getCardiac_complicationsPercentage(){
        return Cardiac_complications_percentage;
    }

    public void setCardiac_complicationsPercentage(Double Cardiac_complications_percentage){
        this.Cardiac_complications_percentage = Cardiac_complications_percentage;
    }
    
    public Double getAverage_risk_cardiacComplications(){
        return Average_risk_cardiac_complications;
    }

    public void setAverage_risk_cardiacComplications(Double Average_risk_cardiac_complications){
        this.Average_risk_cardiac_complications = Average_risk_cardiac_complications;
    }
    
    public Double getSurgical_infectionPercentage(){
        return Surgical_infection_percentage;
    }

    public void setSurgical_infectionPercentage(Double Surgical_infection_percentage){
        this.Surgical_infection_percentage = Surgical_infection_percentage;
    }
    
    public Double getAverage_riskSurgicalInfection(){
        return Average_risk_surgical_infection;
    }

    public void setAverage_riskSurgicalInfection(Double Average_risk_surgical_infection){
        this.Average_risk_surgical_infection = Average_risk_surgical_infection;
    }
    
    public Double getITU_percentage(){
        return ITU_percentage;
    }

    public void setITU_percentage(Double ITU_percentage){
        this.ITU_percentage = ITU_percentage;
    }
    
    public Double getAverage_riskITU(){
        return Average_risk_ITU;
    }

    public void setAverage_riskITU(Double Average_risk_ITU){
        this.Average_risk_ITU = Average_risk_ITU;
    }
    
    public Double getVenous_thromboembolismPercentage(){
        return Venous_thromboembolism_percentage;
    }

    public void setVenous_thromboembolismPercentage(Double Venous_thromboembolism_percentage){
        this.Venous_thromboembolism_percentage = Venous_thromboembolism_percentage;
    }
    
    public Double getAverage_riskVenousThromboembolism(){
        return Average_risk_venous_thromboembolism;
    }

    public void setAverage_riskVenousThromboembolism(Double Average_risk_venous_thromboembolism){
        this.Average_risk_venous_thromboembolism = Average_risk_venous_thromboembolism;
    }
    
    public Double getKidney_failurePercentage(){
        return Kidney_failure_percentage;
    }

    public void setKidney_failurePercentage(Double Kidney_failure_percentage){
        this.Kidney_failure_percentage = Kidney_failure_percentage;
    }
    
    public Double getAverage_riskKidneyFailure(){
        return Average_risk_kidney_failure;
    }

    public void setAverage_riskKidneyFailure(Double Average_risk_kidney_failure){
        this.Average_risk_kidney_failure = Average_risk_kidney_failure;
    }
    
    public Double getIleus_percentage(){
        return Ileus_percentage;
    }

    public void setIleus_percentage(Double Ileus_percentage){
        this.Ileus_percentage = Ileus_percentage;
    }
    
    public Double getAverage_riskIleus(){
        return Average_risk_ileus;
    }

    public void setAverage_riskIleus(Double Average_risk_ileus){
        this.Average_risk_ileus = Average_risk_ileus;
    }
    
    public Double getAnastomotic_leakagePercentage(){
        return Anastomotic_leakage_percentage;
    }

    public void setAnastomotic_leakagePercentage(Double Anastomotic_leakage_percentage){
        this.Anastomotic_leakage_percentage = Anastomotic_leakage_percentage;
    }
    
    public Double getAverage_riskAnastomoticLeakage(){
        return Average_risk_Anastomotic_leakage;
    }

    public void setAverage_riskAnastomoticLeakage(Double Average_risk_Anastomotic_leakage){
        this.Average_risk_Anastomotic_leakage = Average_risk_Anastomotic_leakage;
    }
    
    public Double getReadmission_percentage(){
        return Readmission_percentage;
    }

    public void setReadmission_percentage(Double Readmission_percentage){
        this.Readmission_percentage = Readmission_percentage;
    }
    
    public Double getAverage_riskReadmission(){
        return Average_risk_readmission;
    }

    public void setAverage_riskReadmission(Double Average_risk_readmission){
        this.Average_risk_readmission = Average_risk_readmission;
    }
    
    public Double getReoperation_percentage(){
        return Reoperation_percentage;
    }

    public void setReoperation_percentage(Double Reoperation_percentage){
        this.Reoperation_percentage = Reoperation_percentage;
    }
    
    public Double getAverage_riskReoperation(){
        return Average_risk_reoperation;
    }

    public void setAverage_riskReoperation(Double Average_risk_reoperation){
        this.Average_risk_reoperation = Average_risk_reoperation;
    }
    
    public Double getDeath_percentage(){
        return Death_percentage;
    }

    public void setDeath_percentage(Double Death_percentage){
        this.Death_percentage = Death_percentage;
    }

    public Double getAverage_riskDeath(){
        return Average_risk_death;
    }

    public void setAverage_riskDeath(Double Average_risk_death){
        this.Average_risk_death = Average_risk_death;
    }

    public Double getDischarge_To_Nursing_or_Rehab_Facility_percentage(){
        return Discharge_To_Nursing_or_Rehab_Facility_percentage;
    }

    public void setDischarge_To_Nursing_or_Rehab_Facility_percentage(Double Discharge_To_Nursing_or_Rehab_Facility_percentage){
        this.Discharge_To_Nursing_or_Rehab_Facility_percentage = Discharge_To_Nursing_or_Rehab_Facility_percentage;
    }

    public Double getAverage_risk_discharge_nursing_rehab(){
        return Average_risk_discharge_nursing_rehab;
    }

    public void setAverage_risk_discharge_nursing_rehab(Double Average_risk_discharge_nursing_rehab){
        this.Average_risk_discharge_nursing_rehab = Average_risk_discharge_nursing_rehab;
    }
    

    public Double getACS_daysPrevision(){
        return ACS_days_prevision;
    }

    public void setACS_daysPrevision(Double ACS_days_prevision){
        this.ACS_days_prevision = ACS_days_prevision;
    }

    
    public SurgeryData getSurgerydata() {
        return surgerydata;
    }
    
    public void setSurgerydata(SurgeryData surgerydata) {
        this.surgerydata = surgerydata;
    }

    

    // @Override
    // public String toString() {
    //     return "ACSRiskCalculatorData [ACS_ASA=" + ACS_ASA + ", ACS_DPOC=" + ACS_DPOC + ", ACS_ICC=" + ACS_ICC
    //             + ", ACS_acute_renal_failure=" + ACS_acute_renal_failure + ", ACS_age=" + ACS_age + ", ACS_ascites="
    //             + ACS_ascites + ", ACS_days_prevision=" + ACS_days_prevision + ", ACS_diabetes=" + ACS_diabetes
    //             + ", ACS_dialysis=" + ACS_dialysis + ", ACS_disseminated_cancer=" + ACS_disseminated_cancer
    //             + ", ACS_dyspnea=" + ACS_dyspnea + ", ACS_emergency=" + ACS_emergency + ", ACS_functional_state="
    //             + ACS_functional_state + ", ACS_gender=" + ACS_gender + ", ACS_height=" + ACS_height
    //             + ", ACS_hypertension=" + ACS_hypertension + ", ACS_procedure=" + ACS_procedure + ", ACS_smoker="
    //             + ACS_smoker + ", ACS_steroids=" + ACS_steroids + ", ACS_systemic_sepsis=" + ACS_systemic_sepsis
    //             + ", ACS_ventilator_dependent=" + ACS_ventilator_dependent + ", ACS_weight=" + ACS_weight
    //             + ", Anastomotic_leakage_percentage=" + Anastomotic_leakage_percentage
    //             + ", Any_complications_percentage=" + Any_complications_percentage
    //             + ", Average_risk_Anastomotic_leakage=" + Average_risk_Anastomotic_leakage + ", Average_risk_ITU="
    //             + Average_risk_ITU + ", Average_risk_any_complications=" + Average_risk_any_complications
    //             + ", Average_risk_cardiac_complications=" + Average_risk_cardiac_complications + ", Average_risk_death="
    //             + Average_risk_death + ", Average_risk_discharge_nursing_rehab=" + Average_risk_discharge_nursing_rehab
    //             + ", Average_risk_ileus=" + Average_risk_ileus + ", Average_risk_kidney_failure="
    //             + Average_risk_kidney_failure + ", Average_risk_pneumonia=" + Average_risk_pneumonia
    //             + ", Average_risk_readmission=" + Average_risk_readmission + ", Average_risk_reoperation="
    //             + Average_risk_reoperation + ", Average_risk_serious_complications="
    //             + Average_risk_serious_complications + ", Average_risk_surgical_infection="
    //             + Average_risk_surgical_infection + ", Average_risk_venous_thromboembolism="
    //             + Average_risk_venous_thromboembolism + ", Cardiac_complications_percentage="
    //             + Cardiac_complications_percentage + ", Death_percentage=" + Death_percentage
    //             + ", Discharge_To_Nursing_or_Rehab_Facility_percentage="
    //             + Discharge_To_Nursing_or_Rehab_Facility_percentage + ", ITU_percentage=" + ITU_percentage
    //             + ", Ileus_percentage=" + Ileus_percentage + ", Kidney_failure_percentage=" + Kidney_failure_percentage
    //             + ", Pneumonia_percentage=" + Pneumonia_percentage + ", Readmission_percentage="
    //             + Readmission_percentage + ", Reoperation_percentage=" + Reoperation_percentage
    //             + ", Serious_complications_percentage=" + Serious_complications_percentage
    //             + ", Surgical_infection_percentage=" + Surgical_infection_percentage
    //             + ", Venous_thromboembolism_percentage=" + Venous_thromboembolism_percentage + ", id=" + id + "]";
    // }
    


}