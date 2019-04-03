package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.BloodGroupService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.BloodGroupDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BloodGroup.
 */
@RestController
@RequestMapping("/api")
public class BloodGroupResource {

    private final Logger log = LoggerFactory.getLogger(BloodGroupResource.class);

    private static final String ENTITY_NAME = "redDonorBloodGroup";

    private final BloodGroupService bloodGroupService;

    public BloodGroupResource(BloodGroupService bloodGroupService) {
        this.bloodGroupService = bloodGroupService;
    }

    /**
     * POST  /blood-groups : Create a new bloodGroup.
     *
     * @param bloodGroupDTO the bloodGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bloodGroupDTO, or with status 400 (Bad Request) if the bloodGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blood-groups")
    @Timed
    public ResponseEntity<BloodGroupDTO> createBloodGroup(@RequestBody BloodGroupDTO bloodGroupDTO) throws URISyntaxException {
        log.debug("REST request to save BloodGroup : {}", bloodGroupDTO);
        if (bloodGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new bloodGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BloodGroupDTO result = bloodGroupService.save(bloodGroupDTO);
        return ResponseEntity.created(new URI("/api/blood-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blood-groups : Updates an existing bloodGroup.
     *
     * @param bloodGroupDTO the bloodGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bloodGroupDTO,
     * or with status 400 (Bad Request) if the bloodGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the bloodGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blood-groups")
    @Timed
    public ResponseEntity<BloodGroupDTO> updateBloodGroup(@RequestBody BloodGroupDTO bloodGroupDTO) throws URISyntaxException {
        log.debug("REST request to update BloodGroup : {}", bloodGroupDTO);
        if (bloodGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BloodGroupDTO result = bloodGroupService.save(bloodGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bloodGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blood-groups : get all the bloodGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bloodGroups in body
     */
    @GetMapping("/blood-groups")
    @Timed
    public ResponseEntity<List<BloodGroupDTO>> getAllBloodGroups(Pageable pageable) {
        log.debug("REST request to get a page of BloodGroups");
        Page<BloodGroupDTO> page = bloodGroupService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blood-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /blood-groups/:id : get the "id" bloodGroup.
     *
     * @param id the id of the bloodGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bloodGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/blood-groups/{id}")
    @Timed
    public ResponseEntity<BloodGroupDTO> getBloodGroup(@PathVariable Long id) {
        log.debug("REST request to get BloodGroup : {}", id);
        Optional<BloodGroupDTO> bloodGroupDTO = bloodGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bloodGroupDTO);
    }

    /**
     * DELETE  /blood-groups/:id : delete the "id" bloodGroup.
     *
     * @param id the id of the bloodGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blood-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteBloodGroup(@PathVariable Long id) {
        log.debug("REST request to delete BloodGroup : {}", id);
        bloodGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
