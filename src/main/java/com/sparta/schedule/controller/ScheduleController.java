package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    // DB로 대체
    private final Map<Long, Schedule> scheduleList = new HashMap<>();
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // POST 요청을 받아 일정 정보를 저장
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 단건 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        Schedule schedule = scheduleList.get(id);

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

//    @GetMapping("/{date}")
//    public ScheduleResponseDto findScheduleAll(@PathVariable LocalDateTime date){
//        scheduleList.getDate
//    }

    @PatchMapping("/{id}")
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        // password 전달 후 확인 과정 필요
        //
        Schedule schedule = scheduleList.get(id);
        schedule.edit(dto);
        return new ScheduleResponseDto(schedule);
    }
}
