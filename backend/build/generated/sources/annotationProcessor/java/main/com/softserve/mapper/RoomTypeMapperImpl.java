package com.softserve.mapper;

import com.softserve.dto.RoomTypeDTO;
import com.softserve.entity.RoomType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class RoomTypeMapperImpl implements RoomTypeMapper {

    @Override
    public RoomTypeDTO roomTypeToRoomTypeDTO(RoomType roomType) {
        if ( roomType == null ) {
            return null;
        }

        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();

        roomTypeDTO.setId( roomType.getId() );
        roomTypeDTO.setDescription( roomType.getDescription() );

        return roomTypeDTO;
    }

    @Override
    public RoomType roomTypeDTOTRoomType(RoomTypeDTO roomTypeDTO) {
        if ( roomTypeDTO == null ) {
            return null;
        }

        RoomType roomType = new RoomType();

        roomType.setId( roomTypeDTO.getId() );
        roomType.setDescription( roomTypeDTO.getDescription() );

        return roomType;
    }

    @Override
    public List<RoomTypeDTO> roomTypesToRoomTypeDTOs(List<RoomType> roomTypes) {
        if ( roomTypes == null ) {
            return null;
        }

        List<RoomTypeDTO> list = new ArrayList<RoomTypeDTO>( roomTypes.size() );
        for ( RoomType roomType : roomTypes ) {
            list.add( roomTypeToRoomTypeDTO( roomType ) );
        }

        return list;
    }
}
