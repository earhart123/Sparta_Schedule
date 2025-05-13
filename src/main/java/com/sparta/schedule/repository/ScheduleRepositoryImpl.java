package com.sparta.schedule.repository;

import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{
    //private final Map<Long, Schedule> scheduleList = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        String sql = "INSERT INTO schedule (content, writer, date, password) VALUES (?, ?, ?, ?)";

        int result = jdbcTemplate.update(sql, schedule.getContent(), schedule.getWriter(), schedule.getDate(), schedule.getPassword());

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        String sql = "SELECT id, content, writer, date FROM schedule WHERE id = ?";

        RowMapper<Schedule> rowMapper = new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long scheduleId = rs.getLong("id");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String date = rs.getString("date");
                return new Schedule(scheduleId, content, writer, date);
            }
        };

        Schedule schedule = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll() {

        String sql = "SELECT id, content, writer, date FROM schedule";

        // 2. *RowMapper 익명 클래스 구현
        RowMapper<ScheduleResponseDto> rowMapper = new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, content, writer, date);
            }
        };

        // 3. 실행(익명클래스를 메서드로 전달해서 각 요소를 처리)
        List<ScheduleResponseDto> scheduleList = jdbcTemplate.query(sql, rowMapper);

        // 4. 반환
        return scheduleList;
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDate(String date) {

        String sql = "SELECT id, content, writer, date FROM schedule WHERE date LIKE  '" + date + "%'";

        RowMapper<ScheduleResponseDto> rowMapper = new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, content, writer, date);
            }
        };

        List<ScheduleResponseDto> scheduleList = jdbcTemplate.query(sql, rowMapper);

        return scheduleList;
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByWriter(String writer) {

        String sql = "SELECT id, content, writer, date FROM schedule WHERE writer = ?";

        RowMapper<ScheduleResponseDto> rowMapper = new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                String writer = rs.getString("writer");
                String date = rs.getString("date");
                return new ScheduleResponseDto(id, content, writer, date);
            }
        };

        List<ScheduleResponseDto> scheduleList = jdbcTemplate.query(sql, rowMapper, writer);

        return scheduleList;

    }

    @Override
    public int editSchedule(Schedule schedule) {
        return jdbcTemplate.update("UPDATE schedule SET content = ?, writer = ? where id = ?", schedule.getContent(), schedule.getWriter(), schedule.getId());
    }

    @Override
    public int editScheduleContent(Schedule schedule) {
        return jdbcTemplate.update("UPDATE schedule SET content = ? where id = ?", schedule.getContent(), schedule.getId());
    }

    @Override
    public int editScheduleWriter(Schedule schedule) {
        return jdbcTemplate.update("UPDATE schedule SET writer = ? where id = ?", schedule.getWriter(), schedule.getId());
    }

    @Override
    public int deleteSchedule(Long id) {
        String sql = "DELETE FROM schedule WHERE id = ?";

        int result = jdbcTemplate.update(sql, id);

        return result;
    }

    @Override
    public String getDbPassword(Long id){
        String sql = "SELECT password FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, id);
    }
}
