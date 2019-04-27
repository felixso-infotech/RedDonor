package com.lxisoft.service.impl;

import com.lxisoft.service.BloodGroupService;
import com.lxisoft.domain.BloodGroup;
import com.lxisoft.repository.BloodGroupRepository;
import com.lxisoft.service.dto.BloodGroupDTO;
import com.lxisoft.service.mapper.BloodGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing BloodGroup.
 */
@Service
@Transactional
public class BloodGroupServiceImpl implements BloodGroupService {

    private final Logger log = LoggerFactory.getLogger(BloodGroupServiceImpl.class);

    private final BloodGroupRepository bloodGroupRepository;

    private final BloodGroupMapper bloodGroupMapper;

    public BloodGroupServiceImpl(BloodGroupRepository bloodGroupRepository, BloodGroupMapper bloodGroupMapper) {
        this.bloodGroupRepository = bloodGroupRepository;
        this.bloodGroupMapper = bloodGroupMapper;
    }

    /**
     * Save a bloodGroup.
     *
     * @param bloodGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BloodGroupDTO save(BloodGroupDTO bloodGroupDTO) {
        log.debug("Request to save BloodGroup : {}", bloodGroupDTO);

        BloodGroup bloodGroup = bloodGroupMapper.toEntity(bloodGroupDTO);
        bloodGroup = bloodGroupRepository.save(bloodGroup);
        return bloodGroupMapper.toDto(bloodGroup);
    }

    /**
     * Get all the bloodGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BloodGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BloodGroups");
        return bloodGroupRepository.findAll(pageable)
            .map(bloodGroupMapper::toDto);
    }


    /**
     * Get one bloodGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BloodGroupDTO> findOne(Long id) {
        log.debug("Request to get BloodGroup : {}", id);
        return bloodGroupRepository.findById(id)
            .map(bloodGroupMapper::toDto);
    }

    /**
     * Delete the bloodGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BloodGroup : {}", id);
        bloodGroupRepository.deleteById(id);
    }
}
