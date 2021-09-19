package com.tese.webplatform.iposcore.repositories;

import java.util.List;

import com.tese.webplatform.iposcore.dto.objJsonToSpring;
import com.tese.webplatform.iposcore.models.*;

import org.springframework.data.domain.Page;

public interface PatientRepositoryCustom {

    Page<PatientData> filteredPatients(List<objJsonToSpring> lista, int page, int pageSize);

}