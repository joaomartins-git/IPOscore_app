package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("ppossumDataRepository")
public interface ppossumDataRepository extends JpaRepository<ppossumData, Long> {

}