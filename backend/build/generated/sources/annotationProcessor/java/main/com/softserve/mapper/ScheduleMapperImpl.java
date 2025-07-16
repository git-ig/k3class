package com.softserve.mapper;

import com.softserve.dto.PeriodDTO;
import com.softserve.dto.ScheduleDTO;
import com.softserve.dto.ScheduleForCopyDTO;
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
public class ScheduleMapperImpl implements ScheduleMapper {

    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private LessonInfoMapper lessonInfoMapper;

    @Override
    public ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.setDayOfWeek( schedule.getDayOfWeek() );
        scheduleDTO.setEvenOdd( schedule.getEvenOdd() );
        scheduleDTO.setLesson( lessonInfoMapper.lessonToLessonInfoDTO( schedule.getLesson() ) );
        scheduleDTO.setPeriod( periodToPeriodDTO( schedule.getPeriod() ) );
        scheduleDTO.setRoom( roomMapper.convertToDto( schedule.getRoom() ) );

        return scheduleDTO;
    }

    @Override
    public Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Schedule schedule = new Schedule();

        schedule.setId( scheduleDTO.getId() );
        schedule.setDayOfWeek( scheduleDTO.getDayOfWeek() );
        schedule.setEvenOdd( scheduleDTO.getEvenOdd() );
        schedule.setRoom( roomMapper.convertToEntity( scheduleDTO.getRoom() ) );
        schedule.setPeriod( periodDTOToPeriod( scheduleDTO.getPeriod() ) );
        schedule.setLesson( lessonInfoMapper.lessonInfoDTOToLesson( scheduleDTO.getLesson() ) );

        return schedule;
    }

    @Override
    public ScheduleForCopyDTO scheduleToScheduleForCopyDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleForCopyDTO scheduleForCopyDTO = new ScheduleForCopyDTO();

        scheduleForCopyDTO.setId( schedule.getId() );
        scheduleForCopyDTO.setDayOfWeek( schedule.getDayOfWeek() );
        scheduleForCopyDTO.setEvenOdd( schedule.getEvenOdd() );
        scheduleForCopyDTO.setLesson( lessonInfoMapper.lessonToLessonDTO( schedule.getLesson() ) );
        scheduleForCopyDTO.setPeriod( periodToPeriodDTO( schedule.getPeriod() ) );
        scheduleForCopyDTO.setRoom( roomMapper.convertToDto( schedule.getRoom() ) );

        return scheduleForCopyDTO;
    }

    @Override
    public List<ScheduleDTO> scheduleToScheduleDTOs(List<Schedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( scheduleToScheduleDTO( schedule ) );
        }

        return list;
    }

    @Override
    public List<ScheduleForCopyDTO> scheduleToScheduleForCopyDTOs(List<Schedule> schedules) {
        if ( schedules == null ) {
            return null;
        }

        List<ScheduleForCopyDTO> list = new ArrayList<ScheduleForCopyDTO>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( scheduleToScheduleForCopyDTO( schedule ) );
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
