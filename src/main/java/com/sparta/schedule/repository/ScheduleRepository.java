package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
    Schedule findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleAll();
}
