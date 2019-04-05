package com.lxisoft.service;

import com.lxisoft.service.dto.BloodGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BloodGroup.
 */
public interface BloodGroupService {

    /**
     * Save a bloodGroup.
     *
     * @param bloodGroupDTO the entity to save
     * @return the persisted entity
     */
    BloodGroupDTO save(BloodGroupDTO bloodGroupDTO);

    /**
     * Get all the bloodGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BloodGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bloodGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BloodGroupDTO> findOne(Long id);

    /**
     * Delete the "id" bloodGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
