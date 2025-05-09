package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id;
    private String content;
    private String writer;
    private LocalDateTime date;
    private String password;

    public ScheduleResponseDto(Schedule schedule){
       this.id = schedule.getId();
       this.content = schedule.getContent();
       this.writer = schedule.getWriter();
       this.date = schedule.getDate();
       this.password = schedule.getPassword();
    }
}
