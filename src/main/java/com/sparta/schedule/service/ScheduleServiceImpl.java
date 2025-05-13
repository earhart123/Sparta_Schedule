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

    /**
     * 일정 정보 저장
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {

        Schedule schedule = new Schedule(dto.getContent(), dto.getWriter(), dto.getPassword());

        return scheduleRepository.saveSchedule(schedule);
    }

    /**
     * id로 일정 조회
     */
    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        // 유효하지 않는 id 값이 입력될 경우 예외 처리
        if(schedule == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return schedule;
    }

    /**
     * 전체 일정 조회
     */
    @Override
    public List<ScheduleResponseDto> findScheduleAll() {
        return scheduleRepository.findScheduleAll();
    }

    /**
     * 전체 일정 조회, 수정일 기준 일정 조회, 작성자 기준 일정 조회
     */
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

    /**
     * id로 일정(할일, 작성자) 수정
     */
    @Override
    public ScheduleResponseDto editSchedule(Long id, ScheduleRequestDto dto) {
        int statusCheck = 0;
        Schedule schedule = new Schedule(id, dto.getContent(), dto.getWriter(), dto.getPassword());

        // 입력받은 비밀번호가 동일한지 확인
        if (!scheduleRepository.getDbPassword(id).equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }
        // 할일, 작성자 모두 수정
        if(dto.getContent()!=null && dto.getWriter()!=null){
            statusCheck = scheduleRepository.editSchedule(schedule);
        }
        // 할일만 수정
        else if(dto.getContent()!=null && dto.getWriter()==null){
            statusCheck = scheduleRepository.editScheduleContent(schedule);
        }
        // 작성자만 수정
        else if(dto.getContent()==null && dto.getWriter()!=null){
            statusCheck = scheduleRepository.editScheduleWriter(schedule);
        }
        if(statusCheck!=1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        return new ScheduleResponseDto(schedule);
    }

    /**
     * 일정 삭제
     */
    @Override
    public void deleteSchedule(Long id, ScheduleRequestDto dto) {
        ScheduleResponseDto schedule = scheduleRepository.findScheduleById(id);

        // 입력받은 비밀번호가 동일한지 확인
        if (!scheduleRepository.getDbPassword(id).equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "비밀번호가 일치하지 않습니다.");
        }

        if(schedule == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }else if(scheduleRepository.deleteSchedule(id)!=1)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
    }

}
