/*
* Copyright 2002-2016 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.lxisoft.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.domain.Contact;
import com.lxisoft.model.ContactAggregate;
import com.lxisoft.model.ProfileAggregate;
import com.lxisoft.service.AddressService;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.dto.BloodGroupDTO;

import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class AggregateResource {

	private final Logger log = LoggerFactory.getLogger(AggregateResource.class);

	private static final String ENTITY_NAME = "redDonorContact";

	@Autowired
	AggregateService aggregateService;

	@Autowired
	AddressService addressService;
		
    /**
     * POST  /contacts : Create a new contact.
     *
     * @param contactDTO the contactDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contactDTO, or with status 400 (Bad Request) if the contact has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contacts")
    @Timed
    public ResponseEntity<Contact> createContact(@RequestBody ContactAggregate contactAggregate) throws URISyntaxException {
        log.debug("REST request to save Contact : {}", contactAggregate);
        if (contactAggregate.getId() != null) {
            throw new BadRequestAlertException("A new contact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contact result = aggregateService.saveContact(contactAggregate);
        
        return ResponseEntity.created(new URI("/api/contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contacts : Updates an existing contact.
     *
     * @param contactDTO the contactDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contactDTO,
     * or with status 400 (Bad Request) if the contactDTO is not valid,
     * or with status 500 (Internal Server Error) if the contactDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     *//*
    @PutMapping("/contacts")
    @Timed
    public ResponseEntity<Contact> updateContact(@RequestBody ContactDTO contactDTO) throws URISyntaxException {
        log.debug("REST request to update Contact : {}", contactDTO);
        if (contactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Contact result = aggregateService.saveContact(contactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contactDTO.getId().toString()))
            .body(result);
    }
*/
    /**
     * GET  /contacts : get all the contacts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of contacts in body
     */
    @GetMapping("/contacts")
    @Timed
    public ResponseEntity<List<Contact>> getAllContacts(Pageable pageable) {
        log.debug("REST request to get a page of Contacts");
        Page<Contact> page = aggregateService.findAllContacts(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/contacts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * POST  /addresses : Create a new address.
     *
     * @param addressDTO the addressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new addressDTO, or with status 400 (Bad Request) if the address has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/addresses")
    @Timed
    public ResponseEntity<AddressDTO> createAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException {
        log.debug("REST request to save Address : {}", addressDTO);
        if (addressDTO.getId() != null) {
            throw new BadRequestAlertException("A new address cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddressDTO result = addressService.save(addressDTO);
        return ResponseEntity.created(new URI("/api/addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /addresses : Updates an existing address.
     *
     * @param addressDTO the addressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated addressDTO,
     * or with status 400 (Bad Request) if the addressDTO is not valid,
     * or with status 500 (Internal Server Error) if the addressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/addresses")
    @Timed
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO addressDTO) throws URISyntaxException {
        log.debug("REST request to update Address : {}", addressDTO);
        if (addressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddressDTO result = addressService.save(addressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, addressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /addresses : get all the addresses.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of addresses in body
     */
    @GetMapping("/addresses")
    @Timed
    public ResponseEntity<List<AddressDTO>> getAllAddresses(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("contact-is-null".equals(filter)) {
            log.debug("REST request to get all Addresss where contact is null");
            return new ResponseEntity<>(addressService.findAllWhereContactIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Addresses");
        Page<AddressDTO> page = addressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/addresses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
        Page<BloodGroupDTO> page = aggregateService.findAllBloodGroups(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blood-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
   
    /**
     * GET  /contacts/{id} : get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of approvalStatuses in body
     */
    @GetMapping("/contacts/getContactSetByPhoneNumber/{phoneNumber}")
    @Timed
    public ResponseEntity<List<Contact>> getContactSetByPhoneNumber(Pageable pageable,@PathVariable Long phoneNumber) {
        log.debug("REST request to get a page of contact by phone number");
        Page<Contact> page = aggregateService.findContactSetsByPhoneNumber(pageable,phoneNumber);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/phoneNumber");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * GET  /contacts/:phoneNumber : get the "phoneNumber" contact.
     *
     * @param id the id of the contactDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contactDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contacts/{phoneNumber}")
    @Timed
    public ResponseEntity<ProfileAggregate> getContactDetailsByPhoneNumber(@PathVariable Long phoneNumber) {
        log.debug("REST request to get Contact : {}", phoneNumber);
        
        ProfileAggregate profileAggregate=new ProfileAggregate();
        
        Optional<Contact> contact = aggregateService.findContactByPhoneNumber(phoneNumber);
        
        Contact contactobj=contact.get();      
        
        profileAggregate.setDisplayName(contactobj.getDisplayName());
        profileAggregate.setPhoneNumber(contactobj.getPhoneNumber());
        profileAggregate.setAge(contactobj.getAge());
        profileAggregate.setIsEligible(contactobj.isIsEligible());
        profileAggregate.setBloodGroup(contactobj.getBloodGroup().getBloodGroup());
        
        profileAggregate.setLocation(contactobj.getAddress().getLocation());
        profileAggregate.setCity(contactobj.getAddress().getCity());
        profileAggregate.setState(contactobj.getAddress().getState());
        profileAggregate.setZipCode(contactobj.getAddress().getZipCode());
        
        return ResponseUtil.wrapOrNotFound(Optional.of(profileAggregate));
    }

	@GetMapping("/contactsOfContacts/{phoneNumber}")
	@Timed
	public List<Contact> findContactSetsOfContact(@PathVariable Long phoneNumber) {
		List<Contact> contacts = aggregateService.findContactSetsOfContact(phoneNumber);
		return contacts;
	}

	@GetMapping("/contactsOfRegisteredPeople/{phoneNumber}")
	@Timed
	public List<Contact> findContactsOfRegisteredPeople(@PathVariable Long phoneNumber) {
		List<Contact> contacts = aggregateService.findContactsOfRegisteredPeople(phoneNumber);
		return contacts;
	}

	@GetMapping("/contactsByPhoneNumberAndBloodGroup/{phoneNumber}/{bloodGroup}")
	@Timed
	public List<Contact> findContactSetsOfContactByBloodGroup(@PathVariable Long phoneNumber,
			@PathVariable String bloodGroup) {
		List<Contact> contacts = aggregateService.findContactSetsOfContactByBloodGroup(phoneNumber, bloodGroup);
		return contacts;
	}


}
