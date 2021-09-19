package com.tese.webplatform.iposcore.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;
import com.tese.webplatform.iposcore.repositories.*;
import com.tese.webplatform.iposcore.errors.*;

@RestController
@CrossOrigin()
public class ACSRiskCalculatorDataController{


    @Autowired
    public ACSRiskCalculatorDataRepository ACSRiskCalculatorData;

    public ACSRiskCalculatorDataController(ACSRiskCalculatorDataRepository ACSRiskCalculatorData){
        this.ACSRiskCalculatorData = ACSRiskCalculatorData;
    }

    @GetMapping("/ACSRiskCalculatorDataInfo")
    public List<ACSRiskCalculatorData> getACSRiskCalculatorData() {
        return ACSRiskCalculatorData.findAll();
    }

    @GetMapping("/ACSRiskCalculatorDataInfo/{id}")
    public ACSRiskCalculatorData findACSRiskCalculatorDataById(@PathVariable Long id) {
        return ACSRiskCalculatorData.findById(id).orElseThrow(ACSRiskCalculatorDataNotFountException::new);
    }

    
    @PostMapping("/ACSRiskCalculatorDataInfo")
    public ACSRiskCalculatorData addACSRiskCalculatorData(@RequestBody ACSRiskCalculatorData newACSRiskCalculatorData) {
        return ACSRiskCalculatorData.save(newACSRiskCalculatorData);
    }
    
