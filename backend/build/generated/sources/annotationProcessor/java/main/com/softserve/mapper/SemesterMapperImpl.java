package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.PeriodDTO;
import com.softserve.dto.SemesterDTO;
import com.softserve.dto.SemesterWithGroupsDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Period;
import com.softserve.entity.Semester;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
public class SemesterMapperImpl implements SemesterMapper {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public SemesterDTO semesterToSemesterDTO(Semester semester) {
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

    @Override
    public Semester semesterDTOToSemester(SemesterDTO semesterDTO) {
        if ( semesterDTO == null ) {
            return null;
        }

        Semester semester = new Semester();

        semester.setId( semesterDTO.getId() );
        semester.setDescription( semesterDTO.getDescription() );
        semester.setYear( semesterDTO.getYear() );
        semester.setStartDay( semesterDTO.getStartDay() );
        semester.setEndDay( semesterDTO.getEndDay() );
        semester.setCurrentSemester( semesterDTO.isCurrentSemester() );
        semester.setDefaultSemester( semesterDTO.isDefaultSemester() );
        TreeSet<DayOfWeek> treeSet = semesterDTO.getDaysOfWeek();
        if ( treeSet != null ) {
            semester.setDaysOfWeek( new HashSet<DayOfWeek>( treeSet ) );
        }
        semester.setPeriods( periodDTOLinkedHashSetToPeriodSet( semesterDTO.getPeriods() ) );
        semester.setDisable( semesterDTO.isDisable() );

        return semester;
    }

    @Override
    public Semester semesterWithGroupsDTOToSemester(SemesterWithGroupsDTO semesterWithGroupsDTO) {
        if ( semesterWithGroupsDTO == null ) {
            return null;
        }

        Semester semester = new Semester();

        semester.setId( semesterWithGroupsDTO.getId() );
        semester.setDescription( semesterWithGroupsDTO.getDescription() );
        semester.setYear( semesterWithGroupsDTO.getYear() );
        semester.setStartDay( semesterWithGroupsDTO.getStartDay() );
        semester.setEndDay( semesterWithGroupsDTO.getEndDay() );
        semester.setCurrentSemester( semesterWithGroupsDTO.isCurrentSemester() );
        semester.setDefaultSemester( semesterWithGroupsDTO.isDefaultSemester() );
        TreeSet<DayOfWeek> treeSet = semesterWithGroupsDTO.getDaysOfWeek();
        if ( treeSet != null ) {
            semester.setDaysOfWeek( new HashSet<DayOfWeek>( treeSet ) );
        }
        semester.setPeriods( periodDTOLinkedHashSetToPeriodSet( semesterWithGroupsDTO.getPeriods() ) );
        semester.setGroups( groupDTOLinkedListToGroupSet( semesterWithGroupsDTO.getGroups() ) );
        semester.setDisable( semesterWithGroupsDTO.isDisable() );

        return semester;
    }

    @Override
    public List<SemesterDTO> semestersToSemesterDTOs(List<Semester> semesters) {
        if ( semesters == null ) {
            return null;
        }

        List<SemesterDTO> list = new ArrayList<SemesterDTO>( semesters.size() );
        for ( Semester semester : semesters ) {
            list.add( semesterToSemesterDTO( semester ) );
        }

        return list;
    }

    @Override
    public List<SemesterWithGroupsDTO> semestersToSemesterWithGroupsDTOs(List<Semester> semesters) {
        if ( semesters == null ) {
            return null;
        }

        List<SemesterWithGroupsDTO> list = new ArrayList<SemesterWithGroupsDTO>( semesters.size() );
        for ( Semester semester : semesters ) {
            list.add( semesterToSemesterWithGroupsDTO( semester ) );
        }

        return list;
    }

    @Override
    public SemesterWithGroupsDTO semesterToSemesterWithGroupsDTO(Semester semester) {
        if ( semester == null ) {
            return null;
        }

        SemesterWithGroupsDTO semesterWithGroupsDTO = new SemesterWithGroupsDTO();

        semesterWithGroupsDTO.setId( semester.getId() );
        semesterWithGroupsDTO.setDescription( semester.getDescription() );
        semesterWithGroupsDTO.setYear( semester.getYear() );
        semesterWithGroupsDTO.setStartDay( semester.getStartDay() );
        semesterWithGroupsDTO.setEndDay( semester.getEndDay() );
        semesterWithGroupsDTO.setCurrentSemester( semester.isCurrentSemester() );
        semesterWithGroupsDTO.setDefaultSemester( semester.isDefaultSemester() );
        Set<DayOfWeek> set = semester.getDaysOfWeek();
        if ( set != null ) {
            semesterWithGroupsDTO.setDaysOfWeek( new TreeSet<DayOfWeek>( set ) );
        }
        semesterWithGroupsDTO.setPeriods( periodSetToPeriodDTOLinkedHashSet( semester.getPeriods() ) );
        semesterWithGroupsDTO.setDisable( semester.isDisable() );
        semesterWithGroupsDTO.setGroups( groupSetToGroupDTOLinkedList( semester.getGroups() ) );

        return semesterWithGroupsDTO;
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

    protected Period periodDTOToPeriod(PeriodDTO periodDTO) {
        if ( periodDTO == null ) {
            return null;
        }

        Period period = new Period();

        period.setId( periodDTO.getId() );
        period.setStartTime( periodDTO.getStartTime() );
        period.setEndTime( periodDTO.getEndTime() );
        period.setName( periodDTO.getName() );

        return period;
    }

    protected Set<Period> periodDTOLinkedHashSetToPeriodSet(LinkedHashSet<PeriodDTO> linkedHashSet) {
        if ( linkedHashSet == null ) {
            return null;
        }

        Set<Period> set = new HashSet<Period>( Math.max( (int) ( linkedHashSet.size() / .75f ) + 1, 16 ) );
        for ( PeriodDTO periodDTO : linkedHashSet ) {
            set.add( periodDTOToPeriod( periodDTO ) );
        }

        return set;
    }

    protected Set<Group> groupDTOLinkedListToGroupSet(LinkedList<GroupDTO> linkedList) {
        if ( linkedList == null ) {
            return null;
        }

        Set<Group> set = new HashSet<Group>( Math.max( (int) ( linkedList.size() / .75f ) + 1, 16 ) );
        for ( GroupDTO groupDTO : linkedList ) {
            set.add( groupMapper.groupDTOToGroup( groupDTO ) );
        }

        return set;
    }

    protected LinkedList<GroupDTO> groupSetToGroupDTOLinkedList(Set<Group> set) {
        if ( set == null ) {
            return null;
        }

        LinkedList<GroupDTO> linkedList = new LinkedList<GroupDTO>();
        for ( Group group : set ) {
            linkedList.add( groupMapper.groupToGroupDTO( group ) );
        }

        return linkedList;
    }
}
