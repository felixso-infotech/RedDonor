package com.lxisoft.repository;

import com.lxisoft.domain.Contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Contact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	Page<Contact> findAllContactsByPhoneNumber(Pageable pageable, Long phoneNumber);
	
}
