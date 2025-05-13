package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String content;
    private String writer;
    private String date;
    //private String password;

    public ScheduleResponseDto(Schedule schedule){
       this.id = schedule.getId();
       this.content = schedule.getContent();
       this.writer = schedule.getWriter();
       this.date = schedule.getDate();
    }

}
