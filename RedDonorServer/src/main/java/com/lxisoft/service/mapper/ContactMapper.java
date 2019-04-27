package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contact and its DTO ContactDTO.
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class, BloodGroupMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "address.id", target = "addressId")
    @Mapping(source = "bloodGroup.id", target = "bloodGroupId")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "addressId", target = "address")
    @Mapping(source = "bloodGroupId", target = "bloodGroup")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
