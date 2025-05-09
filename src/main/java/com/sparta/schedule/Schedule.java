package com.sparta.schedule;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class Schedule {
    private String content;
    private String writer;
    private LocalDateTime date;
    private String password;
}
