package com.softserve.mapper;

import com.softserve.dto.StudentDTO;
import com.softserve.dto.StudentDTO.StudentDTOBuilder;
import com.softserve.dto.StudentImportDTO;
import com.softserve.dto.StudentWithoutGroupDTO;
import com.softserve.dto.StudentWithoutGroupDTO.StudentWithoutGroupDTOBuilder;
import com.softserve.entity.Student;
import java.util.ArrayList;
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
public class StudentMapperImpl extends StudentMapper {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public StudentDTO studentToStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTOBuilder studentDTO = StudentDTO.builder();

        studentDTO.email( userToEmail( student.getUser() ) );
        studentDTO.id( student.getId() );
        studentDTO.name( student.getName() );
        studentDTO.surname( student.getSurname() );
        studentDTO.patronymic( student.getPatronymic() );
        studentDTO.group( groupMapper.groupToGroupDTO( student.getGroup() ) );

        return studentDTO.build();
    }

    @Override
    public StudentImportDTO studentToStudentImportDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentImportDTO studentImportDTO = new StudentImportDTO();

        studentImportDTO.setEmail( userToEmail( student.getUser() ) );
        studentImportDTO.setGroupDTO( groupMapper.groupToGroupDTO( student.getGroup() ) );
        studentImportDTO.setId( student.getId() );
        studentImportDTO.setName( student.getName() );
        studentImportDTO.setSurname( student.getSurname() );
        studentImportDTO.setPatronymic( student.getPatronymic() );

        return studentImportDTO;
    }

    @Override
    public Student studentImportDTOToStudent(StudentImportDTO studentImportDTO) {
        if ( studentImportDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setUser( emailToUser( studentImportDTO.getEmail() ) );
        student.setGroup( groupMapper.groupDTOToGroup( studentImportDTO.getGroupDTO() ) );
        student.setId( studentImportDTO.getId() );
        student.setName( studentImportDTO.getName() );
        student.setSurname( studentImportDTO.getSurname() );
        student.setPatronymic( studentImportDTO.getPatronymic() );

        return student;
    }

    @Override
    public Student studentDTOToStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setUser( emailToUser( studentDTO.getEmail() ) );
        student.setId( studentDTO.getId() );
        student.setName( studentDTO.getName() );
        student.setSurname( studentDTO.getSurname() );
        student.setPatronymic( studentDTO.getPatronymic() );
        student.setGroup( groupMapper.groupDTOToGroup( studentDTO.getGroup() ) );

        return student;
    }

    @Override
    public StudentWithoutGroupDTO studentToStudentWithoutGroupDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentWithoutGroupDTOBuilder studentWithoutGroupDTO = StudentWithoutGroupDTO.builder();

        studentWithoutGroupDTO.email( userToEmail( student.getUser() ) );
        studentWithoutGroupDTO.id( student.getId() );
        studentWithoutGroupDTO.name( student.getName() );
        studentWithoutGroupDTO.surname( student.getSurname() );
        studentWithoutGroupDTO.patronymic( student.getPatronymic() );

        return studentWithoutGroupDTO.build();
    }

    @Override
    public Student studentWithoutGroupDTOToStudent(StudentWithoutGroupDTO studentWithoutGroupDTO) {
        if ( studentWithoutGroupDTO == null ) {
            return null;
        }

        Student student = new Student();

        student.setUser( emailToUser( studentWithoutGroupDTO.getEmail() ) );
        student.setId( studentWithoutGroupDTO.getId() );
        student.setName( studentWithoutGroupDTO.getName() );
        student.setSurname( studentWithoutGroupDTO.getSurname() );
        student.setPatronymic( studentWithoutGroupDTO.getPatronymic() );

        return student;
    }

    @Override
    public List<StudentDTO> convertToDTOList(List<Student> studentList) {
        if ( studentList == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( studentList.size() );
        for ( Student student : studentList ) {
            list.add( studentToStudentDTO( student ) );
        }

        return list;
    }
}
