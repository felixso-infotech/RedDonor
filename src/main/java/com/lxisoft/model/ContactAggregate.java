package com.lxisoft.model;

import java.util.HashSet;
import java.util.Set;

import com.lxisoft.domain.Address;
import com.lxisoft.domain.BloodGroup;
import com.lxisoft.domain.Contact;

public class ContactAggregate {
	
	private Long id;
	
	private String displayName;
    
    private Long phoneNumber;
   
    private String email;
   
    private Long age;
    
    private Boolean isEligible;
    
    private Address address;

    private BloodGroup bloodGroup;
    
    private Set<Contact> contactSets = new HashSet<>();

	
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Boolean getIsEligible() {
		return isEligible;
	}

	public void setIsEligible(Boolean isEligible) {
		this.isEligible = isEligible;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Set<Contact> getContactSets() {
		return contactSets;
	}

	public void setContactSets(Set<Contact> contactSets) {
		this.contactSets = contactSets;
	}

	

}
