package com.sparta.schedule.entity;

import com.sparta.schedule.dto.ScheduleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String content;
    private String writer;
    private LocalDateTime date;
    private String password;

    public void edit(ScheduleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.date = LocalDateTime.now();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
