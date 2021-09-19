package com.tese.webplatform.iposcore.controllers;

// import org.python.core.PyInteger;
// import org.python.core.PyObject;
// import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

// import java.io.FileReader;
import java.io.IOException;
// import java.io.StringWriter;
// import java.util.Date;
// import java.util.ArrayList;
// import java.util.Date;
import java.util.List;

// import javax.script.ScriptContext;
// import javax.script.ScriptEngine;
// import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
// import javax.script.SimpleScriptContext;

import com.tese.webplatform.iposcore.models.*;
import com.tese.webplatform.iposcore.repositories.*;
import com.tese.webplatform.iposcore.dto.objJsonToSpring;
import com.tese.webplatform.iposcore.dto.updateDTO;
import com.tese.webplatform.iposcore.errors.*;

// import static org.junit.Assert.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;

@RestController
@CrossOrigin
public class PatientController {

    @Autowired
    public PatientRepository patient;

    @Autowired
    public SurgeryDataRepository tempData;

    @Autowired
    public internmentCaracteristicsRepository tempIntern;

    @Autowired
    public PostSurgicalComplicationsDataRepository tempPostData;

    @Autowired
    public ACSRiskCalculatorDataRepository tempACSData;

    @Autowired
    public ApplicationForUCIAdmissionRepository tempUCIdata;

    @Autowired
    public ARISCATDataRepository tempARISCATData;

    @Autowired
    public HospitalDischargeDataRepository tempHospitalDischargeData;

    @Autowired
    public ppossumDataRepository tempPpossumData;

    @Autowired
    public PreoperativeComorbiditiesDataRepository tempPreoperativeComorbiditiesData;

    @Autowired
    public CharlsonDataRepository tempCharlsonData;

    @Autowired
    public FindByGenderIdRepository findGender;

    public PatientController(PatientRepository patient) {
        this.patient = patient;
        // this.tempData=tempData;
    }

    @GetMapping("/patientsInfo")
    public List<PatientData> getPatientsInfo() {
        return patient.findAll();
    }

    @GetMapping("/patientsInfo/patient/{id}")
    public PatientData findPatientById(@PathVariable Long id) {
        return patient.findById(id).orElseThrow(PatientNotFoundException::new);
    }

    @GetMapping("/patientsInfo/age/{age}")
    public List<PatientData> findPatientByAge(@PathVariable Long age) {
        List<PatientData> temp = patient.findByAge(age);
        if (temp.isEmpty())
            throw new PatientNotFoundException();

        return temp;
    }

    @GetMapping("/patientsInfo/gender/{gender}")
    public List<PatientData> findPatientByGender(@PathVariable int gender) {
        List<PatientData> temp = patient.findByGender(gender);
        if (temp.isEmpty())
            throw new PatientNotFoundException();

        return temp;
    }

