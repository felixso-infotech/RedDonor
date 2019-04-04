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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.dto.ContactDTO;

public interface AggregateService {
	
	 /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    ContactDTO saveContact(ContactDTO contactDTO);

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ContactDTO> findAllContacts(Pageable pageable);
    
    /**
     * Save a address.
     *
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    AddressDTO saveAddress(AddressDTO addressDTO);

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AddressDTO> findAllAddress(Pageable pageable);
    /**
     * Get all the AddressDTO where Contact is null.
     *
     * @return the list of entities
     */
    List<AddressDTO> findAllWhereContactIsNull();

}
