package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("HospitalDischargeDataRepository")
public interface HospitalDischargeDataRepository extends JpaRepository<HospitalDischargeData, Long> {

    List<HospitalDischargeData> findByDeathUpTo1year(int deathUpTo1year);
}