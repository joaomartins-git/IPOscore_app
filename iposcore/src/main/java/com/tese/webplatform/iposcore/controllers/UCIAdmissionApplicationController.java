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
public class UCIAdmissionApplicationController{
    @Autowired
    public ApplicationForUCIAdmissionRepository applicationForUCI;

    public UCIAdmissionApplicationController(ApplicationForUCIAdmissionRepository applicationForUCI){
        this.applicationForUCI=applicationForUCI;
    }

    @GetMapping("/applicationForUciInfo")
    public List<UCIAdmissionApplicationData> getApplicationForUCI() {
        return applicationForUCI.findAll();
    }
    
    @GetMapping("/applicationForUciInfo/{id}")
    public UCIAdmissionApplicationData findUCIApplicationById(@PathVariable long id) {
        return applicationForUCI.findById(id).orElseThrow(UCIAdmissionRequestNotFoundException::new);
    }

    @PostMapping("/applicationForUciInfo")
    public UCIAdmissionApplicationData addUCIApplication(@RequestBody UCIAdmissionApplicationData newUCIApplication) {
        return applicationForUCI.save(newUCIApplication);
    }
    
    @PutMapping("/applicationForUciInfo/{id}")
    public UCIAdmissionApplicationData updateApplicationForUciAdmissionInfo(@PathVariable Long id, @RequestBody UCIAdmissionApplicationData newUCIApplication) {
        UCIAdmissionApplicationData UCIApplicationToUpdate = applicationForUCI.findById(id).orElseThrow(UCIAdmissionRequestNotFoundException::new);
     
        UCIApplicationToUpdate.setAnesthesiaRequestDate(newUCIApplication.getAnesthesia_request_Date());
        UCIApplicationToUpdate.setAnesthesiaRequestType(newUCIApplication.getAnesthesia_request_type());
        UCIApplicationToUpdate.setUCIApplicationDate(newUCIApplication.getUCI_Admission_Date());
        UCIApplicationToUpdate.setId(newUCIApplication.getID());
     
        return applicationForUCI.save(UCIApplicationToUpdate);
    }   

    @DeleteMapping("/applicationForUciInfo/{id}")
    public void deleteUCI(@PathVariable Long id) {
        applicationForUCI.findById(id).orElseThrow(UCIAdmissionRequestNotFoundException::new);
        applicationForUCI.deleteById(id);
    }

}