package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;

public interface ScheduleRepository {
    Schedule saveSchedule(Schedule schedule);
}
