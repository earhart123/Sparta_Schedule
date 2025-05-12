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
    private LocalDateTime date;
    private final String password;

    public Schedule(String content, String writer, String password){
        this.content = content;
        this.writer = writer;
        this.password = password;
    }

    public String getReportingDate(){
        return date.toLocalDate().toString();
    }

    public void edit(ScheduleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
        this.date = LocalDateTime.now();
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}
