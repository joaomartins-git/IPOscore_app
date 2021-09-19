package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("surgeryDataRepository")
public interface SurgeryDataRepository extends JpaRepository<SurgeryData, Long> {

    List<SurgeryData> findBySurgeryLocation(String surgeryLocation);
    List<SurgeryData> findBySurgeryDate(Date surgeryDate);
    List<SurgeryData> findByOperationPerformed(String operationPerformed);

}