package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.GroupDTO.GroupDTOBuilder;
import com.softserve.dto.GroupDTOInRoomSchedule;
import com.softserve.dto.GroupForUpdateDTO;
import com.softserve.dto.GroupForUpdateDTO.GroupForUpdateDTOBuilder;
import com.softserve.dto.GroupWithStudentsDTO;
import com.softserve.dto.GroupWithStudentsDTO.GroupWithStudentsDTOBuilder;
import com.softserve.dto.StudentWithoutGroupDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Group.GroupBuilder;
import com.softserve.entity.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public GroupDTO groupToGroupDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTOBuilder groupDTO = GroupDTO.builder();

        if ( group.getId() != null ) {
            groupDTO.id( group.getId() );
        }
        groupDTO.disable( group.isDisable() );
        if ( group.getTitle() != null ) {
            groupDTO.title( group.getTitle() );
        }

        return groupDTO.build();
    }

    @Override
    public Group groupDTOToGroup(GroupDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        GroupBuilder group = Group.builder();

        if ( groupDTO.getId() != null ) {
            group.id( groupDTO.getId() );
        }
        if ( groupDTO.getTitle() != null ) {
            group.title( groupDTO.getTitle() );
        }
        if ( groupDTO.getDisable() != null ) {
            group.disable( groupDTO.getDisable() );
        }

        return group.build();
    }

    @Override
    public GroupForUpdateDTO groupToGroupForUpdateDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupForUpdateDTOBuilder groupForUpdateDTO = GroupForUpdateDTO.builder();

        if ( group.getId() != null ) {
            groupForUpdateDTO.id( group.getId() );
        }
        if ( group.getTitle() != null ) {
            groupForUpdateDTO.title( group.getTitle() );
        }
        groupForUpdateDTO.disable( group.isDisable() );

        return groupForUpdateDTO.build();
    }

    @Override
    public GroupWithStudentsDTO groupToGroupWithStudentsDTO(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupWithStudentsDTOBuilder groupWithStudentsDTO = GroupWithStudentsDTO.builder();

        if ( group.getId() != null ) {
            groupWithStudentsDTO.id( group.getId() );
        }
        if ( group.getTitle() != null ) {
            groupWithStudentsDTO.title( group.getTitle() );
        }
        List<StudentWithoutGroupDTO> list = studentListToStudentWithoutGroupDTOList( group.getStudents() );
        if ( list != null ) {
            groupWithStudentsDTO.students( list );
        }

        return groupWithStudentsDTO.build();
    }

    @Override
    public Group groupForUpdateDTOToGroup(GroupForUpdateDTO groupForUpdateDTO) {
        if ( groupForUpdateDTO == null ) {
            return null;
        }

        GroupBuilder group = Group.builder();

        if ( groupForUpdateDTO.getId() != null ) {
            group.id( groupForUpdateDTO.getId() );
        }
        if ( groupForUpdateDTO.getTitle() != null ) {
            group.title( groupForUpdateDTO.getTitle() );
        }
        group.disable( groupForUpdateDTO.isDisable() );

        return group.build();
    }

    @Override
    public List<GroupDTO> groupsToGroupDTOs(List<Group> groups) {
        if ( groups == null ) {
            return null;
        }

        List<GroupDTO> list = new ArrayList<GroupDTO>( groups.size() );
        for ( Group group : groups ) {
            list.add( groupToGroupDTO( group ) );
        }

        return list;
    }

    @Override
    public GroupDTOInRoomSchedule groupToGroupDTOInRoomSchedule(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDTOInRoomSchedule groupDTOInRoomSchedule = new GroupDTOInRoomSchedule();

        if ( group.getTitle() != null ) {
            groupDTOInRoomSchedule.setGroupName( group.getTitle() );
        }
        if ( group.getId() != null ) {
            groupDTOInRoomSchedule.setGroupId( group.getId() );
        }

        return groupDTOInRoomSchedule;
    }

    @Override
    public List<GroupDTOInRoomSchedule> toGroupDTOInRoomSchedule(List<Group> group) {
        if ( group == null ) {
            return null;
        }

        List<GroupDTOInRoomSchedule> list = new ArrayList<GroupDTOInRoomSchedule>( group.size() );
        for ( Group group1 : group ) {
            list.add( groupToGroupDTOInRoomSchedule( group1 ) );
        }

        return list;
    }

    @Override
    public List<GroupDTO> groupsToGroupDTOs(Collection<Group> groups) {
        if ( groups == null ) {
            return null;
        }

        List<GroupDTO> list = new ArrayList<GroupDTO>( groups.size() );
        for ( Group group : groups ) {
            list.add( groupToGroupDTO( group ) );
        }

        return list;
    }

    protected List<StudentWithoutGroupDTO> studentListToStudentWithoutGroupDTOList(List<Student> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentWithoutGroupDTO> list1 = new ArrayList<StudentWithoutGroupDTO>( list.size() );
        for ( Student student : list ) {
            list1.add( studentMapper.studentToStudentWithoutGroupDTO( student ) );
        }

        return list1;
    }
}
