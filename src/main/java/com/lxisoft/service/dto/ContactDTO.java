package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.lxisoft.domain.Contact;

/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    private String name;

    private Long phoneNumber;

    private String email;

    private Long age;

    private Boolean isEligible;

    private Long addressId;

    private Long contactId;

    private Long bloodGroupId;
    
    private Set<ContactDTO> contactSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean isIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Long bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;
        if (contactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", email='" + getEmail() + "'" +
            ", age=" + getAge() +
            ", isEligible='" + isIsEligible() + "'" +
            ", address=" + getAddressId() +
            ", contact=" + getContactId() +
            ", bloodGroup=" + getBloodGroupId() +
            "}";
    }



	public Boolean getIsEligible() {
		return isEligible;
	}

	public Set<ContactDTO> getContactSet() {
		return contactSet;
	}

	public void setContactSet(Set<ContactDTO> contactSet) {
		this.contactSet = contactSet;
	}
}
