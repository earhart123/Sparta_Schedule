package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleAll();
    List<ScheduleResponseDto> findScheduleByDate(String date);
    List<ScheduleResponseDto> findScheduleByWriter(String writer);
    int editSchedule(Schedule schedule);
    int editScheduleContent(Schedule schedule);
    int editScheduleWriter(Schedule schedule);
    int deleteSchedule(Long id);
    String getDbPassword(Long id);
}
