package com.softserve.mapper;

import com.softserve.dto.DepartmentDTO;
import com.softserve.dto.DepartmentDTO.DepartmentDTOBuilder;
import com.softserve.dto.TeacherDTO;
import com.softserve.dto.TeacherForUpdateDTO;
import com.softserve.dto.TeacherImportDTO;
import com.softserve.dto.TeacherWithUserDTO;
import com.softserve.dto.UserDataDTO;
import com.softserve.entity.Department;
import com.softserve.entity.Teacher;
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
public class TeacherMapperImpl extends TeacherMapper {

    @Override
    public TeacherDTO teacherToTeacherDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setDepartmentDTO( departmentToDepartmentDTO( teacher.getDepartment() ) );
        teacherDTO.setEmail( userIdToEmail( teacher.getUserId() ) );
        teacherDTO.setId( teacher.getId() );
        teacherDTO.setDisable( teacher.isDisable() );
        teacherDTO.setName( teacher.getName() );
        teacherDTO.setSurname( teacher.getSurname() );
        teacherDTO.setPatronymic( teacher.getPatronymic() );
        teacherDTO.setPosition( teacher.getPosition() );

        return teacherDTO;
    }

    @Override
    public TeacherImportDTO teacherToTeacherImportDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherImportDTO teacherImportDTO = new TeacherImportDTO();

        teacherImportDTO.setDepartmentDTO( departmentToDepartmentDTO( teacher.getDepartment() ) );
        teacherImportDTO.setEmail( userIdToEmail( teacher.getUserId() ) );
        teacherImportDTO.setId( teacher.getId() );
        teacherImportDTO.setName( teacher.getName() );
        teacherImportDTO.setSurname( teacher.getSurname() );
        teacherImportDTO.setPatronymic( teacher.getPatronymic() );
        teacherImportDTO.setPosition( teacher.getPosition() );

        return teacherImportDTO;
    }

    @Override
    public Teacher teacherForUpdateDTOToTeacher(TeacherForUpdateDTO teacherForUpdateDTO) {
        if ( teacherForUpdateDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setDepartment( departmentDTOToDepartment( teacherForUpdateDTO.getDepartmentDTO() ) );
        teacher.setUserId( emailToUser( teacherForUpdateDTO.getEmail() ) );
        teacher.setId( teacherForUpdateDTO.getId() );
        teacher.setName( teacherForUpdateDTO.getName() );
        teacher.setSurname( teacherForUpdateDTO.getSurname() );
        teacher.setPatronymic( teacherForUpdateDTO.getPatronymic() );
        teacher.setPosition( teacherForUpdateDTO.getPosition() );
        teacher.setDisable( teacherForUpdateDTO.isDisable() );

        return teacher;
    }

    @Override
    public TeacherForUpdateDTO teacherToTeacherForUpdateDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherForUpdateDTO teacherForUpdateDTO = new TeacherForUpdateDTO();

        teacherForUpdateDTO.setDepartmentDTO( departmentToDepartmentDTO( teacher.getDepartment() ) );
        teacherForUpdateDTO.setEmail( userIdToEmail( teacher.getUserId() ) );
        teacherForUpdateDTO.setId( teacher.getId() );
        teacherForUpdateDTO.setName( teacher.getName() );
        teacherForUpdateDTO.setSurname( teacher.getSurname() );
        teacherForUpdateDTO.setPatronymic( teacher.getPatronymic() );
        teacherForUpdateDTO.setPosition( teacher.getPosition() );
        teacherForUpdateDTO.setDisable( teacher.isDisable() );

        return teacherForUpdateDTO;
    }

    @Override
    public Teacher teacherDTOToTeacher(TeacherDTO teacherDTO) {
        if ( teacherDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setDepartment( departmentDTOToDepartment( teacherDTO.getDepartmentDTO() ) );
        teacher.setUserId( emailToUser( teacherDTO.getEmail() ) );
        teacher.setId( teacherDTO.getId() );
        teacher.setName( teacherDTO.getName() );
        teacher.setSurname( teacherDTO.getSurname() );
        teacher.setPatronymic( teacherDTO.getPatronymic() );
        teacher.setPosition( teacherDTO.getPosition() );
        if ( teacherDTO.getDisable() != null ) {
            teacher.setDisable( teacherDTO.getDisable() );
        }

        return teacher;
    }

    @Override
    public Teacher teacherImportDTOToTeacher(TeacherImportDTO teacherImportDTO) {
        if ( teacherImportDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setDepartment( departmentDTOToDepartment( teacherImportDTO.getDepartmentDTO() ) );
        teacher.setUserId( emailToUser( teacherImportDTO.getEmail() ) );
        teacher.setId( teacherImportDTO.getId() );
        teacher.setName( teacherImportDTO.getName() );
        teacher.setSurname( teacherImportDTO.getSurname() );
        teacher.setPatronymic( teacherImportDTO.getPatronymic() );
        teacher.setPosition( teacherImportDTO.getPosition() );

        return teacher;
    }

    @Override
    public TeacherWithUserDTO toTeacherWithUserDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherWithUserDTO teacherWithUserDTO = new TeacherWithUserDTO();

        teacherWithUserDTO.setId( teacher.getId() );
        teacherWithUserDTO.setName( teacher.getName() );
        teacherWithUserDTO.setSurname( teacher.getSurname() );
        teacherWithUserDTO.setPatronymic( teacher.getPatronymic() );
        teacherWithUserDTO.setPosition( teacher.getPosition() );
        teacherWithUserDTO.setUserId( teacher.getUserId() );

        return teacherWithUserDTO;
    }

    @Override
    public UserDataDTO teacherToUserDataDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        UserDataDTO userDataDTO = new UserDataDTO();

        userDataDTO.setTeacherId( teacher.getId() );
        userDataDTO.setTeacherDepartmentDTO( departmentToDepartmentDTO( teacher.getDepartment() ) );
        userDataDTO.setTeacherName( teacher.getName() );
        userDataDTO.setTeacherPosition( teacher.getPosition() );
        userDataDTO.setTeacherPatronymic( teacher.getPatronymic() );
        userDataDTO.setTeacherSurname( teacher.getSurname() );

        return userDataDTO;
    }

    @Override
    public List<TeacherDTO> teachersToTeacherDTOs(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDTO> list = new ArrayList<TeacherDTO>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( teacherToTeacherDTO( teacher ) );
        }

        return list;
    }

    protected DepartmentDTO departmentToDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTOBuilder departmentDTO = DepartmentDTO.builder();

        departmentDTO.id( department.getId() );
        departmentDTO.name( department.getName() );
        departmentDTO.disable( department.isDisable() );

        return departmentDTO.build();
    }

    protected Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDTO.getId() );
        department.setName( departmentDTO.getName() );
        department.setDisable( departmentDTO.isDisable() );

        return department;
    }
}
