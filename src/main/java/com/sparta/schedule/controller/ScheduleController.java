package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    // DB로 대체
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto dto) {

        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        LocalDateTime localDateTime = LocalDateTime.now();
        Schedule schedule = new Schedule(scheduleId, dto.getContent(), dto.getWriter(), localDateTime, dto.getPassword());

        scheduleList.put(scheduleId, schedule);

        return new ScheduleResponseDto(schedule);
    }

//    @GetMapping("/scheduleId")
//    public findAllScheduleList
}
