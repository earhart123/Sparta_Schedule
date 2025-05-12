package com.sparta.schedule.repository;

import com.sparta.schedule.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        Long scheduleId = scheduleList.isEmpty() ? 1 : Collections.max(scheduleList.keySet()) + 1;
        schedule.setId(scheduleId);
        LocalDateTime localDateTime = LocalDateTime.now();
        schedule.setDate(localDateTime);

        scheduleList.put(scheduleId, schedule);

        return schedule;
    }

    @Override
    public Schedule findScheduleById(Long id) {
        return scheduleList.get(id);
    }
}
