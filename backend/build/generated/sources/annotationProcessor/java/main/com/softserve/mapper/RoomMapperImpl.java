package com.softserve.mapper;

import com.softserve.dto.RoomDTO;
import com.softserve.dto.RoomTypeDTO;
import com.softserve.entity.Room;
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
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room convertToEntity(RoomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( dto.getId() );
        room.setName( dto.getName() );
        room.setType( roomTypeDTOToRoomType( dto.getType() ) );
        room.setDisable( dto.isDisable() );

        return room;
    }

    @Override
    public RoomDTO convertToDto(Room entity) {
        if ( entity == null ) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId( entity.getId() );
        roomDTO.setName( entity.getName() );
        roomDTO.setDisable( entity.isDisable() );
        roomDTO.setType( roomTypeToRoomTypeDTO( entity.getType() ) );

        return roomDTO;
    }

    @Override
    public List<RoomDTO> convertToDtoList(List<Room> rooms) {
        if ( rooms == null ) {
            return null;
        }

        List<RoomDTO> list = new ArrayList<RoomDTO>( rooms.size() );
        for ( Room room : rooms ) {
            list.add( convertToDto( room ) );
        }

        return list;
    }

    protected RoomType roomTypeDTOToRoomType(RoomTypeDTO roomTypeDTO) {
        if ( roomTypeDTO == null ) {
            return null;
        }

        RoomType roomType = new RoomType();

        roomType.setId( roomTypeDTO.getId() );
        roomType.setDescription( roomTypeDTO.getDescription() );

        return roomType;
    }

    protected RoomTypeDTO roomTypeToRoomTypeDTO(RoomType roomType) {
        if ( roomType == null ) {
            return null;
        }

        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();

        roomTypeDTO.setId( roomType.getId() );
        roomTypeDTO.setDescription( roomType.getDescription() );

        return roomTypeDTO;
    }
}
