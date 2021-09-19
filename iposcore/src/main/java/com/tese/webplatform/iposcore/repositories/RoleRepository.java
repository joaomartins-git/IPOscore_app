package com.tese.webplatform.iposcore.repositories;

import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.tese.webplatform.iposcore.models.Role;
import com.tese.webplatform.iposcore.models.RoleName;
 
@Repository("RoleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}