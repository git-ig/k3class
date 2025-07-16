package com.softserve.mapper;

import com.softserve.dto.GroupDTO;
import com.softserve.dto.GroupDTO.GroupDTOBuilder;
import com.softserve.dto.LessonForTeacherScheduleDTO;
import com.softserve.entity.Group;
import com.softserve.entity.Lesson;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class LessonForTeacherScheduleMapperImpl implements LessonForTeacherScheduleMapper {

    @Override
    public LessonForTeacherScheduleDTO lessonToLessonForTeacherScheduleDTO(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonForTeacherScheduleDTO lessonForTeacherScheduleDTO = new LessonForTeacherScheduleDTO();

        lessonForTeacherScheduleDTO.setLessonType( lesson.getLessonType() );
        lessonForTeacherScheduleDTO.setId( lesson.getId() );
        lessonForTeacherScheduleDTO.setLinkToMeeting( lesson.getLinkToMeeting() );
        lessonForTeacherScheduleDTO.setSubjectForSite( lesson.getSubjectForSite() );
        lessonForTeacherScheduleDTO.setGroup( groupToGroupDTO( lesson.getGroup() ) );

        return lessonForTeacherScheduleDTO;
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
}
