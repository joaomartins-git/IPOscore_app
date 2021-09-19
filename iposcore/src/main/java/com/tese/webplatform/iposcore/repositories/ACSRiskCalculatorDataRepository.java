package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import com.tese.webplatform.iposcore.models.*;

@Repository("ACSRiskCalculatorDataRepository")
public interface ACSRiskCalculatorDataRepository extends JpaRepository<ACSRiskCalculatorData, Long> {

    

}
