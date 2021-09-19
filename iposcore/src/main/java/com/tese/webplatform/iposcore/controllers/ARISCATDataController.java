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
public class ARISCATDataController{

    @Autowired
    public ARISCATDataRepository ARISCATData;

    public ARISCATDataController(ARISCATDataRepository ARISCATData){
        this.ARISCATData = ARISCATData;
    }

    @GetMapping("/ARISCATDataInfo")
    public List<ARISCATData> getARISCATData() {
        return ARISCATData.findAll();
    }

    @GetMapping("/ARISCATDataInfo/{id}")
    public ARISCATData findARISCATDataById(@PathVariable Long id) {
        return ARISCATData.findById(id).orElseThrow(ARISCATDataNotFoundException::new);
    }

    @GetMapping("/ARISCATDataInfo/ARISCAT_age/{ARISCAT_age}")
    public List<ARISCATData> findByARISCATage(@PathVariable int ARISCATage) {
        List<ARISCATData> temp = ARISCATData.findByARISCATage(ARISCATage);
        if(temp.isEmpty())
            throw new ARISCATDataNotFoundException();
        return temp;
    
    }
    
    @PostMapping("/ARISCATDataInfo")
    public ARISCATData addARISCATData(@RequestBody ARISCATData newARISCATData) {
        return ARISCATData.save(newARISCATData);
    }
    
    @PutMapping("/ARISCATDataInfo/{id}")
    public ARISCATData updateARISCATDataInfo(@PathVariable Long id, @RequestBody ARISCATData newARISCATData) {
        ARISCATData ARISCATDataInfoUpdate = ARISCATData.findById(id).orElseThrow(ARISCATDataNotFoundException::new);
     
        ARISCATDataInfoUpdate.setId(newARISCATData.getID());
        ARISCATDataInfoUpdate.setARISCATage(newARISCATData.getARISCATage());
        ARISCATDataInfoUpdate.setARISCAT_SpO2(newARISCATData.getARISCAT_SpO2());
        ARISCATDataInfoUpdate.setARISCAT_respiratory_infection_last_month(newARISCATData.getARISCAT_respiratory_infection_last_month());
        ARISCATDataInfoUpdate.setARISCAT_preoperative_anemia(newARISCATData.getARISCAT_preoperative_anemia());
        ARISCATDataInfoUpdate.setARISCAT_surgical_incision(newARISCATData.getARISCAT_surgical_incision());
        ARISCATDataInfoUpdate.setARISCAT_surgery_duration(newARISCATData.getARISCAT_surgery_duration());
        ARISCATDataInfoUpdate.setARISCAT_emerging_Procedure(newARISCATData.getARISCAT_emerging_Procedure());
        ARISCATDataInfoUpdate.setARISCAT_Total_points(newARISCATData.getARISCAT_Total_points());
        ARISCATDataInfoUpdate.setARISCAT_Score(newARISCATData.getARISCAT_Score());
 

        return ARISCATData.save(ARISCATDataInfoUpdate);
    } 

    @DeleteMapping("/ARISCATDataInfo/{id}")
    public void deleteARISCATDataData(@PathVariable Long id) {
        ARISCATData.findById(id).orElseThrow(ARISCATDataNotFoundException::new);
        ARISCATData.deleteById(id);
    }



}