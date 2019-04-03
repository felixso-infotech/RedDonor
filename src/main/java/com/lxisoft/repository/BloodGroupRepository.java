package com.lxisoft.repository;

import com.lxisoft.domain.BloodGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BloodGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Long> {

}
