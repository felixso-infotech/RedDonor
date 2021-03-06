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
package com.lxisoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.domain.Contact;
import com.lxisoft.model.ContactAggregate;
import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.dto.BloodGroupDTO;


public interface AggregateService {
	
	 /**
     * Save a contact.
     *
     * @param contactAggregate the entity to save
     * @return the persisted entity
     */
    Contact saveContact(ContactAggregate contactAggregate);
    
    /**
     * Save a contactSet.
     *
     * @param contactAggregate the entity to save
     * @return the persisted entity
     */
    
    Contact saveContactsetByPhoneNumber(ContactAggregate contactAggregate);
    
    /**
     * Save a contact eligibility.
     *
     * @param contactAggregate the entity to save
     * @return the persisted entity
     */
    
    Contact updateContactIsEligible(ContactAggregate contactAggregate);


    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Contact> findAllContacts(Pageable pageable);
    
    
    /**
     * Get all the AddressDTO where Contact is null.
     *
     * @return the list of entities
     */
    List<AddressDTO> findAllWhereContactIsNull();

    
    /**
     * Get all the bloodGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BloodGroupDTO> findAllBloodGroups(Pageable pageable);

    
    /**
     * Get all the contactSets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Contact> findContactSetsByPhoneNumber(Pageable pageable,Long phoneNumber);
    
    /**
     * Get the "phoneNumber" contact.
     *
     * @param phoneNumber the id of the entity
     * @return the entity
     */

	Optional<Contact> findContactByPhoneNumber(Long phoneNumber);

	List<Contact> findContactSetsOfContact(Long phoneNumber);

	List<Contact> findContactsOfRegisteredPeople(Long phoneNumber);

	List<Contact> findContactSetsOfContactByBloodGroup(Long phoneNumber, String bloodGroup);

	
}
