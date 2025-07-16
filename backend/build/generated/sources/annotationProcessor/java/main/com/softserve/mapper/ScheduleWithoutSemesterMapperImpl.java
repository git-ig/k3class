package com.softserve.mapper;

import com.softserve.dto.PeriodDTO;
import com.softserve.dto.ScheduleWithoutSemesterDTO;
import com.softserve.entity.Period;
import com.softserve.entity.Schedule;
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
public class ScheduleWithoutSemesterMapperImpl implements ScheduleWithoutSemesterMapper {

    @Autowired
    private LessonInfoMapper lessonInfoMapper;
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public ScheduleWithoutSemesterDTO scheduleToScheduleWithoutSemesterDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleWithoutSemesterDTO scheduleWithoutSemesterDTO = new ScheduleWithoutSemesterDTO();

        scheduleWithoutSemesterDTO.setId( schedule.getId() );
        scheduleWithoutSemesterDTO.setDayOfWeek( schedule.getDayOfWeek() );
        scheduleWithoutSemesterDTO.setEvenOdd( schedule.getEvenOdd() );
        scheduleWithoutSemesterDTO.setLesson( lessonInfoMapper.lessonToLessonInfoDTO( schedule.getLesson() ) );
        scheduleWithoutSemesterDTO.setPeriod( periodToPeriodDTO( schedule.getPeriod() ) );
        scheduleWithoutSemesterDTO.setRoom( roomMapper.convertToDto( schedule.getRoom() ) );

        return scheduleWithoutSemesterDTO;
    }

    @Override
    public Schedule scheduleWithoutSemesterDTOToSchedule(ScheduleWithoutSemesterDTO scheduleWithoutSemesterDTO) {
        if ( scheduleWithoutSemesterDTO == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( scheduleWithoutSemesterDTO.getId() );
        schedule.setDayOfWeek( scheduleWithoutSemesterDTO.getDayOfWeek() );
        schedule.setEvenOdd( scheduleWithoutSemesterDTO.getEvenOdd() );
        schedule.setRoom( roomMapper.convertToEntity( scheduleWithoutSemesterDTO.getRoom() ) );
        schedule.setPeriod( periodDTOToPeriod( scheduleWithoutSemesterDTO.getPeriod() ) );
        schedule.setLesson( lessonInfoMapper.lessonInfoDTOToLesson( scheduleWithoutSemesterDTO.getLesson() ) );

        return schedule;
    }

    @Override
    public List<ScheduleWithoutSemesterDTO> scheduleToScheduleWithoutSemesterDTOs(List<Schedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<ScheduleWithoutSemesterDTO> list = new ArrayList<ScheduleWithoutSemesterDTO>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( scheduleToScheduleWithoutSemesterDTO( schedule ) );
        }

        return list;
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
}
