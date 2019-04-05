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
package com.lxisoft.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.Address;
import com.lxisoft.domain.Contact;
import com.lxisoft.repository.AddressRepository;
import com.lxisoft.repository.BloodGroupRepository;
import com.lxisoft.repository.ContactRepository;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.dto.BloodGroupDTO;
import com.lxisoft.service.dto.ContactDTO;
import com.lxisoft.service.mapper.AddressMapper;
import com.lxisoft.service.mapper.BloodGroupMapper;
import com.lxisoft.service.mapper.ContactMapper;

@Service
@Transactional
public class AggregateServiceImpl implements AggregateService {
	
	private final Logger log = LoggerFactory.getLogger(AggregateServiceImpl.class);

	@Autowired
	ContactRepository contactRepository;

	@Autowired
    ContactMapper contactMapper;
	
	@Autowired
	AddressRepository addressRepository;

	@Autowired
    AddressMapper addressMapper;

	@Autowired
	BloodGroupRepository bloodGroupRepository;
	
	@Autowired
	BloodGroupMapper bloodGroupMapper;

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContactDTO saveContact(ContactDTO contactDTO) {
        log.debug("Request to save Contact : {}", contactDTO);

        Contact contact = contactMapper.toEntity(contactDTO);
        contact = contactRepository.save(contact);
        return contactMapper.toDto(contact);
    }

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContactDTO> findAllContacts(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable)
            .map(contactMapper::toDto);
    }

    /**
     * Save a address.
     *
     * @param addressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);

        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    /**
     * Get all the addresses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AddressDTO> findAllAddress(Pageable pageable) {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable)
            .map(addressMapper::toDto);
    }



    /**
     *  get all the addresses where Contact is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<AddressDTO> findAllWhereContactIsNull() {
        log.debug("Request to get all addresses where Contact is null");
        return StreamSupport
            .stream(addressRepository.findAll().spliterator(), false)
            .filter(address -> address.getContact() == null)
            .map(addressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the bloodGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BloodGroupDTO> findAllBloodGroups(Pageable pageable) {
        log.debug("Request to get all BloodGroups");
        return bloodGroupRepository.findAll(pageable)
            .map(bloodGroupMapper::toDto);
    }

    
}
