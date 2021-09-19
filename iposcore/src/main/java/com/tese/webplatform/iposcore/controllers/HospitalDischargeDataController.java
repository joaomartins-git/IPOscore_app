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
public class HospitalDischargeDataController{
    @Autowired
    public HospitalDischargeDataRepository hospitalDischargeData;

    public HospitalDischargeDataController(HospitalDischargeDataRepository hospitalDischargeData){
        this.hospitalDischargeData=hospitalDischargeData;
    }

    @GetMapping("/hospitalDischargeDataInfo")
    public List<HospitalDischargeData> getHospitalDischargeDataInfo() {
        return hospitalDischargeData.findAll();
    }
    
    @GetMapping("/hospitalDischargeDataInfo/{id}")
    public HospitalDischargeData findHospitalDischargeDataById(@PathVariable long id) {
        return hospitalDischargeData.findById(id).orElseThrow(HospitalDischargeDataNotFoundException::new);
    }

    @GetMapping("/hospitalDischargeDataInfo/death_upto_1year/{death_upto_1year}")
    public List<HospitalDischargeData> findByDeathUpTo1Year(@PathVariable int deathUpTo1year) {
        List<HospitalDischargeData> temp = hospitalDischargeData.findByDeathUpTo1year(deathUpTo1year);
        if(temp.isEmpty())
            throw new HospitalDischargeDataNotFoundException();
        return temp;
    }

    @PostMapping(value="/hospitalDischargeDataInfo")
    public HospitalDischargeData addHospitalDischargeData(@RequestBody HospitalDischargeData newHospitalDischargeData) {
        return hospitalDischargeData.save(newHospitalDischargeData);
    }
    
    @PutMapping(value="/hospitalDischargeDataInfo/{id}")
    public HospitalDischargeData updateHospitalDischargeDataInfo(@PathVariable Long id, @RequestBody HospitalDischargeData newHospitalDischargeData) {
        HospitalDischargeData hospitalDischargeDataToUpdate = hospitalDischargeData.findById(id).orElseThrow(HospitalDischargeDataNotFoundException::new);
     
        hospitalDischargeDataToUpdate.setId(newHospitalDischargeData.getID());
        hospitalDischargeDataToUpdate.setDestiny_after_IPO_Discharge(newHospitalDischargeData.getDestiny_after_IPO_Discharge());
        hospitalDischargeDataToUpdate.setDeathUpTo1Year(newHospitalDischargeData.getDeathUpTo1Year());
        hospitalDischargeDataToUpdate.setDeath_date(newHospitalDischargeData.getDeath_date());
        hospitalDischargeDataToUpdate.setAdditional_Info(newHospitalDischargeData.getAdditional_Info());
        hospitalDischargeDataToUpdate.setAdditional_Info_english(newHospitalDischargeData.getAdditional_Info_english());
     
        return hospitalDischargeData.save(hospitalDischargeDataToUpdate);
    }   

    @DeleteMapping(value="hospitalDischargeDataInfo/{id}")
    public void deleteHospitalDischargeData(@PathVariable Long id) {
        hospitalDischargeData.findById(id).orElseThrow(HospitalDischargeDataNotFoundException::new);
        hospitalDischargeData.deleteById(id);
    }

}