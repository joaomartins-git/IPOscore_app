package com.tese.webplatform.iposcore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tese.webplatform.iposcore.models.*;

@Repository("PostSurgicalComplicationsDataRepository")
public interface PostSurgicalComplicationsDataRepository extends JpaRepository<PostSurgicalComplicationsData, Long> {

    List<PostSurgicalComplicationsData> findByPostSurgicalComplication(int post_surgical_complication);
    
}