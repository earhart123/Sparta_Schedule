package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleAll();
    List<ScheduleResponseDto> findScheduleByDate(String date);
    List<ScheduleResponseDto> findScheduleByWriter(String writer);
}
