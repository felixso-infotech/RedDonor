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

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.Address;
import com.lxisoft.domain.Contact;
import com.lxisoft.model.ContactAggregate;
import com.lxisoft.repository.AddressRepository;
import com.lxisoft.repository.BloodGroupRepository;
import com.lxisoft.repository.ContactRepository;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.AddressDTO;
import com.lxisoft.service.dto.BloodGroupDTO;

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
    public Contact saveContact(ContactAggregate contactAggregate) {
    	
        log.debug("Request to save Contact : {}", contactAggregate);
        
        Contact contact=null;
                
        if((contactAggregate.getPhoneNumber()) != (contactRepository.findContactByPhoneNumber(contactAggregate.getPhoneNumber()).get().getPhoneNumber()))
        {
         		contact = new Contact();
        		
        		contact.setDisplayName(contactAggregate.getDisplayName());
        		contact.setPhoneNumber(contactAggregate.getPhoneNumber());
        		contact.setBloodGroup(contactAggregate.getBloodGroup());
        		contact.setEmail(contactAggregate.getEmail());
        		contact.setAge(contactAggregate.getAge());      		
        		contact.setAddress(contactAggregate.getAddress()); 
        		contact.setContactSets(contactAggregate.getContactSets());
        		       		               
        		    List<Contact> contactList=new ArrayList<Contact>(contactAggregate.getContactSets());
        		          		    
        		     for(int j=0;j>=contactList.size();j++)       		    	 
        		     { 
        		    	 
        		     if(contactList.get(j).getPhoneNumber() != contactRepository.findContactByPhoneNumber(contactList.get(j).getPhoneNumber()).get().getPhoneNumber())
        		                    	 
        		           {
        			         contact = new Contact();
            		
        			         contact.setDisplayName(contactList.get(j).getDisplayName());
        			
        			         contact.setPhoneNumber(contactList.get(j).getPhoneNumber());      			        
             			    			
        			         contact = contactRepository.save(contact);
        		                    	
        		           }
        		      else
        		           {
        		        	   
        		           System.out.print(contactList.get(j).getPhoneNumber()+"\t This PhnNo already exist and owner name is"+contactList.get(j).getDisplayName());
        		           
        		           contact= contactRepository.findContactByPhoneNumber(contactList.get(j).getPhoneNumber()).get();
        		           
        		            contact.setBloodGroup(contactAggregate.getBloodGroup());
        	        		contact.setEmail(contactAggregate.getEmail());
        	        		contact.setAge(contactAggregate.getAge());      		
        	        		contact.setAddress(contactAggregate.getAddress()); 
        	        		
        	        		contact = contactRepository.save(contact);
        		           
        		           }
        			
        		      }                                 
        		                             		
                contact = contactRepository.save(contact);
        	}
        
        else
        {
        	contact= contactRepository.findContactByPhoneNumber(contactAggregate.getPhoneNumber()).get();
	           
            contact.setBloodGroup(contactAggregate.getBloodGroup());
    		contact.setEmail(contactAggregate.getEmail());
    		contact.setAge(contactAggregate.getAge());      		
    		contact.setAddress(contactAggregate.getAddress()); 
    		    		
    		contact = contactRepository.save(contact);
        }
        
        
        return contact;
    }

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Contact> findAllContacts(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable);
            
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

   
      /**
     * Get all the contactSets.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Contact> findContactSetsByPhoneNumber(Pageable pageable,Long phoneNumber){
        
        log.debug("Request to get all Contacts by phone number");
      
        Contact contact=contactRepository.findContactByPhoneNumber(phoneNumber).get();      
        
        List<Contact> contactList=new ArrayList<Contact>(contact.getContactSets());
        
        Page<Contact> page = new PageImpl<Contact>(contactList, pageable, contactList.size());
        
        return page;
      }
    
    
    /**
     * Get one contact by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)	
	public Optional<Contact> findContactByPhoneNumber(Long phoneNumber) {	    
	        log.debug("Request to get Contact : {}", phoneNumber);
	        Optional<Contact> contact=contactRepository.findContactByPhoneNumber(phoneNumber);
	        return contact;         
	    }
	

	

	@Override
	public List<Contact> findContactSetsOfContact(Long phoneNumber) {
		return contactRepository.findContactSetsOfContact(phoneNumber);
	}

	@Override
	public List<Contact> findContactsOfRegisteredPeople(Long phoneNumber) {
		List<Contact> contacts = contactRepository.findContactSetsOfContact(phoneNumber);
		List<Contact> registered = new ArrayList<Contact>();
		contacts.forEach(c -> {
			if (c.getBloodGroup() != null)
				registered.add(c);
		});
		return registered;
	}

	@Override
	public List<Contact> findContactSetsOfContactByBloodGroup(Long phoneNumber, String bloodGroup) {
		List<Contact> contacts = contactRepository.findContactSetsOfContact(phoneNumber);
		List<Contact> contactsByBloodGroup = new ArrayList<Contact>();
		contacts.forEach(c -> {
			if (c.getBloodGroup().getBloodGroup().equals(bloodGroup))
				contactsByBloodGroup.add(c);
		});
		return contactsByBloodGroup;
	}

}
