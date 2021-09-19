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
public class CharlsonDataController {
    
    @Autowired
    public CharlsonDataRepository CharlsonData;

    public CharlsonDataController(CharlsonDataRepository CharlsonData){
        this.CharlsonData = CharlsonData;
    }

    @GetMapping("/CharlsonDataInfo")
    public List<CharlsonData> getARISCATData() {
        return CharlsonData.findAll();
    }

    @GetMapping("/CharlsonDataInfo/{id}")
    public CharlsonData findCharlsonDataById(@PathVariable Long id) {
        return CharlsonData.findById(id).orElseThrow(CharlsonDataNotFoundException::new);
    }
    // continuar a adicionar os metodos como no ariscat e adicionar uma classe de erro tbm


    @PostMapping("/CharlsonDataInfo")
    public CharlsonData addCharlsonData(@RequestBody CharlsonData newCharlsonData) {
        return CharlsonData.save(newCharlsonData);
    }
    
    @PutMapping("/CharlsonDataInfo/{id}")
    public CharlsonData updateCharlsonDataInfo(@PathVariable Long id, @RequestBody CharlsonData newCharlsonData) {
        CharlsonData CharlsonDataInfoUpdate = CharlsonData.findById(id).orElseThrow(CharlsonDataNotFoundException::new);
     
        CharlsonDataInfoUpdate.setId(newCharlsonData.getId());
        CharlsonDataInfoUpdate.setCHARLSON_age(newCharlsonData.getCHARLSON_age());
        CharlsonDataInfoUpdate.setCHARLSON_diabetes_mellitus(newCharlsonData.getCHARLSON_diabetes_mellitus());
        CharlsonDataInfoUpdate.setCHARLSON_liver_disease(newCharlsonData.getCHARLSON_liver_disease());
        CharlsonDataInfoUpdate.setCHARLSON_malignancy_solid_tumor(newCharlsonData.getCHARLSON_malignancy_solid_tumor());
        CharlsonDataInfoUpdate.setCHARLSON_AIDS(newCharlsonData.getCHARLSON_AIDS());
        CharlsonDataInfoUpdate.setCHARLSON_chronic_kidney_disease(newCharlsonData.getCHARLSON_chronic_kidney_disease());
        CharlsonDataInfoUpdate.setCHARLSON_heart_failure(newCharlsonData.getCHARLSON_heart_failure());
        CharlsonDataInfoUpdate.setCHARLSON_myocardial_infarction(newCharlsonData.getCHARLSON_myocardial_infarction());
        CharlsonDataInfoUpdate.setCHARLSON_DPOC(newCharlsonData.getCHARLSON_DPOC());
        CharlsonDataInfoUpdate.setCHARLSON_peripheral_vascular_disease(newCharlsonData.getCHARLSON_peripheral_vascular_disease());
        CharlsonDataInfoUpdate.setCHARLSON_AVC(newCharlsonData.getCHARLSON_AVC());
        CharlsonDataInfoUpdate.setCHARLSON_dementia(newCharlsonData.getCHARLSON_dementia());
        CharlsonDataInfoUpdate.setCHARLSON_hemiplegia(newCharlsonData.getCHARLSON_hemiplegia());
        CharlsonDataInfoUpdate.setCHARLSON_connective_tissue_disease(newCharlsonData.getCHARLSON_connective_tissue_disease());
        CharlsonDataInfoUpdate.setCHARLSON_peptic_ulcer(newCharlsonData.getCHARLSON_peptic_ulcer());
        CharlsonDataInfoUpdate.setPOINTS_Charlson_Comorbidity_Index(newCharlsonData.getPOINTS_Charlson_Comorbidity_Index());
        CharlsonDataInfoUpdate.setCHARLSON_Estimated_10year_survival_percentage(newCharlsonData.getCHARLSON_Estimated_10year_survival_percentage());
 

        return CharlsonData.save(CharlsonDataInfoUpdate);
    } 

    @DeleteMapping("/CharlsonDataInfo/{id}")
    public void deleteCharlsonDataData(@PathVariable Long id) {
        CharlsonData.findById(id).orElseThrow(CharlsonDataNotFoundException::new);
        CharlsonData.deleteById(id);
    }

}
