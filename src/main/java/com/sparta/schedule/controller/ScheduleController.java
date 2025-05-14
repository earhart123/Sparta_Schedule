package com.sparta.schedule.controller;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 일정 정보 저장
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(dto), HttpStatus.CREATED);
    } // POST 성공 후 id가 null로 반환됨

    /**
     * 단건 일정 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    /**
     * 전체 일정 조회
     */
    @GetMapping
    public List<ScheduleResponseDto> findScheduleAll(){
        return scheduleService.findScheduleAll();
    }

    /**
     * 작성자 또는 수정일 기준 일정 조회
     */
    @GetMapping("/find-param")
    public List<ScheduleResponseDto> findScheduleByDateOrWriter(@RequestParam(required = false, defaultValue = "") String writer,
                                                        @RequestParam(required = false, defaultValue = "") String date){

        return scheduleService.findScheduleByDateOrWriter(writer, date);
    }

    /**
     * 일정 수정
     */
    @PatchMapping("/{id}")
    public ScheduleResponseDto editSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        return scheduleService.editSchedule(id, dto);
    } // 작성자만 수정 시 반환 content가 null로 노출됨

    /**
     * 일정 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto){
        scheduleService.deleteSchedule(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
