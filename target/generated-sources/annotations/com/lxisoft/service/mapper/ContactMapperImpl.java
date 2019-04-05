package com.lxisoft.service.mapper;

import com.lxisoft.domain.Address;
import com.lxisoft.domain.BloodGroup;
import com.lxisoft.domain.Contact;
import com.lxisoft.service.dto.ContactDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-04T15:56:15+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private BloodGroupMapper bloodGroupMapper;

    @Override
    public List<Contact> toEntity(List<ContactDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>( dtoList.size() );
        for ( ContactDTO contactDTO : dtoList ) {
            list.add( toEntity( contactDTO ) );
        }

        return list;
    }

    @Override
    public List<ContactDTO> toDto(List<Contact> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContactDTO> list = new ArrayList<ContactDTO>( entityList.size() );
        for ( Contact contact : entityList ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    @Override
    public ContactDTO toDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        Long id = contactBloodGroupId( contact );
        if ( id != null ) {
            contactDTO.setBloodGroupId( id );
        }
        Long id1 = contactContactId( contact );
        if ( id1 != null ) {
            contactDTO.setContactId( id1 );
        }
        Long id2 = contactAddressId( contact );
        if ( id2 != null ) {
            contactDTO.setAddressId( id2 );
        }
        contactDTO.setId( contact.getId() );
        contactDTO.setName( contact.getName() );
        contactDTO.setPhoneNumber( contact.getPhoneNumber() );
        contactDTO.setEmail( contact.getEmail() );
        contactDTO.setAge( contact.getAge() );
        contactDTO.setIsEligible( contact.isIsEligible() );

        return contactDTO;
    }

    @Override
    public Contact toEntity(ContactDTO contactDTO) {
        if ( contactDTO == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setBloodGroup( bloodGroupMapper.fromId( contactDTO.getBloodGroupId() ) );
        contact.setAddress( addressMapper.fromId( contactDTO.getAddressId() ) );
        contact.setContact( fromId( contactDTO.getContactId() ) );
        contact.setId( contactDTO.getId() );
        contact.setName( contactDTO.getName() );
        contact.setPhoneNumber( contactDTO.getPhoneNumber() );
        contact.setEmail( contactDTO.getEmail() );
        contact.setAge( contactDTO.getAge() );
        contact.setIsEligible( contactDTO.isIsEligible() );

        return contact;
    }

    private Long contactBloodGroupId(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        BloodGroup bloodGroup = contact.getBloodGroup();
        if ( bloodGroup == null ) {
            return null;
        }
        Long id = bloodGroup.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contactContactId(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        Contact contact1 = contact.getContact();
        if ( contact1 == null ) {
            return null;
        }
        Long id = contact1.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contactAddressId(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        Address address = contact.getAddress();
        if ( address == null ) {
            return null;
        }
        Long id = address.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
