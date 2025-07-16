package com.softserve.mapper;

import com.softserve.dto.PeriodDTO;
import com.softserve.dto.TemporaryScheduleDTO;
import com.softserve.dto.TemporaryScheduleDTOForDashboard;
import com.softserve.dto.TemporaryScheduleSaveDTO;
import com.softserve.entity.Period;
import com.softserve.entity.Schedule;
import com.softserve.entity.TemporarySchedule;
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
public class TemporaryScheduleMapperImpl implements TemporaryScheduleMapper {

    @Override
    public TemporarySchedule convertToEntity(TemporaryScheduleSaveDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TemporarySchedule temporarySchedule = new TemporarySchedule();

        temporarySchedule.setScheduleId( dtoScheduleId( dto ) );
        temporarySchedule.setId( dto.getId() );
        temporarySchedule.setDate( dto.getDate() );
        temporarySchedule.setLinkToMeeting( dto.getLinkToMeeting() );
        temporarySchedule.setSubjectForSite( dto.getSubjectForSite() );
        temporarySchedule.setLessonType( dto.getLessonType() );
        temporarySchedule.setTeacher( dto.getTeacher() );
        temporarySchedule.setSubject( dto.getSubject() );
        temporarySchedule.setGroup( dto.getGroup() );
        temporarySchedule.setSemester( dto.getSemester() );
        temporarySchedule.setRoom( dto.getRoom() );
        temporarySchedule.setPeriod( dto.getPeriod() );
        temporarySchedule.setGrouped( dto.isGrouped() );
        temporarySchedule.setVacation( dto.isVacation() );
        temporarySchedule.setNotification( dto.isNotification() );

        return temporarySchedule;
    }

    @Override
    public TemporaryScheduleDTO convertToDto(TemporarySchedule entity) {
        if ( entity == null ) {
            return null;
        }

        TemporaryScheduleDTO temporaryScheduleDTO = new TemporaryScheduleDTO();

        temporaryScheduleDTO.setId( entity.getId() );
        temporaryScheduleDTO.setDate( entity.getDate() );
        temporaryScheduleDTO.setLinkToMeeting( entity.getLinkToMeeting() );
        temporaryScheduleDTO.setSubjectForSite( entity.getSubjectForSite() );
        temporaryScheduleDTO.setTeacher( entity.getTeacher() );
        temporaryScheduleDTO.setLessonType( entity.getLessonType() );
        temporaryScheduleDTO.setSubject( entity.getSubject() );
        temporaryScheduleDTO.setGroup( entity.getGroup() );
        temporaryScheduleDTO.setRoom( entity.getRoom() );
        temporaryScheduleDTO.setSemester( entity.getSemester() );
        temporaryScheduleDTO.setPeriod( periodToPeriodDTO( entity.getPeriod() ) );
        temporaryScheduleDTO.setGrouped( entity.isGrouped() );
        temporaryScheduleDTO.setVacation( entity.isVacation() );

        return temporaryScheduleDTO;
    }

    @Override
    public TemporaryScheduleDTOForDashboard convertToDtoForDashboard(TemporarySchedule entity) {
        if ( entity == null ) {
            return null;
        }

        TemporaryScheduleDTOForDashboard temporaryScheduleDTOForDashboard = new TemporaryScheduleDTOForDashboard();

        temporaryScheduleDTOForDashboard.setDate( entity.getDate() );
        temporaryScheduleDTOForDashboard.setLinkToMeeting( entity.getLinkToMeeting() );
        temporaryScheduleDTOForDashboard.setSubjectForSite( entity.getSubjectForSite() );
        temporaryScheduleDTOForDashboard.setTeacher( entity.getTeacher() );
        temporaryScheduleDTOForDashboard.setLessonType( entity.getLessonType() );
        temporaryScheduleDTOForDashboard.setSubject( entity.getSubject() );
        temporaryScheduleDTOForDashboard.setGroup( entity.getGroup() );
        temporaryScheduleDTOForDashboard.setRoom( entity.getRoom() );
        temporaryScheduleDTOForDashboard.setPeriod( entity.getPeriod() );
        temporaryScheduleDTOForDashboard.setGrouped( entity.isGrouped() );
        temporaryScheduleDTOForDashboard.setVacation( entity.isVacation() );

        return temporaryScheduleDTOForDashboard;
    }

    @Override
    public List<TemporaryScheduleDTO> convertToDtoList(List<TemporarySchedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<TemporaryScheduleDTO> list = new ArrayList<TemporaryScheduleDTO>( schedules.size() );
        for ( TemporarySchedule temporarySchedule : schedules ) {
            list.add( convertToDto( temporarySchedule ) );
        }

        return list;
    }

    private Long dtoScheduleId(TemporaryScheduleSaveDTO temporaryScheduleSaveDTO) {
        if ( temporaryScheduleSaveDTO == null ) {
            return null;
        }
        Schedule schedule = temporaryScheduleSaveDTO.getSchedule();
        if ( schedule == null ) {
            return null;
        }
        Long id = schedule.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected PeriodDTO periodToPeriodDTO(Period period) {
        if ( period == null ) {
            return null;
        }

        PeriodDTO periodDTO = new PeriodDTO();

        periodDTO.setId( period.getId() );
        periodDTO.setStartTime( period.getStartTime() );
        periodDTO.setEndTime( period.getEndTime() );
        periodDTO.setName( period.getName() );

        return periodDTO;
    }
}
