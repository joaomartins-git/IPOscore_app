package com.tese.webplatform.iposcore.repositories;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("patientRepository")
public interface PatientRepository extends JpaRepository<PatientData, Long>, PatientRepositoryCustom{

    List<PatientData> findByAge(Long age);
    List<PatientData> findByGender(int gender);

    // List<PatientData> listDataExploration (String[] val);

}

