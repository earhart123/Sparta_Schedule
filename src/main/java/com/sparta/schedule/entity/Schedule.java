package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    @Setter
    private Long id;
    @Setter
    private String content;
    @Setter
    private String writer;
    @Setter
    private String date;
    private String password;

    public Schedule(String content, String writer, String password){
        this.content = content;
        this.writer = writer;
        this.date = LocalDateTime.now().toString();
        this.password = password;
    }

    public Schedule(Long scheduleId, String content, String writer, String date) {
        this.id = scheduleId;
        this.content = content;
        this.writer = writer;
        this.date = date;
    }

    public String getReportingDate(){
        return date;
    }

    public void edit(ScheduleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.date = LocalDateTime.now().toString();
    }

    public void editContent(ScheduleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.date = LocalDateTime.now().toString();
    }

    public void editWriter(ScheduleRequestDto requestDto){
        this.writer = writer;
        this.date = LocalDateTime.now().toString();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
