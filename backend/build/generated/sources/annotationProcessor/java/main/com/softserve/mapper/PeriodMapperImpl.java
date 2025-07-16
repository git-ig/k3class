package com.softserve.mapper;

import com.softserve.dto.AddPeriodDTO;
import com.softserve.dto.PeriodDTO;
import com.softserve.entity.Period;
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
public class PeriodMapperImpl implements PeriodMapper {

    @Override
    public Period convertToEntity(AddPeriodDTO addPeriodDTO) {
        if ( addPeriodDTO == null ) {
            return null;
        }

        Period period = new Period();

        period.setStartTime( addPeriodDTO.getStartTime() );
        period.setEndTime( addPeriodDTO.getEndTime() );
        period.setName( addPeriodDTO.getName() );

        return period;
    }

    @Override
    public PeriodDTO convertToDto(Period entity) {
        if ( entity == null ) {
            return null;
        }

        PeriodDTO periodDTO = new PeriodDTO();

        periodDTO.setId( entity.getId() );
        periodDTO.setStartTime( entity.getStartTime() );
        periodDTO.setEndTime( entity.getEndTime() );
        periodDTO.setName( entity.getName() );

        return periodDTO;
    }

    @Override
    public Period convertToEntity(PeriodDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Period period = new Period();

        period.setId( dto.getId() );
        period.setStartTime( dto.getStartTime() );
        period.setEndTime( dto.getEndTime() );
        period.setName( dto.getName() );

        return period;
    }

    @Override
    public List<PeriodDTO> convertToDtoList(List<Period> periods) {
        if ( periods == null ) {
            return null;
        }

        List<PeriodDTO> list = new ArrayList<PeriodDTO>( periods.size() );
        for ( Period period : periods ) {
            list.add( convertToDto( period ) );
        }

        return list;
    }

    @Override
    public List<Period> convertToEntityList(List<AddPeriodDTO> periods) {
        if ( periods == null ) {
            return null;
        }

        List<Period> list = new ArrayList<Period>( periods.size() );
        for ( AddPeriodDTO addPeriodDTO : periods ) {
            list.add( convertToEntity( addPeriodDTO ) );
        }

        return list;
    }
}
