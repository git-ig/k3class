package com.softserve.mapper;

import com.softserve.dto.TeacherNameDTO;
import com.softserve.entity.Teacher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class TeacherNameMapperImpl implements TeacherNameMapper {

    @Override
    public TeacherNameDTO teacherDTOToTeacher(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherNameDTO teacherNameDTO = new TeacherNameDTO();

        teacherNameDTO.setId( teacher.getId() );
        teacherNameDTO.setName( teacher.getName() );
        teacherNameDTO.setSurname( teacher.getSurname() );
        teacherNameDTO.setPatronymic( teacher.getPatronymic() );

        return teacherNameDTO;
    }

    @Override
    public Teacher teacherToTeacherDTO(TeacherNameDTO teacherNameDTO) {
        if ( teacherNameDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( teacherNameDTO.getId() );
        teacher.setName( teacherNameDTO.getName() );
        teacher.setSurname( teacherNameDTO.getSurname() );
        teacher.setPatronymic( teacherNameDTO.getPatronymic() );

        return teacher;
    }
}
