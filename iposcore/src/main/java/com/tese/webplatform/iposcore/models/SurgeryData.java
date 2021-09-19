package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Surgery_Data")
public class SurgeryData implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Surgery_Date")
    private Date surgeryDate;
    
    @ManyToOne
    @JoinColumn(name = "patientnumber", nullable = false)
    @JsonIgnoreProperties(value = "lstSurgeryDatas", allowSetters = true)
    private PatientData patientNumber;
    
    @Column(name = "Patient_provenace")
    private Long patientProvenace;

    @Column(name = "Surgery_location") 
    private String surgeryLocation;

    @Column(name = "Preoperative_diagnosis") 
    private String preoperativeDiagnosis;

    @Column(name = "Operation_performed") 
    private String operationPerformed;

    @Column(name = "Interventions_ICD10")
    private String interventionsICD10;

    @Column(name = "procedures_COD")
    private String proceduresCOD;

    @Column(name="ASA")
    private int ASA;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private UCIAdmissionApplicationData uciAdmissionData;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private internmentCaracteristicsData internmentCaracteristics;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private ppossumData PpossumData;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private ACSRiskCalculatorData acsRiskCalculatorData;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private ARISCATData ariscatData;
    
    @OneToOne(mappedBy = "surgerydata", optional = true)
    private PostSurgicalComplicationsData postsurgicalData;

    @OneToOne(mappedBy = "surgerydata", optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
    private HospitalDischargeData hospitalDischargeInfo;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private PreoperativeComorbiditiesData comorbiditiesData;

    @OneToOne(mappedBy = "surgerydata", optional = true)
    private CharlsonData charlsonData;


    public SurgeryData() {}

    public SurgeryData(Date surgeryDate, PatientData patientNumber, Long patientProvenace, String surgeryLocation, String preoperativeDiagnosis, String operationPerformed, String interventionsICD10, String proceduresCOD, Long id) {
        this.surgeryDate = surgeryDate;
        this.patientNumber = patientNumber;
        this.patientProvenace = patientProvenace;
        this.surgeryLocation = surgeryLocation;
        this.preoperativeDiagnosis = preoperativeDiagnosis;
        this.operationPerformed = operationPerformed;
        this.interventionsICD10 = interventionsICD10;
        this.proceduresCOD = proceduresCOD;
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Date getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(Date surgeryDate) {
        this.surgeryDate = surgeryDate;
    }
    
    public PatientData getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(PatientData patientNumber) {
        this.patientNumber = patientNumber;
    }
    
    public Long getPatientProvenace() {
        return patientProvenace;
    }

    public void setPatientProvenace(Long patientProvenace) {
        this.patientProvenace = patientProvenace;
    }
    
    public String getSurgeryLocation() {
        return surgeryLocation;
    }
    public void setSurgeryLocation(String surgeryLocation) {
        this.surgeryLocation = surgeryLocation;
    }

    public String getPreoperativeDiagnosis() {
        return preoperativeDiagnosis;
    }
    public void setPreoperativeDiagnosis(String preoperativeDiagnosis) {
        this.preoperativeDiagnosis = preoperativeDiagnosis;
    }

    public String getOperationPerformed() {
        return operationPerformed;
    }
    public void setOperationPerformed(String operationPerformed) {
        this.operationPerformed = operationPerformed;
    }
    
    public String getInterventionsICD10() {
        return interventionsICD10;
    }
    public void setInterventionsICD10(String interventionsICD10) {
        this.interventionsICD10 = interventionsICD10;
    }

    public String getProceduresCOD() {
        return proceduresCOD;
    }
    public void setProceduresCOD(String proceduresCOD) {
        this.proceduresCOD = proceduresCOD;
    }

    public int getASA() {
        return ASA;
    }

    public void setASA(int ASA) {
        this.ASA = ASA;
    }

    public UCIAdmissionApplicationData getUciAdmissionData() {
        return uciAdmissionData;
    }

    public void setUciAdmissionData(UCIAdmissionApplicationData uciAdmissionData) {
        this.uciAdmissionData = uciAdmissionData;
    }

    public internmentCaracteristicsData getInternmentCaracteristics() {
        return internmentCaracteristics;
    }

    public void setInternmentCaracteristics(internmentCaracteristicsData internmentCaracteristics) {
        this.internmentCaracteristics = internmentCaracteristics;
    }

    public ACSRiskCalculatorData getAcsRiskCalculatorData() {
        return acsRiskCalculatorData;
    }

    public void setAcsRiskCalculatorData(ACSRiskCalculatorData acsRiskCalculatorData) {
        this.acsRiskCalculatorData = acsRiskCalculatorData;
    }

    public ARISCATData getAriscatData() {
        return ariscatData;
    }

    public void setAriscatData(ARISCATData ariscatData) {
        this.ariscatData = ariscatData;
    }

    public ppossumData getPpossumData() {
        return PpossumData;
    }

    public void setPpossumData(ppossumData ppossumData) {
        PpossumData = ppossumData;
    }

    public PostSurgicalComplicationsData getPostsurgicalData() {
        return postsurgicalData;
    }

    public void setPostsurgicalData(PostSurgicalComplicationsData postsurgicalData) {
        this.postsurgicalData = postsurgicalData;
    }

    public HospitalDischargeData getHospitalDischargeInfo() {
        return hospitalDischargeInfo;
    }

    public void setHospitalDischargeInfo(HospitalDischargeData hospitalDischargeInfo) {
        this.hospitalDischargeInfo = hospitalDischargeInfo;
    }

    public PreoperativeComorbiditiesData getComorbiditiesData() {
        return comorbiditiesData;
    }

    public void setComorbiditiesData(PreoperativeComorbiditiesData comorbiditiesData) {
        this.comorbiditiesData = comorbiditiesData;
    }

    public CharlsonData getCharlsonData() {
        return charlsonData;
    }

    public void setCharlsonData(CharlsonData charlsonData) {
        this.charlsonData = charlsonData;
    }
    

    @Override
    public String toString() {
        return "SurgeryData [id=" + id + ", interventionsICD10=" + interventionsICD10 + ", operationPerformed="
                + operationPerformed + ", patientProvenace=" + patientProvenace + ", preoperativeDiagnosis="
                + preoperativeDiagnosis + ", proceduresCOD=" + proceduresCOD + ", surgeryDate=" + surgeryDate
                + ", surgeryLocation=" + surgeryLocation + "]";
    }



}