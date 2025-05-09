package com.sparta.schedule.entity;

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
}
