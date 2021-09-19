package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("ARISCATDataRepository")
public interface ARISCATDataRepository extends JpaRepository<ARISCATData, Long> {



    List<ARISCATData> findByARISCATage(int ARISCATage);

}
