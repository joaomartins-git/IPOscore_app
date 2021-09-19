package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tese.webplatform.iposcore.models.internmentCaracteristicsData;

@Repository("internmentCaracteristicsRepository")
public interface internmentCaracteristicsRepository extends JpaRepository<internmentCaracteristicsData, Long> {

    List<internmentCaracteristicsData> findByProvenace(int provenace);
    List<internmentCaracteristicsData> findByAdmissionMotive(int admissionMotive);
    List<internmentCaracteristicsData> findBySurgeryType(int surgeryType);
    List<internmentCaracteristicsData> findBySpecialty(String specialty);
    List<internmentCaracteristicsData> findBySpecialtyCOD(int specialtyCOD);
    List<internmentCaracteristicsData> findByFirstSurgeryInIPO(int firstSurgeryInIPO);
    List<internmentCaracteristicsData> findByPreoperativeQT(int preoperativeQT);
    List<internmentCaracteristicsData> findByUciReadmission(int uciReadmission);
    List<internmentCaracteristicsData> findByUciDays(Double uciDays);
    List<internmentCaracteristicsData> findByIpopDays(Double ipopDays);
    List<internmentCaracteristicsData> findByTotalPointsNAS(Double totalPointsNAS);
    List<internmentCaracteristicsData> findByPointsPerDayNAS(Double pointsPerDayNAS);





}