package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.GroupDTO.GroupDTOBuilder;
import com.softserve.dto.LessonDTO;
import com.softserve.dto.LessonForGroupsDTO;
import com.softserve.dto.LessonInfoDTO;
import com.softserve.dto.LessonWithLinkDTO;
import com.softserve.dto.PeriodDTO;
import com.softserve.dto.SemesterDTO;
import com.softserve.dto.SubjectDTO;
import com.softserve.dto.TeacherNameDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Group.GroupBuilder;
import com.softserve.entity.Lesson;
import com.softserve.entity.Period;
import com.softserve.entity.Semester;
import com.softserve.entity.Subject;
import com.softserve.entity.Teacher;
import com.softserve.entity.enums.LessonType;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class LessonInfoMapperImpl implements LessonInfoMapper {

    @Override
    public LessonInfoDTO lessonToLessonInfoDTO(Lesson lesson) {
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
    public Lesson lessonInfoDTOToLesson(LessonInfoDTO lessonInfoDTO) {
        if ( lessonInfoDTO == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setSemester( lessonInfoDTOToSemester( lessonInfoDTO ) );
        lesson.setId( lessonInfoDTO.getId() );
        lesson.setHours( lessonInfoDTO.getHours() );
        lesson.setLinkToMeeting( lessonInfoDTO.getLinkToMeeting() );
        lesson.setSubjectForSite( lessonInfoDTO.getSubjectForSite() );
        lesson.setLessonType( lessonInfoDTO.getLessonType() );
        lesson.setTeacher( teacherNameDTOToTeacher( lessonInfoDTO.getTeacher() ) );
        lesson.setSubject( subjectDTOToSubject( lessonInfoDTO.getSubject() ) );
        lesson.setGroup( groupDTOToGroup( lessonInfoDTO.getGroup() ) );
        lesson.setGrouped( lessonInfoDTO.isGrouped() );

        return lesson;
    }

    @Override
    public LessonDTO lessonToLessonDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDTO lessonDTO = new LessonDTO();

        lessonDTO.setSemesterId( lessonSemesterId( lesson ) );
        lessonDTO.setId( lesson.getId() );
        lessonDTO.setHours( lesson.getHours() );
        lessonDTO.setLinkToMeeting( lesson.getLinkToMeeting() );
        lessonDTO.setSubjectForSite( lesson.getSubjectForSite() );
        lessonDTO.setLessonType( lesson.getLessonType() );
        lessonDTO.setSubject( subjectToSubjectDTO( lesson.getSubject() ) );
        lessonDTO.setTeacher( teacherToTeacherNameDTO( lesson.getTeacher() ) );
        lessonDTO.setGroup( groupToGroupDTO( lesson.getGroup() ) );
        lessonDTO.setSemester( semesterToSemesterDTO( lesson.getSemester() ) );

        return lessonDTO;
    }

    @Override
    public Lesson lessonDTOToLesson(LessonDTO lessonDTO) {
        if ( lessonDTO == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setId( lessonDTO.getId() );
        lesson.setHours( lessonDTO.getHours() );
        lesson.setLinkToMeeting( lessonDTO.getLinkToMeeting() );
        lesson.setSubjectForSite( lessonDTO.getSubjectForSite() );
        lesson.setLessonType( lessonDTO.getLessonType() );
        lesson.setTeacher( teacherNameDTOToTeacher( lessonDTO.getTeacher() ) );
        lesson.setSubject( subjectDTOToSubject( lessonDTO.getSubject() ) );
        lesson.setGroup( groupDTOToGroup( lessonDTO.getGroup() ) );
        lesson.setSemester( semesterDTOToSemester( lessonDTO.getSemester() ) );

        return lesson;
    }

    @Override
    public List<LessonInfoDTO> lessonsToLessonInfoDTOs(List<Lesson> lessons) {
        if ( lessons == null ) {
            return null;
        }

        List<LessonInfoDTO> list = new ArrayList<LessonInfoDTO>( lessons.size() );
        for ( Lesson lesson : lessons ) {
            list.add( lessonToLessonInfoDTO( lesson ) );
        }

        return list;
    }

    @Override
    public List<LessonDTO> lessonsToLessonDTOs(List<Lesson> lessons) {
        if ( lessons == null ) {
            return null;
        }

        List<LessonDTO> list = new ArrayList<LessonDTO>( lessons.size() );
        for ( Lesson lesson : lessons ) {
            list.add( lessonToLessonDTO( lesson ) );
        }

        return list;
    }

    @Override
    public LessonInfoDTO lessonForGroupsDTOToLessonInfoDTO(LessonForGroupsDTO lessonForGroupsDTO) {
        if ( lessonForGroupsDTO == null ) {
            return null;
        }

        LessonInfoDTO lessonInfoDTO = new LessonInfoDTO();

        lessonInfoDTO.setId( lessonForGroupsDTO.getId() );
        lessonInfoDTO.setHours( lessonForGroupsDTO.getHours() );
        lessonInfoDTO.setLinkToMeeting( lessonForGroupsDTO.getLinkToMeeting() );
        lessonInfoDTO.setSubjectForSite( lessonForGroupsDTO.getSubjectForSite() );
        lessonInfoDTO.setLessonType( lessonForGroupsDTO.getLessonType() );
        lessonInfoDTO.setSubject( lessonForGroupsDTO.getSubject() );
        lessonInfoDTO.setTeacher( lessonForGroupsDTO.getTeacher() );
        lessonInfoDTO.setSemesterId( lessonForGroupsDTO.getSemesterId() );
        lessonInfoDTO.setGrouped( lessonForGroupsDTO.isGrouped() );

        return lessonInfoDTO;
    }

    @Override
    public Lesson lessonWithLinkDTOToLesson(LessonWithLinkDTO lessonWithLinkDTO) {
        if ( lessonWithLinkDTO == null ) {
            return null;
        }

        Lesson lesson = new Lesson();

        lesson.setSemester( lessonWithLinkDTOToSemester( lessonWithLinkDTO ) );
        lesson.setSubject( lessonWithLinkDTOToSubject( lessonWithLinkDTO ) );
        lesson.setTeacher( lessonWithLinkDTOToTeacher( lessonWithLinkDTO ) );
        lesson.setLinkToMeeting( lessonWithLinkDTO.getLinkToMeeting() );
        if ( lessonWithLinkDTO.getLessonType() != null ) {
            lesson.setLessonType( Enum.valueOf( LessonType.class, lessonWithLinkDTO.getLessonType() ) );
        }

        return lesson;
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

    protected Semester lessonInfoDTOToSemester(LessonInfoDTO lessonInfoDTO) {
        if ( lessonInfoDTO == null ) {
            return null;
        }

        Semester semester = new Semester();

        semester.setId( lessonInfoDTO.getSemesterId() );

        return semester;
    }

    protected Teacher teacherNameDTOToTeacher(TeacherNameDTO teacherNameDTO) {
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

    protected Subject subjectDTOToSubject(SubjectDTO subjectDTO) {
        if ( subjectDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( subjectDTO.getId() );
        subject.setName( subjectDTO.getName() );
        subject.setDisable( subjectDTO.isDisable() );

        return subject;
    }

    protected Group groupDTOToGroup(GroupDTO groupDTO) {
        if ( groupDTO == null ) {
            return null;
        }

        GroupBuilder group = Group.builder();

        group.id( groupDTO.getId() );
        group.title( groupDTO.getTitle() );
        if ( groupDTO.getDisable() != null ) {
            group.disable( groupDTO.getDisable() );
        }

        return group.build();
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

    protected Semester semesterDTOToSemester(SemesterDTO semesterDTO) {
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

    protected Semester lessonWithLinkDTOToSemester(LessonWithLinkDTO lessonWithLinkDTO) {
        if ( lessonWithLinkDTO == null ) {
            return null;
        }

        Semester semester = new Semester();

        semester.setId( lessonWithLinkDTO.getSemesterId() );

        return semester;
    }

    protected Subject lessonWithLinkDTOToSubject(LessonWithLinkDTO lessonWithLinkDTO) {
        if ( lessonWithLinkDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( lessonWithLinkDTO.getSubjectId() );

        return subject;
    }

    protected Teacher lessonWithLinkDTOToTeacher(LessonWithLinkDTO lessonWithLinkDTO) {
        if ( lessonWithLinkDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( lessonWithLinkDTO.getTeacherId() );

        return teacher;
    }
}
