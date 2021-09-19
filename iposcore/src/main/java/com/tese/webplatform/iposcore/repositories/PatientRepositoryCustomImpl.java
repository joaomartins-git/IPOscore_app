package com.tese.webplatform.iposcore.repositories;

import java.util.List;
// import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tese.webplatform.iposcore.dto.objJsonToSpring;
import com.tese.webplatform.iposcore.models.PatientData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public class PatientRepositoryCustomImpl implements PatientRepositoryCustom {

    @Autowired
    private EntityManager em;
    

    @Override
    public Page<PatientData> filteredPatients(List<objJsonToSpring> lista, int page, int pageSize) {
        StringBuilder query = new StringBuilder("SELECT DISTINCT pd FROM PatientData pd ");
        StringBuilder querySize = new StringBuilder("SELECT COUNT(pd) FROM PatientData pd ");

        String fazAMesmaCenaPoBuildJoin = buildJoin(lista);
        query.append(fazAMesmaCenaPoBuildJoin);
        querySize.append(fazAMesmaCenaPoBuildJoin);


        String nomequalquerseila = "";

        if(!lista.isEmpty()){
            query.append("WHERE ");
            querySize.append("WHERE ");
        }
        for(int x= 0; x < lista.size(); x++){
            if(x != 0){
                if(lista.get(x).isConjunction()){
                    nomequalquerseila = " AND ";
                }
                else{
                    nomequalquerseila = " OR ";
                }
                query.append(nomequalquerseila);
                querySize.append(nomequalquerseila);

            }
            String fazAiUmaString = buildWhere(lista.get(x));
            query.append(fazAiUmaString); 
            querySize.append(fazAiUmaString); 
        }
        // System.err.println(obsJson.isConjunction());
        
        System.err.println(query.toString());
        // System.err.println(querySize.toString());

        TypedQuery<PatientData> tQuery = em.createQuery(query.toString(), PatientData.class);
        TypedQuery<Long> tdQuery = em.createQuery(querySize.toString(), Long.class);

        tQuery.setFirstResult(page*pageSize);
        tQuery.setMaxResults(pageSize);

        // tQuery.setMaxResults(20);
        return new PageImpl<PatientData>(tQuery.getResultList(), PageRequest.of(page, pageSize), tdQuery.getSingleResult());
    }


    private String buildWhere(objJsonToSpring obj){
        
        if(obj!= null && obj.getAttribute()!= null && obj.getRelation()!=null && obj.getValue()!=null){
            if(obj.getAttribute().equalsIgnoreCase("age")){
                return "pd.age " + obj.getRelation() + " " + obj.getValue();
            }
            if(obj.getAttribute().equalsIgnoreCase("gender")){
                return "pd.gender.typeof_gender " + obj.getRelation() + " '" +  obj.getValue()+ "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("Surgery Location")){
                return "sd.surgeryLocation " + obj.getRelation() + " '" + obj.getValue().replace("'", "''") + "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("IPOP Days")){
                return "ic.ipopDays " + obj.getRelation() + " " + obj.getValue();
            }
            if(obj.getAttribute().equalsIgnoreCase("UCI Days")){
                return "ic.uciDays " + obj.getRelation() + " " + obj.getValue();
            }
            if(obj.getAttribute().equalsIgnoreCase("UCI Admission Date")){
                // System.err.println(TimeZone.getDefault());
                return "ucd.UCI_Admission_Date " + obj.getRelation() + " '" + obj.getValue() + "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("Clavien Dindo Classification")){
                return "psd.Clavien_Dindo_classification.typeof_Clavien_Dindo_classification " + obj.getRelation() + " '" + obj.getValue()+ "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("Functional State")){
                return "acs.ACS_functional_state.typeof_acs_functional_state " + obj.getRelation() + " '" + obj.getValue()+ "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("UCI Admission Motive")){
                return "ic.admissionMotive.typeof_motive " + obj.getRelation() + " '" + obj.getValue() + "' ";
            }
            if(obj.getAttribute().equalsIgnoreCase("Provenace")){
                return "ic.provenace.typeof_provenace " + obj.getRelation() + " '" + obj.getValue() + "' ";
            }
        }
        return "";
    } 

    private String buildJoin(List<objJsonToSpring> lista){
        String burp = "";
        final String surgeryData = "LEFT JOIN pd.lstSurgeryDatas sd ";
        final String internmentCaracteristics = "LEFT JOIN sd.internmentCaracteristics ic ";
        final String uciAdmissionApplicatioData = "LEFT JOIN sd.uciAdmissionData ucd ";
        final String acsRiskCalculatorData = "LEFT JOIN sd.acsRiskCalculatorData acs ";
        final String ariscatData = "LEFT JOIN sd.ariscatData ad ";
        final String postsurgicalData = "LEFT JOIN sd.postsurgicalData psd ";
        final String hospitalDischargeInfo = "LEFT JOIN sd.hospitalDischargeInfo hdi ";
        final String comorbiditiesData = "LEFT JOIN sd.comorbiditiesData cd ";
        final String ppossumData = "LEFT JOIN sd.ppossumData ppd ";

        for(objJsonToSpring obj: lista){
            if(obj!= null && obj.getAttribute()!= null && obj.getRelation()!=null && obj.getValue()!=null){
                if(obj.getAttribute().equalsIgnoreCase("Surgery Location") || obj.getAttribute().equalsIgnoreCase("IPOP Days") || obj.getAttribute().equalsIgnoreCase("UCI Days") || obj.getAttribute().equalsIgnoreCase("UCI Admission Motive") || obj.getAttribute().equalsIgnoreCase("UCI Admission Date") || obj.getAttribute().equalsIgnoreCase("Clavien Dindo Classification") || obj.getAttribute().equalsIgnoreCase("Functional State") || obj.getAttribute().equalsIgnoreCase("Provenace")){
                    burp += !burp.contains(surgeryData) ? surgeryData : " ";
                }
                if(obj.getAttribute().equalsIgnoreCase("IPOP Days")  || obj.getAttribute().equalsIgnoreCase("UCI Days") || obj.getAttribute().equalsIgnoreCase("UCI Admission Motive") || obj.getAttribute().equalsIgnoreCase("Provenace")){
                    burp += !burp.contains(internmentCaracteristics) ? internmentCaracteristics : " ";
                }
                if(obj.getAttribute().equalsIgnoreCase("UCI Admission Date")){
                    burp += !burp.contains(uciAdmissionApplicatioData) ? uciAdmissionApplicatioData : " ";
                }
                if(obj.getAttribute().equalsIgnoreCase("Clavien Dindo Classification")){
                    burp += !burp.contains(postsurgicalData) ? postsurgicalData : " ";
                }
                if(obj.getAttribute().equalsIgnoreCase("Functional State")){
                    burp += !burp.contains(acsRiskCalculatorData) ? acsRiskCalculatorData : " ";
                }
                
            }
        }
        return burp;
    } 
}