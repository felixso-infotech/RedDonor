package com.lxisoft.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contact.class)
public abstract class Contact_ {

	public static volatile SingularAttribute<Contact, BloodGroup> bloodGroup;
	public static volatile SingularAttribute<Contact, Long> phoneNumber;
	public static volatile SingularAttribute<Contact, Address> address;
	public static volatile SingularAttribute<Contact, Contact> contact;
	public static volatile SingularAttribute<Contact, String> name;
	public static volatile SingularAttribute<Contact, Boolean> isEligible;
	public static volatile SingularAttribute<Contact, Long> id;
	public static volatile SingularAttribute<Contact, String> email;
	public static volatile SingularAttribute<Contact, Long> age;
	public static volatile SetAttribute<Contact, Contact> contacts;

}

