package com.tese.webplatform.iposcore.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Patients")
public class PatientData implements Serializable{

    private static final long serialVersionUID = 876688928410084519L;

    @Id
    @Column(name= "IPONumber")
    private Long ipoNumber;

    @Column(name= "Age")
    private Long age;

    // @JsonIgnoreProperties(value = "lstSurgeryDatas", allowSetters = true)
   
    @ManyToOne
    @JoinColumn(name= "Gender")
    private params__gender gender;

    // @Column(name= "Gender")
    // private int gender;

    @OneToMany(mappedBy = "patientNumber", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("surgeryDate ASC")
    private Set<SurgeryData> lstSurgeryDatas = new HashSet<>();

    public PatientData(){}

    // public PatientData(Long ipoNumber, Long age, int gender){
    //     this.ipoNumber = ipoNumber;
    //     this.age = age;
    //     this.gender = gender;
    // }

    public PatientData(Long ipoNumber, Long age, params__gender gender){
        this.ipoNumber = ipoNumber;
        this.age = age;
        this.gender = gender;
    }

    public Long getIpoNumber(){
        return ipoNumber;
    }
    public Long getAge(){
        return age;
    }
    // public int getGender(){
    //     return gender;
    // }

    public params__gender getGender(){
        return gender;
    }


    public void setIpoNumber(Long ipoNumber){
        this.ipoNumber = ipoNumber;
    }

    public void setAge(Long age){
        this.age = age;
    }

    // public void setGender(int gender){
    //     this.gender = gender;
    // }
    
    public void setGender(params__gender gender){
        this.gender = gender;
    }

    public Set<SurgeryData> getLstSurgeryDatas() {
        return lstSurgeryDatas;
    }

    public void setLstSurgeryDatas(Set<SurgeryData> surgeryDatas) {
        this.lstSurgeryDatas = surgeryDatas;
    }

    public PatientData lstSurgeryDatas(Set<SurgeryData> surgeryDatas) {
        this.lstSurgeryDatas = surgeryDatas;

        return this;
    }

    public PatientData addLstSurgeryDatas(SurgeryData surgeryData){
        this.lstSurgeryDatas.add(surgeryData);
        surgeryData.setPatientNumber(this);
        System.err.println(this.lstSurgeryDatas);
        System.err.println(this);

        return this;
    }

    public PatientData removeLstSurgeryDatas(SurgeryData surgeryData){
        this.lstSurgeryDatas.remove(surgeryData);
        surgeryData.setPatientNumber(null);

        return this;
    }

    // @Override
    // public String toString(){
    //     return "Patient [IPO Number=" + ipoNumber + ", Age="+age+", Gender="+gender+"]";
    // }




}
