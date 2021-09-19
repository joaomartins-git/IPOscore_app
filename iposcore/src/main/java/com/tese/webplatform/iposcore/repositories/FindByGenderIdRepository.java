package com.tese.webplatform.iposcore.repositories;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.tese.webplatform.iposcore.models.*;

@Repository("FindByGenderRepository")
public interface FindByGenderIdRepository extends JpaRepository<params__gender, Long>{
    
    Optional<params__gender> findById(Long id);

}