    @PostMapping(value = "/patientsInfo")
    public PatientData addPatient(@RequestBody PatientData newPatient) {

        PatientData pData = new PatientData();
        pData.setAge(newPatient.getAge());
        pData.setGender(newPatient.getGender());
        pData.setIpoNumber(newPatient.getIpoNumber());

        // pData = patient.save(pData);
        final PatientData fpd = patient.save(pData);

        newPatient.getLstSurgeryDatas().stream().forEach(sd -> {
            SurgeryData newSd = new SurgeryData();
            // newSd.setInternmentCaracteristics(icd);
            newSd.setPreoperativeDiagnosis(sd.getPreoperativeDiagnosis());
            newSd.setSurgeryLocation(sd.getSurgeryLocation());
            newSd.setPatientNumber(fpd);

            newSd.setSurgeryDate(sd.getSurgeryDate());

            newSd.setPatientProvenace(sd.getPatientProvenace());
            System.out.println(" que esta merda:  " + sd.getPatientProvenace());
            // newSd.setPatientProvenace((long) 1);

            newSd = tempData.save(newSd);
            internmentCaracteristicsData icd = null;
            if (sd.getInternmentCaracteristics() != null) {
                icd = sd.getInternmentCaracteristics();
                icd.setSurgerydata(newSd);
                // icd.setProvenace(newSd.getInternmentCaracteristics().getProvenace());
                // adicionar o resto das vars pelo setX
                icd = tempIntern.save(icd);
            }
            PostSurgicalComplicationsData pscd = null;
            if (sd.getPostsurgicalData() != null) {
                pscd = sd.getPostsurgicalData();
                pscd.setSurgerydata(newSd);

                pscd = tempPostData.save(pscd);
            }

            ACSRiskCalculatorData acsr = null;
            if (sd.getAcsRiskCalculatorData() != null) {
                acsr = sd.getAcsRiskCalculatorData();
                acsr.setSurgerydata(newSd);

                acsr = tempACSData.save(acsr);
            }

            ARISCATData ariscat = null;
            if (sd.getAriscatData() != null) {
                ariscat = sd.getAriscatData();
                ariscat.setSurgerydata(newSd);

                ariscat = tempARISCATData.save(ariscat);
            }

            HospitalDischargeData hddata = null;
            if (sd.getHospitalDischargeInfo() != null) {
                hddata = sd.getHospitalDischargeInfo();
                hddata.setSurgerydata(newSd);

                hddata = tempHospitalDischargeData.save(hddata);
            }

            ppossumData ppdata = null;
            if (sd.getPpossumData() != null) {
                ppdata = sd.getPpossumData();
                ppdata.setSurgerydata(newSd);

                ppdata = tempPpossumData.save(ppdata);
            }

            PreoperativeComorbiditiesData pcdata = null;
            if (sd.getComorbiditiesData() != null) {
                pcdata = sd.getComorbiditiesData();
                pcdata.setSurgerydata(newSd);

                pcdata = tempPreoperativeComorbiditiesData.save(pcdata);
            }

            UCIAdmissionApplicationData ucidata = null;
            if (sd.getUciAdmissionData() != null) {
                ucidata = sd.getUciAdmissionData();
                ucidata.setSurgerydata(newSd);

                ucidata = tempUCIdata.save(ucidata);
            }

            CharlsonData charlson = null;
            if(sd.getCharlsonData() != null){
                charlson = sd.getCharlsonData();
                charlson.setSurgerydata(newSd);

                charlson = tempCharlsonData.save(charlson);
            }

        });

        // SurgeryData data = new SurgeryData();
        // HospitalDischargeData hData = new HospitalDischargeData();
        // data.setSurgeryDate(new Date());
        // data.setPatientProvenace(1);
        // data.setPatientNumber(newPatient);

        // hData.setDeath_date(new Date());
        // hData.setDeathUpTo1Year(false);
        // hData.setDestiny_after_IPO_Discharge(1);

        // hData.setSurgerydata(data);

        // data.setHospitalDischargeInfo(hData);

        // tempData.save(data);
        // newPatient.getLstSurgeryDatas().add(data);

        System.err.println("dados do paciente:  " + newPatient);
        System.err.println("dados a acrescentar no array:  " + newPatient.getLstSurgeryDatas());

        return fpd;
    }


