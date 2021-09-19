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

import java.util.Date;
import java.util.List;

import com.tese.webplatform.iposcore.models.*;
import com.tese.webplatform.iposcore.repositories.*;
import com.tese.webplatform.iposcore.errors.*;

@RestController
@CrossOrigin
public class SurgeryDataController{

    @Autowired
    public SurgeryDataRepository surgeryData;

    public SurgeryDataController(SurgeryDataRepository surgeryData){
        this.surgeryData = surgeryData;
    }

    @GetMapping("/surgeryDataInfo")
    public List<SurgeryData> getSurgeryData() {
        return surgeryData.findAll();
    }

    @GetMapping("/surgeryDataInfo/id/{id}")
    public SurgeryData findSurgeryDataById(@PathVariable Long id) {
        return surgeryData.findById(id).orElseThrow(SurgeryDataNotFoundException::new);
    }

    @GetMapping("/surgeryDataInfo/surgeryLocation/{surgeryLocation}")
    public List<SurgeryData> findSurgeryDataBySurgeryLocation(@PathVariable String surgeryLocation) {
        List<SurgeryData> temp = surgeryData.findBySurgeryLocation(surgeryLocation);
        if(temp.isEmpty())
            throw new SurgeryDataNotFoundException();
        return temp;
    }

    @GetMapping("/surgeryDataInfo/surgeryDate/{surgeryDate}")
    public List<SurgeryData> findBySurgeryDate(@PathVariable Date surgeryDate) {
        List<SurgeryData> temp = surgeryData.findBySurgeryDate(surgeryDate);
        if(temp.isEmpty())
            throw new SurgeryDataNotFoundException();
        return temp;
    }

    @GetMapping("/surgeryDataInfo/operationPerformed/{operationPerformed}")
    public List<SurgeryData> findByOperationPerformed(@PathVariable String operationPerformed) {
        List<SurgeryData> temp = surgeryData.findByOperationPerformed(operationPerformed);
        if(temp.isEmpty())
            throw new SurgeryDataNotFoundException();
        return temp;
    }
    
    @PostMapping("/surgeryDataInfo")
    public SurgeryData addSurgeryData(@RequestBody SurgeryData newSurgeryData) {
        return surgeryData.save(newSurgeryData);
    }
    
    @PutMapping("/surgeryDataInfo/{id}")
    public SurgeryData updateSurgeryDataInfo(@PathVariable Long id, @RequestBody SurgeryData newSurgeryData) {
        SurgeryData surgeryDataInfoUpdate = surgeryData.findById(id).orElseThrow(SurgeryDataNotFoundException::new);
     
        surgeryDataInfoUpdate.setId(newSurgeryData.getId());
        surgeryDataInfoUpdate.setInterventionsICD10(newSurgeryData.getInterventionsICD10());
        surgeryDataInfoUpdate.setOperationPerformed(newSurgeryData.getOperationPerformed());
        surgeryDataInfoUpdate.setPatientNumber(newSurgeryData.getPatientNumber());
        surgeryDataInfoUpdate.setPatientProvenace(newSurgeryData.getPatientProvenace());
        surgeryDataInfoUpdate.setPreoperativeDiagnosis(newSurgeryData.getPreoperativeDiagnosis());
        surgeryDataInfoUpdate.setProceduresCOD(newSurgeryData.getProceduresCOD());
        surgeryDataInfoUpdate.setSurgeryDate(newSurgeryData.getSurgeryDate());
        surgeryDataInfoUpdate.setSurgeryLocation(newSurgeryData.getSurgeryLocation());
        
        surgeryDataInfoUpdate.setASA(newSurgeryData.getASA());

        return surgeryData.save(surgeryDataInfoUpdate);
    } 

    @DeleteMapping(value="/surgeryDataInfo/{id}")
    public void deleteSurgeryDataInfo(@PathVariable Long id) {
        surgeryData.findById(id).orElseThrow(SurgeryDataNotFoundException::new);
        surgeryData.deleteById(id);
    }



}