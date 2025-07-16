package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.GroupDTO.GroupDTOBuilder;
import com.softserve.dto.PeriodDTO;
import com.softserve.dto.RoomDTO;
import com.softserve.dto.RoomTypeDTO;
import com.softserve.dto.SemesterDTO;
import com.softserve.dto.SubjectDTO;
import com.softserve.dto.TemporaryScheduleForArchiveDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Period;
import com.softserve.entity.Room;
import com.softserve.entity.RoomType;
import com.softserve.entity.Semester;
import com.softserve.entity.Subject;
import com.softserve.entity.TemporarySchedule;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class TemporaryScheduleMapperForArchiveImpl implements TemporaryScheduleMapperForArchive {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<TemporaryScheduleForArchiveDTO> convertToNewDtoList(List<TemporarySchedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<TemporaryScheduleForArchiveDTO> list = new ArrayList<TemporaryScheduleForArchiveDTO>( schedules.size() );
        for ( TemporarySchedule temporarySchedule : schedules ) {
            list.add( temporaryScheduleToTemporaryScheduleForArchiveDTO( temporarySchedule ) );
        }

        return list;
    }

    protected SubjectDTO subjectToSubjectDTO(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectDTO subjectDTO = new SubjectDTO();

        subjectDTO.setId( subject.getId() );
        subjectDTO.setName( subject.getName() );
        subjectDTO.setDisable( subject.isDisable() );

        return subjectDTO;
    }

    protected GroupDTO groupToGroupDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTOBuilder groupDTO = GroupDTO.builder();

        groupDTO.id( group.getId() );
        groupDTO.disable( group.isDisable() );
        groupDTO.title( group.getTitle() );

        return groupDTO.build();
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

    protected RoomDTO roomToRoomDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId( room.getId() );
        roomDTO.setName( room.getName() );
        roomDTO.setDisable( room.isDisable() );
        roomDTO.setType( roomTypeToRoomTypeDTO( room.getType() ) );

        return roomDTO;
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

    protected LinkedHashSet<PeriodDTO> periodSetToPeriodDTOLinkedHashSet(Set<Period> set) {
        if ( set == null ) {
            return null;
        }

        LinkedHashSet<PeriodDTO> linkedHashSet = new LinkedHashSet<PeriodDTO>();
        for ( Period period : set ) {
            linkedHashSet.add( periodToPeriodDTO( period ) );
        }

        return linkedHashSet;
    }

    protected SemesterDTO semesterToSemesterDTO(Semester semester) {
        if ( semester == null ) {
            return null;
        }

        SemesterDTO semesterDTO = new SemesterDTO();

        semesterDTO.setId( semester.getId() );
        semesterDTO.setDescription( semester.getDescription() );
        semesterDTO.setYear( semester.getYear() );
        semesterDTO.setStartDay( semester.getStartDay() );
        semesterDTO.setEndDay( semester.getEndDay() );
        semesterDTO.setCurrentSemester( semester.isCurrentSemester() );
        semesterDTO.setDefaultSemester( semester.isDefaultSemester() );
        Set<DayOfWeek> set = semester.getDaysOfWeek();
        if ( set != null ) {
            semesterDTO.setDaysOfWeek( new TreeSet<DayOfWeek>( set ) );
        }
        semesterDTO.setPeriods( periodSetToPeriodDTOLinkedHashSet( semester.getPeriods() ) );
        semesterDTO.setDisable( semester.isDisable() );

        return semesterDTO;
    }

    protected TemporaryScheduleForArchiveDTO temporaryScheduleToTemporaryScheduleForArchiveDTO(TemporarySchedule temporarySchedule) {
        if ( temporarySchedule == null ) {
            return null;
        }

        TemporaryScheduleForArchiveDTO temporaryScheduleForArchiveDTO = new TemporaryScheduleForArchiveDTO();

        temporaryScheduleForArchiveDTO.setId( temporarySchedule.getId() );
        temporaryScheduleForArchiveDTO.setDate( temporarySchedule.getDate() );
        temporaryScheduleForArchiveDTO.setLinkToMeeting( temporarySchedule.getLinkToMeeting() );
        temporaryScheduleForArchiveDTO.setSubjectForSite( temporarySchedule.getSubjectForSite() );
        temporaryScheduleForArchiveDTO.setTeacher( teacherMapper.teacherToTeacherDTO( temporarySchedule.getTeacher() ) );
        temporaryScheduleForArchiveDTO.setLessonType( temporarySchedule.getLessonType() );
        temporaryScheduleForArchiveDTO.setSubject( subjectToSubjectDTO( temporarySchedule.getSubject() ) );
        temporaryScheduleForArchiveDTO.setGroup( groupToGroupDTO( temporarySchedule.getGroup() ) );
        temporaryScheduleForArchiveDTO.setRoom( roomToRoomDTO( temporarySchedule.getRoom() ) );
        temporaryScheduleForArchiveDTO.setSemester( semesterToSemesterDTO( temporarySchedule.getSemester() ) );
        temporaryScheduleForArchiveDTO.setPeriod( periodToPeriodDTO( temporarySchedule.getPeriod() ) );
        temporaryScheduleForArchiveDTO.setGrouped( temporarySchedule.isGrouped() );
        temporaryScheduleForArchiveDTO.setVacation( temporarySchedule.isVacation() );

        return temporaryScheduleForArchiveDTO;
    }
}
