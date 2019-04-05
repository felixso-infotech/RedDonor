package com.lxisoft.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    private String displayName;

    private Long phoneNumber;

    private String email;

    private Long age;

    private Boolean isEligible;

    private Long addressId;

    private Long bloodGroupId;

    private Set<ContactDTO> contactSets = new HashSet<>();
    
    private List<ContactDTO> phoneNumbers= new ArrayList<>();

    

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

    public Boolean isIsEligible() {
        return isEligible;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
    }
    
    public Boolean getIsEligible() {
		return isEligible;
	}
    
    public List<ContactDTO> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<ContactDTO> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getBloodGroupId() {
        return bloodGroupId;
    }

    public void setBloodGroupId(Long bloodGroupId) {
        this.bloodGroupId = bloodGroupId;
    }

    public Set<ContactDTO> getContactSets() {
        return contactSets;
    }

    public void setContactSets(Set<ContactDTO> contacts) {
        this.contactSets = contacts;
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
            ", displayName='" + getDisplayName() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", email='" + getEmail() + "'" +
            ", age=" + getAge() +
            ", isEligible='" + isIsEligible() + "'" +
            ", address=" + getAddressId() +
            ", bloodGroup=" + getBloodGroupId() +
            "}";
    }
}
