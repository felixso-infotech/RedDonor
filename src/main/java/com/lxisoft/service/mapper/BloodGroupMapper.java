package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.BloodGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BloodGroup and its DTO BloodGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BloodGroupMapper extends EntityMapper<BloodGroupDTO, BloodGroup> {



    default BloodGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        BloodGroup bloodGroup = new BloodGroup();
        bloodGroup.setId(id);
        return bloodGroup;
    }
}
