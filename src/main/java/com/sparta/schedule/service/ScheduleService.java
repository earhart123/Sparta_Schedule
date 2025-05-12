package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    public ScheduleResponseDto findScheduleById(Long id);
    public List<ScheduleResponseDto> findScheduleAll();
}
