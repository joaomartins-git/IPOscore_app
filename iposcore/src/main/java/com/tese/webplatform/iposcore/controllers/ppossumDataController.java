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
@CrossOrigin
public class ppossumDataController{

    @Autowired
    public ppossumDataRepository ppossumData;

    public ppossumDataController(ppossumDataRepository ppossumData){
        this.ppossumData = ppossumData;
    }

    @GetMapping("/ppossumDataInfo")
    public List<ppossumData> getPpossumData() {
        return ppossumData.findAll();
    }

    @GetMapping("/ppossumDataInfo/{id}")
    public ppossumData findPpossumDataById(@PathVariable Long id) {
        return ppossumData.findById(id).orElseThrow(ppossumDataNotFoundException::new);
    }

    
    @PostMapping("/ppossumDataInfo")
    public ppossumData addPpossumData(@RequestBody ppossumData newPpossumData) {
        return ppossumData.save(newPpossumData);
    }
    
    @PutMapping("/ppossumDataInfo/{id}")
    public ppossumData updatePpossumDataInfo(@PathVariable Long id, @RequestBody ppossumData newPpossumData) {
        ppossumData ppossumDataInfoUpdate = ppossumData.findById(id).orElseThrow(ppossumDataNotFoundException::new);
     
        ppossumDataInfoUpdate.setId(newPpossumData.getID());
        ppossumDataInfoUpdate.setPP_Age(newPpossumData.getPP_Age());
        ppossumDataInfoUpdate.setPP_cardiac(newPpossumData.getPP_cardiac());
        ppossumDataInfoUpdate.setPP_respiratory(newPpossumData.getPP_respiratory());
        ppossumDataInfoUpdate.setPP_systolicBloodPressure(newPpossumData.getPP_systolic_blood_pressure());
        ppossumDataInfoUpdate.setPPECG(newPpossumData.getPP_ECG());
        ppossumDataInfoUpdate.setPP_artirialPulse(newPpossumData.getPP_artirial_pulse());
        ppossumDataInfoUpdate.setPPHemoglobin(newPpossumData.getPP_hemoglobin());
        ppossumDataInfoUpdate.setPPLeukocytes(newPpossumData.getPP_leukocytes());
        ppossumDataInfoUpdate.setPPUrea(newPpossumData.getPP_urea());
        ppossumDataInfoUpdate.setPPSodium(newPpossumData.getPP_sodium());
        ppossumDataInfoUpdate.setPPPotassium(newPpossumData.getPP_potassium());
        ppossumDataInfoUpdate.setPPGlasgowScale(newPpossumData.getPP_Glasgow_scale());
        ppossumDataInfoUpdate.setPPOperationType(newPpossumData.getPP_operation_type());
        ppossumDataInfoUpdate.setPPNumberOfProcedures(newPpossumData.getPP_number_of_procedures());
        ppossumDataInfoUpdate.setPPBloodLoss(newPpossumData.getPP_blood_loss());
        ppossumDataInfoUpdate.setPPPeritonealContamination(newPpossumData.getPP_peritoneal_contamination());
        ppossumDataInfoUpdate.setPPStateOfMalignancy(newPpossumData.getPP_state_of_malignancy());
        ppossumDataInfoUpdate.setPPCEPOD_gradingOperation(newPpossumData.getPP_CEPOD_grading_operation());
        ppossumDataInfoUpdate.setPPossumPhysiologicalScore(newPpossumData.getP_Possum_Physiological_Score());
        ppossumDataInfoUpdate.setPPossumSurgicalSeverityScore(newPpossumData.getP_Possum_Surgical_Severity_Score());
        ppossumDataInfoUpdate.setPPossumMorbidityPercentage(newPpossumData.getP_Possum_Morbidity_Percentage());
        ppossumDataInfoUpdate.setPPossumMortalityPercentage(newPpossumData.getP_Possum_Mortality_Percentage());

        return ppossumData.save(ppossumDataInfoUpdate);
    } 

    @DeleteMapping("/ppossumDataInfo/{id}")
    public void deletePpossumDataData(@PathVariable Long id) {
        ppossumData.findById(id).orElseThrow(ppossumDataNotFoundException::new);
        ppossumData.deleteById(id);
    }



}