    @PutMapping("/ACSRiskCalculatorDataInfo/{id}")
    public ACSRiskCalculatorData updateACSRiskCalculatorDataInfo(@PathVariable Long id, @RequestBody ACSRiskCalculatorData newACSRiskCalculatorData) {
        ACSRiskCalculatorData ACSRiskCalculatorDataInfoUpdate = ACSRiskCalculatorData.findById(id).orElseThrow(ACSRiskCalculatorDataNotFountException::new);
     
        ACSRiskCalculatorDataInfoUpdate.setId(newACSRiskCalculatorData.getId());
       
        ACSRiskCalculatorDataInfoUpdate.setACS_Procedure(newACSRiskCalculatorData.getACS_Procedure());
        ACSRiskCalculatorDataInfoUpdate.setACS_Age(newACSRiskCalculatorData.getACS_age());
        ACSRiskCalculatorDataInfoUpdate.setACS_gender(newACSRiskCalculatorData.getACS_gender());
        ACSRiskCalculatorDataInfoUpdate.setACS_functional_state(newACSRiskCalculatorData.getACS_functional_state());
        ACSRiskCalculatorDataInfoUpdate.setACS_emergency(newACSRiskCalculatorData.getACS_emergency());
        ACSRiskCalculatorDataInfoUpdate.setACS_ASA(newACSRiskCalculatorData.getACS_ASA());
        ACSRiskCalculatorDataInfoUpdate.setACS_steroids(newACSRiskCalculatorData.getACS_steroids());
        ACSRiskCalculatorDataInfoUpdate.setACS_ascites(newACSRiskCalculatorData.getACS_ascites());
        ACSRiskCalculatorDataInfoUpdate.setACS_systemic_sepsis(newACSRiskCalculatorData.getACS_systemic_sepsis());
        ACSRiskCalculatorDataInfoUpdate.setACS_ventilator_dependent(newACSRiskCalculatorData.getACS_ventilator_dependent());
        ACSRiskCalculatorDataInfoUpdate.setACS_disseminated_cancer(newACSRiskCalculatorData.getACS_disseminated_cancer());
        ACSRiskCalculatorDataInfoUpdate.setACS_diabetes(newACSRiskCalculatorData.getACS_diabetes());
        ACSRiskCalculatorDataInfoUpdate.setACS_hypertension(newACSRiskCalculatorData.getACS_hypertension());
        ACSRiskCalculatorDataInfoUpdate.setACS_ICC(newACSRiskCalculatorData.getACS_ICC());
        ACSRiskCalculatorDataInfoUpdate.setACS_dyspnea(newACSRiskCalculatorData.getACS_dyspnea());
        ACSRiskCalculatorDataInfoUpdate.setACS_smoker(newACSRiskCalculatorData.getACS_smoker());
        ACSRiskCalculatorDataInfoUpdate.setACS_DPOC(newACSRiskCalculatorData.getACS_DPOC());
        ACSRiskCalculatorDataInfoUpdate.setACS_dialysis(newACSRiskCalculatorData.getACS_dialysis());
        ACSRiskCalculatorDataInfoUpdate.setACS_acute_renal_failure(newACSRiskCalculatorData.getACS_acute_renal_failure());
        ACSRiskCalculatorDataInfoUpdate.setACS_height(newACSRiskCalculatorData.getACS_height());
        ACSRiskCalculatorDataInfoUpdate.setACS_weight(newACSRiskCalculatorData.getACS_weight());
        ACSRiskCalculatorDataInfoUpdate.setSerious_complicationsPercentage(newACSRiskCalculatorData.getSerious_complicationsPercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_risk_seriousComplications(newACSRiskCalculatorData.getAverage_risk_seriousComplications());
        ACSRiskCalculatorDataInfoUpdate.setAny_complicationsPercentage(newACSRiskCalculatorData.getAny_complicationsPercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_risk_anyComplications(newACSRiskCalculatorData.getAverage_risk_anyComplications());
        ACSRiskCalculatorDataInfoUpdate.setPneumonia_percentage(newACSRiskCalculatorData.getPneumonia_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskPneumonia(newACSRiskCalculatorData.getAverage_riskPneumonia());
        ACSRiskCalculatorDataInfoUpdate.setCardiac_complicationsPercentage(newACSRiskCalculatorData.getCardiac_complicationsPercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_risk_cardiacComplications(newACSRiskCalculatorData.getAverage_risk_cardiacComplications());
        ACSRiskCalculatorDataInfoUpdate.setSurgical_infectionPercentage(newACSRiskCalculatorData.getSurgical_infectionPercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskSurgicalInfection(newACSRiskCalculatorData.getAverage_riskSurgicalInfection());
        ACSRiskCalculatorDataInfoUpdate.setITU_percentage(newACSRiskCalculatorData.getITU_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskITU(newACSRiskCalculatorData.getAverage_riskITU());
        ACSRiskCalculatorDataInfoUpdate.setVenous_thromboembolismPercentage(newACSRiskCalculatorData.getVenous_thromboembolismPercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskVenousThromboembolism(newACSRiskCalculatorData.getAverage_riskVenousThromboembolism());
        ACSRiskCalculatorDataInfoUpdate.setKidney_failurePercentage(newACSRiskCalculatorData.getKidney_failurePercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskKidneyFailure(newACSRiskCalculatorData.getAverage_riskKidneyFailure());
        ACSRiskCalculatorDataInfoUpdate.setIleus_percentage(newACSRiskCalculatorData.getIleus_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskIleus(newACSRiskCalculatorData.getAverage_riskIleus());
        ACSRiskCalculatorDataInfoUpdate.setAnastomotic_leakagePercentage(newACSRiskCalculatorData.getAnastomotic_leakagePercentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskAnastomoticLeakage(newACSRiskCalculatorData.getAverage_riskAnastomoticLeakage());
        ACSRiskCalculatorDataInfoUpdate.setReadmission_percentage(newACSRiskCalculatorData.getReadmission_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskReadmission(newACSRiskCalculatorData.getAverage_riskReadmission());
        ACSRiskCalculatorDataInfoUpdate.setReoperation_percentage(newACSRiskCalculatorData.getReoperation_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskReoperation(newACSRiskCalculatorData.getAverage_riskReoperation());
        ACSRiskCalculatorDataInfoUpdate.setDeath_percentage(newACSRiskCalculatorData.getDeath_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_riskDeath(newACSRiskCalculatorData.getAverage_riskDeath());
        ACSRiskCalculatorDataInfoUpdate.setDischarge_To_Nursing_or_Rehab_Facility_percentage(newACSRiskCalculatorData.getDischarge_To_Nursing_or_Rehab_Facility_percentage());
        ACSRiskCalculatorDataInfoUpdate.setAverage_risk_discharge_nursing_rehab(newACSRiskCalculatorData.getAverage_risk_discharge_nursing_rehab());
        ACSRiskCalculatorDataInfoUpdate.setACS_daysPrevision(newACSRiskCalculatorData.getACS_daysPrevision());

        return ACSRiskCalculatorData.save(ACSRiskCalculatorDataInfoUpdate);
    } 

    @DeleteMapping("/ACSRiskCalculatorDataInfo/{id}")
    public void deleteACSRiskCalculatorDataData(@PathVariable Long id) {
        ACSRiskCalculatorData.findById(id).orElseThrow(ACSRiskCalculatorDataNotFountException::new);
        ACSRiskCalculatorData.deleteById(id);
    }


}