package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tese.webplatform.iposcore.models.UCIAdmissionApplicationData;

@Repository("uciAdmissionApplicationRepository")
public interface ApplicationForUCIAdmissionRepository extends JpaRepository<UCIAdmissionApplicationData, Long> {

}