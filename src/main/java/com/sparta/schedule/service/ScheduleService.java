package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    ScheduleResponseDto findScheduleById(Long id);
    List<ScheduleResponseDto> findScheduleAll();
    List<ScheduleResponseDto> findScheduleByDateOrWriter(String writer, String date);
    ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto dto);
    void deleteSchedule(Long id, ScheduleRequestDto dto);
}
