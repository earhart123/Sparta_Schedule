package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getContent(), dto.getWriter(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        // 유효하지 않는 id 값이 입력될 경우 예외 처리
        if(schedule == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return schedule;
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll() {
        return scheduleRepository.findScheduleAll();
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDateOrWriter(String writer, String date) {
        if(writer.equals("null") && date.equals("null")) {
            // 모든 목록 반환 findScheduleAll
            return scheduleRepository.findScheduleAll();
        }
        else if(writer.equals("null")){
            // date 동일한 일정 반환
            return scheduleRepository.findScheduleByDate(date);
        }
        else if(date.equals("null")){
            // writer 동일한 일정 반환
            return scheduleRepository.findScheduleByWriter(writer);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByWriter(String writer) {
        if(scheduleRepository.findScheduleByDate(writer)!=null) {
            return scheduleRepository.findScheduleByWriter(writer);
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule;
        ScheduleResponseDto scheduleResponseDto;
        if (!scheduleRepository.getDbPassword(id).equals(schedule.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
        // 할일, 작성자 모두 수정
        if(dto.getContent()!=null && dto.getWriter()!=null){
            schedule = new Schedule(dto.getContent(), dto.getWriter(), dto.getPassword());
            scheduleRepository.editSchedule(schedule);
            return new ScheduleResponseDto(scheduleRepository.findScheduleById(schedule.getId()).get());
        }
        // 할일만 수정
        else if(dto.getContent()!=null && dto.getWriter()==null){
            schedule = new Schedule(dto.getContent(), dto.getPassword());
            scheduleRepository.editScheduleContent(schedule);
        }
        // 작성자만 수정
        else if(dto.getContent()==null && dto.getWriter()!=null){
            schedule = new Schedule(dto.getContent(), dto.getPassword());
            scheduleRepository.editScheduleWriter(schedule);
        }
        return null;
    }


    @Override
    public void deleteSchedule(Long id) {
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        if(schedule == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        scheduleRepository.deleteSchedule(id);
    }

}
