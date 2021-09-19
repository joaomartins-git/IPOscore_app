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
public class PreoperativeComorbiditiesDataController{
    @Autowired
    public PreoperativeComorbiditiesDataRepository preoperativeComorbiditiesData;

    public PreoperativeComorbiditiesDataController(PreoperativeComorbiditiesDataRepository preoperativeComorbiditiesData){
        this.preoperativeComorbiditiesData=preoperativeComorbiditiesData;
    }

    @GetMapping("/preoperativeComorbiditiesDataInfo")
    public List<PreoperativeComorbiditiesData> getPreoperativeComorbiditiesDataInfo() {
        return preoperativeComorbiditiesData.findAll();
    }
    
    @GetMapping("/preoperativeComorbiditiesDataInfo/{id}")
    public PreoperativeComorbiditiesData findPreoperativeComorbiditiesDataById(@PathVariable long id) {
        return preoperativeComorbiditiesData.findById(id).orElseThrow(PreoperativeComorbiditiesNotFoundException::new);
    }

    @PostMapping(value="/preoperativeComorbiditiesDataInfo")
    public PreoperativeComorbiditiesData addPreoperativeComorbiditiesData(@RequestBody PreoperativeComorbiditiesData newPreoperativeComorbiditiesData) {
        return preoperativeComorbiditiesData.save(newPreoperativeComorbiditiesData);
    }
    
    @PutMapping(value="/preoperativeComorbiditiesDataInfo/{id}")
    public PreoperativeComorbiditiesData updatePreoperativeComorbiditiesDataInfo(@PathVariable Long id, @RequestBody PreoperativeComorbiditiesData newPreoperativeComorbiditiesData) {
        PreoperativeComorbiditiesData preoperativeComorbiditiesDataToUpdate = preoperativeComorbiditiesData.findById(id).orElseThrow(PreoperativeComorbiditiesNotFoundException::new);
     
        preoperativeComorbiditiesDataToUpdate.setId(newPreoperativeComorbiditiesData.getID());
        preoperativeComorbiditiesDataToUpdate.setComorbiditiesInfo(newPreoperativeComorbiditiesData.getComorbiditiesInfo());
     
        return preoperativeComorbiditiesData.save(preoperativeComorbiditiesDataToUpdate);
    }   

    @DeleteMapping(value="preoperativeComorbiditiesDataInfo/{id}")
    public void deletePreoperativeComorbiditiesData(@PathVariable Long id) {
        preoperativeComorbiditiesData.findById(id).orElseThrow(PreoperativeComorbiditiesNotFoundException::new);
        preoperativeComorbiditiesData.deleteById(id);
    }

}