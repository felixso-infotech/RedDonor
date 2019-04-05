package com.lxisoft.service.mapper;

import com.lxisoft.domain.BloodGroup;
import com.lxisoft.service.dto.BloodGroupDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-04-04T15:56:15+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class BloodGroupMapperImpl implements BloodGroupMapper {

    @Override
    public BloodGroup toEntity(BloodGroupDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BloodGroup bloodGroup = new BloodGroup();

        bloodGroup.setId( dto.getId() );
        bloodGroup.setBloodGroup( dto.getBloodGroup() );

        return bloodGroup;
    }

    @Override
    public BloodGroupDTO toDto(BloodGroup entity) {
        if ( entity == null ) {
            return null;
        }

        BloodGroupDTO bloodGroupDTO = new BloodGroupDTO();

        bloodGroupDTO.setId( entity.getId() );
        bloodGroupDTO.setBloodGroup( entity.getBloodGroup() );

        return bloodGroupDTO;
    }

    @Override
    public List<BloodGroup> toEntity(List<BloodGroupDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BloodGroup> list = new ArrayList<BloodGroup>( dtoList.size() );
        for ( BloodGroupDTO bloodGroupDTO : dtoList ) {
            list.add( toEntity( bloodGroupDTO ) );
        }

        return list;
    }

    @Override
    public List<BloodGroupDTO> toDto(List<BloodGroup> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BloodGroupDTO> list = new ArrayList<BloodGroupDTO>( entityList.size() );
        for ( BloodGroup bloodGroup : entityList ) {
            list.add( toDto( bloodGroup ) );
        }

        return list;
    }
}