    //new case
    @PutMapping(value = "/patientsInfo/{id}")
    public void updatePatientInfo(@PathVariable Long id, @RequestBody PatientData newPatient) {
        PatientData patientToUpdate = patient.findById(id).orElseThrow(PatientNotFoundException::new);

        patientToUpdate.setIpoNumber(newPatient.getIpoNumber());
        patientToUpdate.setAge(newPatient.getAge());
        patientToUpdate.setGender(newPatient.getGender());
        
        final PatientData fpd = patient.save(patientToUpdate);
        
        patientToUpdate.getLstSurgeryDatas().stream().forEach(sd -> {
            SurgeryData newSd = new SurgeryData();
            // SurgeryData sfff = (SurgeryData) patientToUpdate.getLstSurgeryDatas();
            // newSd.setInternmentCaracteristics(icd);
            newSd.setPreoperativeDiagnosis(sd.getPreoperativeDiagnosis());
            newSd.setSurgeryLocation(sd.getSurgeryLocation());
            newSd.setPatientNumber(fpd);

            newSd.setSurgeryDate(sd.getSurgeryDate());

            newSd.setPatientProvenace(sd.getPatientProvenace());
            System.out.println(" que esta merda:  " + sd.getPatientProvenace());
            // newSd.setPatientProvenace((long) 1);

            newSd = tempData.save(newSd);
            internmentCaracteristicsData icd = null;
            if (sd.getInternmentCaracteristics() != null) {
                icd = sd.getInternmentCaracteristics();
                icd.setSurgerydata(newSd);
                // icd.setProvenace(newSd.getInternmentCaracteristics().getProvenace());
                // adicionar o resto das vars pelo setX
                icd = tempIntern.save(icd);
            }
            PostSurgicalComplicationsData pscd = null;
            if (sd.getPostsurgicalData() != null) {
                pscd = sd.getPostsurgicalData();
                pscd.setSurgerydata(newSd);

                pscd = tempPostData.save(pscd);
            }

            ACSRiskCalculatorData acsr = null;
            if (sd.getAcsRiskCalculatorData() != null) {
                acsr = sd.getAcsRiskCalculatorData();
                acsr.setSurgerydata(newSd);

                acsr = tempACSData.save(acsr);
            }

            ARISCATData ariscat = null;
            if (sd.getAriscatData() != null) {
                ariscat = sd.getAriscatData();
                ariscat.setSurgerydata(newSd);

                ariscat = tempARISCATData.save(ariscat);
            }

            HospitalDischargeData hddata = null;
            if (sd.getHospitalDischargeInfo() != null) {
                hddata = sd.getHospitalDischargeInfo();
                hddata.setSurgerydata(newSd);

                hddata = tempHospitalDischargeData.save(hddata);
            }

            ppossumData ppdata = null;
            if (sd.getPpossumData() != null) {
                ppdata = sd.getPpossumData();
                ppdata.setSurgerydata(newSd);

                ppdata = tempPpossumData.save(ppdata);
            }

            PreoperativeComorbiditiesData pcdata = null;
            if (sd.getComorbiditiesData() != null) {
                pcdata = sd.getComorbiditiesData();
                pcdata.setSurgerydata(newSd);

                pcdata = tempPreoperativeComorbiditiesData.save(pcdata);
            }

            UCIAdmissionApplicationData ucidata = null;
            if (sd.getUciAdmissionData() != null) {
                ucidata = sd.getUciAdmissionData();
                ucidata.setSurgerydata(newSd);

                ucidata = tempUCIdata.save(ucidata);
            }

            CharlsonData charlson = null;
            if(sd.getCharlsonData() != null){
                charlson = sd.getCharlsonData();
                charlson.setSurgerydata(newSd);

                charlson = tempCharlsonData.save(charlson);
            }
        });

    }

    @PutMapping(value = "/patientsInfo/smallPatientUpdate")
    public void smallPatientUpdate( @RequestBody updateDTO newPatient){
        PatientData patientToUpdate = patient.findById(newPatient.getIpoNumber()).orElseThrow(PatientNotFoundException::new);

        
        // Posso dar set disto?
        // findGender.findById(newPatient.getGender().getId()).orElseThrow(PatientNotFoundException::new);
        

        //System.out.println(findGender.findById(newPatient.getGender().getId()).orElseThrow(PatientNotFoundException::new) + " vai praqui uma bela merda sim senhora!");
        
        
        patientToUpdate.setAge(newPatient.getAge());
        
        System.out.println("farto desta merda toda fdsssssssssssssssssss " );
        
        patientToUpdate.setGender(findGender.findById(newPatient.getGender().getId()).orElseThrow(PatientNotFoundException::new));
        
        System.out.println("PUTA PUTA PUTA, FDPS DO CRL");
        patient.save(patientToUpdate);

    }


    @DeleteMapping(value = "/patientsInfo/{id}/")
    public void deletePatient(@PathVariable Long id) {
        patient.findById(id).orElseThrow(PatientNotFoundException::new);
        patient.deleteById(id);
    }

    @PostMapping("/patientsInfo/filtered")
    public Page<PatientData> getFilteredPatientsInfo(@RequestBody List<objJsonToSpring> coisas,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        System.err.println(coisas + " " + page + " " + size);

        return patient.filteredPatients(coisas, page, size);
    }

    @GetMapping("/listPageable")
    public Page<PatientData> findAllPageables(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return patient.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/listForDataExploration")
    public String listDataExploration() throws IOException, ScriptException {

        final String uri = "http://127.0.0.1:5010/data_exploration";
        

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        
        // System.out.println(result);

        return result;
    }

    @GetMapping("/listForFeatureRanking")
    public String listFeatureRanking() throws IOException, ScriptException {

        final String uri = "http://127.0.0.1:5010/data_feature_ranking";
        

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        
        // System.out.println(result);

        return result;
    }
}