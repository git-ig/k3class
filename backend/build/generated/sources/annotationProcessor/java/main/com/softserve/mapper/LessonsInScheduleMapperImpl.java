package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.GroupDTO.GroupDTOBuilder;
import com.softserve.dto.LessonInfoDTO;
import com.softserve.dto.LessonsInScheduleDTO;
import com.softserve.dto.SubjectDTO;
import com.softserve.dto.TeacherNameDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Lesson;
import com.softserve.entity.Semester;
import com.softserve.entity.Subject;
import com.softserve.entity.Teacher;
import com.softserve.entity.TemporarySchedule;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class LessonsInScheduleMapperImpl implements LessonsInScheduleMapper {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public LessonsInScheduleDTO lessonToLessonsInScheduleDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonsInScheduleDTO lessonsInScheduleDTO = new LessonsInScheduleDTO();

        if ( lesson.getLessonType() != null ) {
            lessonsInScheduleDTO.setLessonType( lesson.getLessonType().name() );
        }
        lessonsInScheduleDTO.setTeacher( teacherMapper.teacherToTeacherDTO( lesson.getTeacher() ) );
        lessonsInScheduleDTO.setLinkToMeeting( lesson.getLinkToMeeting() );
        lessonsInScheduleDTO.setSubjectForSite( lesson.getSubjectForSite() );

        return lessonsInScheduleDTO;
    }

    @Override
    public LessonInfoDTO lessonToLessonsInTemporaryScheduleDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonInfoDTO lessonInfoDTO = new LessonInfoDTO();

        lessonInfoDTO.setSemesterId( lessonSemesterId( lesson ) );
        lessonInfoDTO.setId( lesson.getId() );
        lessonInfoDTO.setHours( lesson.getHours() );
        lessonInfoDTO.setLinkToMeeting( lesson.getLinkToMeeting() );
        lessonInfoDTO.setSubjectForSite( lesson.getSubjectForSite() );
        lessonInfoDTO.setLessonType( lesson.getLessonType() );
        lessonInfoDTO.setSubject( subjectToSubjectDTO( lesson.getSubject() ) );
        lessonInfoDTO.setTeacher( teacherToTeacherNameDTO( lesson.getTeacher() ) );
        lessonInfoDTO.setGroup( groupToGroupDTO( lesson.getGroup() ) );
        lessonInfoDTO.setGrouped( lesson.isGrouped() );

        return lessonInfoDTO;
    }

    @Override
    public LessonInfoDTO lessonToLessonsInTemporaryScheduleDTO(TemporarySchedule temporarySchedule) {
        if ( temporarySchedule == null ) {
            return null;
        }

        LessonInfoDTO lessonInfoDTO = new LessonInfoDTO();

        lessonInfoDTO.setSemesterId( temporaryScheduleSemesterId( temporarySchedule ) );
        lessonInfoDTO.setId( temporarySchedule.getId() );
        lessonInfoDTO.setLinkToMeeting( temporarySchedule.getLinkToMeeting() );
        lessonInfoDTO.setSubjectForSite( temporarySchedule.getSubjectForSite() );
        lessonInfoDTO.setLessonType( temporarySchedule.getLessonType() );
        lessonInfoDTO.setSubject( subjectToSubjectDTO( temporarySchedule.getSubject() ) );
        lessonInfoDTO.setTeacher( teacherToTeacherNameDTO( temporarySchedule.getTeacher() ) );
        lessonInfoDTO.setGroup( groupToGroupDTO( temporarySchedule.getGroup() ) );
        lessonInfoDTO.setGrouped( temporarySchedule.isGrouped() );

        return lessonInfoDTO;
    }

    private Long lessonSemesterId(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }
        Semester semester = lesson.getSemester();
        if ( semester == null ) {
            return null;
        }
        Long id = semester.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected TeacherNameDTO teacherToTeacherNameDTO(Teacher teacher) {
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

    private Long temporaryScheduleSemesterId(TemporarySchedule temporarySchedule) {
        if ( temporarySchedule == null ) {
            return null;
        }
        Semester semester = temporarySchedule.getSemester();
        if ( semester == null ) {
            return null;
        }
        Long id = semester.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
