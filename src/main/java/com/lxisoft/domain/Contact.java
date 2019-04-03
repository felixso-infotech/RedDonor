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

    @Column(name = "name")
    private String name;

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
    @JsonIgnoreProperties("contacts")
    private Contact contact;

    @OneToMany(mappedBy = "contact")
    private Set<Contact> contacts = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private BloodGroup bloodGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
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

    public Contact getContact() {
        return contact;
    }

    public Contact contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Contact contacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Contact addContacts(Contact contact) {
        this.contacts.add(contact);
        contact.setContact(this);
        return this;
    }

    public Contact removeContacts(Contact contact) {
        this.contacts.remove(contact);
        contact.setContact(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
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
            ", name='" + getName() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", email='" + getEmail() + "'" +
            ", age=" + getAge() +
            ", isEligible='" + isIsEligible() + "'" +
            "}";
    }
}
