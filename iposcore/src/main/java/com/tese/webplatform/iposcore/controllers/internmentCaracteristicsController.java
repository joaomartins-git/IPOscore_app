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
public class internmentCaracteristicsController{

    @Autowired
    public internmentCaracteristicsRepository internment;

    public internmentCaracteristicsController(internmentCaracteristicsRepository internment){
        this.internment=internment;
    }

    @GetMapping("/internmentCaracteristicsInfo")
    public List<internmentCaracteristicsData> getInternmentCaracteristicsData() {
        return internment.findAll();
    }

    @GetMapping("/internmentCaracteristicsInfo/{id}")
    public internmentCaracteristicsData findInternmentCaracteristicsInfoById(@PathVariable Long id) {
        return internment.findById(id).orElseThrow(internmentCaracteristicsNotFoundException::new);
    }

    
    @GetMapping("/internmentCaracteristicsInfo/provenace/{provenace}")
    public List<internmentCaracteristicsData> findByInternmentProvenace(@PathVariable int provenace) {
        List<internmentCaracteristicsData> temp = internment.findByProvenace(provenace);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    
    @GetMapping("/internmentCaracteristicsInfo/admissionMotive/{admissionMotive}")
    public List<internmentCaracteristicsData> findByInternmentAdmissionMotive(@PathVariable int admissionMotive) {
        List<internmentCaracteristicsData> temp = internment.findByAdmissionMotive(admissionMotive);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/surgeryType/{surgeryType}")
    public List<internmentCaracteristicsData> findByInternmentSurgeryType(@PathVariable int surgeryType) {
        List<internmentCaracteristicsData> temp = internment.findBySurgeryType(surgeryType);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/specialty/{specialty}")
    public List<internmentCaracteristicsData> findByInternmentSpecialty(@PathVariable String specialty) {
        List<internmentCaracteristicsData> temp = internment.findBySpecialty(specialty);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/specialtyCOD/{specialtyCOD}")
    public List<internmentCaracteristicsData> findByInternmentSpecialtyCOD(@PathVariable int specialtyCOD) {
        List<internmentCaracteristicsData> temp = internment.findBySpecialtyCOD(specialtyCOD);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }


    @GetMapping("/internmentCaracteristicsInfo/firstSurgeryInIPO/{firstSurgeryInIPO}")
    public List<internmentCaracteristicsData> findByInternmentFirstSurgeryInIPO(@PathVariable int firstSurgeryInIPO) {
        List<internmentCaracteristicsData> temp = internment.findByFirstSurgeryInIPO(firstSurgeryInIPO);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }


    @GetMapping("/internmentCaracteristicsInfo/preoperativeQT/{preoperativeQT}")
    public List<internmentCaracteristicsData> findByInternmentPreoperativeQT(@PathVariable int preoperativeQT) {
        List<internmentCaracteristicsData> temp = internment.findByPreoperativeQT(preoperativeQT);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/uciReadmission/{uciReadmission}")
    public List<internmentCaracteristicsData> findByInternmentUciReadmission(@PathVariable int uciReadmission) {
        List<internmentCaracteristicsData> temp = internment.findByUciReadmission(uciReadmission);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/uciDays/{uciDays}")
    public List<internmentCaracteristicsData> findByInternmentUciDays(@PathVariable Double uciDays) {
        List<internmentCaracteristicsData> temp = internment.findByUciDays(uciDays);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }


    @GetMapping("/internmentCaracteristicsInfo/ipopDays/{ipopDays}")
    public List<internmentCaracteristicsData> findByInternmentIpopDays(@PathVariable Double ipopDays) {
        List<internmentCaracteristicsData> temp = internment.findByIpopDays(ipopDays);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }


    @GetMapping("/internmentCaracteristicsInfo/totalPointsNAS/{totalPointsNAS}")
    public List<internmentCaracteristicsData> findByInternmentTotalPointsNAS(@PathVariable Double totalPointsNAS) {
        List<internmentCaracteristicsData> temp = internment.findByTotalPointsNAS(totalPointsNAS);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    @GetMapping("/internmentCaracteristicsInfo/pointsPerDayNAS/{pointsPerDayNAS}")
    public List<internmentCaracteristicsData> findByInternmentPointsPerDayNAS(@PathVariable Double pointsPerDayNAS) {
        List<internmentCaracteristicsData> temp = internment.findByPointsPerDayNAS(pointsPerDayNAS);
        if(temp.isEmpty())
            throw new internmentCaracteristicsNotFoundException();
        return temp;
    }

    
    
    @PostMapping("/internmentCaracteristicsInfo")
    public internmentCaracteristicsData addUCIApplication(@RequestBody internmentCaracteristicsData newInternmentCaracteristicsData) {
        return internment.save(newInternmentCaracteristicsData);
    }
    
    @PutMapping("/internmentCaracteristicsInfo/{id}")
    public internmentCaracteristicsData updateInternmentCaracteristicsData(@PathVariable Long id, @RequestBody internmentCaracteristicsData newInternmentCaracteristicsData) {
        internmentCaracteristicsData internmentCaracteristicsInfoUpdate = internment.findById(id).orElseThrow(UCIAdmissionRequestNotFoundException::new);
     
        internmentCaracteristicsInfoUpdate.setId(newInternmentCaracteristicsData.getID());
        internmentCaracteristicsInfoUpdate.setProvenace(newInternmentCaracteristicsData.getProvenace());
        internmentCaracteristicsInfoUpdate.setAdmissionMotive(newInternmentCaracteristicsData.getAdmissionMotive());
        internmentCaracteristicsInfoUpdate.setSurgeryType(newInternmentCaracteristicsData.getSurgeryType());
        internmentCaracteristicsInfoUpdate.setSpecialty(newInternmentCaracteristicsData.getSpecialty());
        internmentCaracteristicsInfoUpdate.setSpecialtyCOD(newInternmentCaracteristicsData.getSpecialtyCOD());
        internmentCaracteristicsInfoUpdate.setUciDays(newInternmentCaracteristicsData.getUciDays());
        internmentCaracteristicsInfoUpdate.setIpopDays(newInternmentCaracteristicsData.getIpopDays());
        internmentCaracteristicsInfoUpdate.setFirstSurgeryInIPO(newInternmentCaracteristicsData.getFirstSurgeryInIPO());
        internmentCaracteristicsInfoUpdate.setTotalPointsNAS(newInternmentCaracteristicsData.getNASTotalPoints());
        internmentCaracteristicsInfoUpdate.setPointsPerDayNAS(newInternmentCaracteristicsData.getNASPointsPerDay());
        internmentCaracteristicsInfoUpdate.setPreoperativeQT(newInternmentCaracteristicsData.getPreoperativeQT());
        internmentCaracteristicsInfoUpdate.setUciReadmission(newInternmentCaracteristicsData.getUciReadmission());
        internmentCaracteristicsInfoUpdate.setDestiny_after_Uci(newInternmentCaracteristicsData.getDestiny_after_Uci());
        
        internmentCaracteristicsInfoUpdate.setUciReadmission_Motive(newInternmentCaracteristicsData.getUciReadmission_Motive());
        internmentCaracteristicsInfoUpdate.setPerformedSurgery(newInternmentCaracteristicsData.getPerformedSurgery());
        internmentCaracteristicsInfoUpdate.setUnderwentSCI(newInternmentCaracteristicsData.getUnderwentSCI());
        

        return internment.save(internmentCaracteristicsInfoUpdate);
    } 

    @DeleteMapping(value="/internmentCaracteristicsInfo/{id}")
    public void deleteInternmentCaracteristicsData(@PathVariable Long id) {
        internment.findById(id).orElseThrow(internmentCaracteristicsNotFoundException::new);
        internment.deleteById(id);
    }

}