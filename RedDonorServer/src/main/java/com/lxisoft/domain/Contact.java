package com.lxisoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * Contact entity.
 * @Author Anjali
 */
@ApiModel(description = "Contact entity. @Author Anjali")
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Long age;

    @Column(name = "is_eligible")
    private Boolean isEligible;

    @OneToOne    @JoinColumn(unique = true)
    private Address address;

    @ManyToOne
    @JsonIgnoreProperties("")
    private BloodGroup bloodGroup;

    @ManyToMany
    @JoinTable(name = "contact_contact_set",
               joinColumns = @JoinColumn(name = "contacts_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "contact_sets_id", referencedColumnName = "id"))
    private Set<Contact> contactSets = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Contact displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Contact phoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAge() {
        return age;
    }

    public Contact age(Long age) {
        this.age = age;
        return this;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Boolean isIsEligible() {
        return isEligible;
    }

    public Contact isEligible(Boolean isEligible) {
        this.isEligible = isEligible;
        return this;
    }

    public void setIsEligible(Boolean isEligible) {
        this.isEligible = isEligible;
    }

    public Address getAddress() {
        return address;
    }

    public Contact address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public Contact bloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
        return this;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Set<Contact> getContactSets() {
        return contactSets;
    }

    public Contact contactSets(Set<Contact> contacts) {
        this.contactSets = contacts;
        return this;
    }

    public Contact addContactSet(Contact contact) {
        this.contactSets.add(contact);
        return this;
    }

    public Contact removeContactSet(Contact contact) {
        this.contactSets.remove(contact);
        return this;
    }

    public void setContactSets(Set<Contact> contacts) {
        this.contactSets = contacts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        if (contact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", displayName='" + getDisplayName() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", email='" + getEmail() + "'" +
            ", age=" + getAge() +
            ", isEligible='" + isIsEligible() + "'" +
            "}";
    }
}
