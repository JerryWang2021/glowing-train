/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.dao.EmployeeDaoDB.EmployeeMapper;
import com.sg.jdbctcomplexexample.dao.RoomDaoDB.RoomMapper;
import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jerry
 */
@Repository
public class MeetingDaoDB implements MeetingDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Meeting> getAllMeetings() {
        final String sql = "SELECT * FROM meeting";
        List<Meeting> meetings = jdbc.query(sql, new MeetingMapper());
        
        addRoomAndEmployeesToMeetings(meetings);
        
        return meetings;
    }
    
    private void addRoomAndEmployeesToMeetings(List<Meeting> meetings){
        for (Meeting meeting : meetings) {
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
        }
    }

    @Override
    public Meeting getMeetingByid(int id) {
        try {
            final String sql = "SELECT * FROM meeting WHERE id = ?";
            Meeting meeting = jdbc.queryForObject(sql, new MeetingMapper(), id);
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
            return meeting;
        }catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Room getRoomForMeeting(Meeting meeting){
        final String sql = "SELECT R.* FROM room r "
                + "Join meeting m ON r.id = m.roomId WHERE m.id = ?";
        return jdbc.queryForObject(sql, new RoomMapper(), meeting.getId());
    }
    
    private List<Employee> getEmployeesForMeeting(Meeting meeting){
        final String sql = "SELECT e.* FROM employee e "
                + "JOIN meeting_employee me ON e.id = me.employeeId WHERE me.meetingId = ?";
        return jdbc.query(sql, new EmployeeMapper(), meeting.getId());
    }

    @Override
    @Transactional
    public Meeting addMeeting(Meeting meeting) {
        final String sql = "INSERT INTO meeting(name, time, roomId) VALUES(?,?,?)";
        jdbc.update(sql, 
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        meeting.setId(newId);
        
        insertMeetingEmployee(meeting);
        
        return meeting;
    }
    
    private void insertMeetingEmployee(Meeting meeting){
        final String sql = "INSERT INTO meeting_employee"
                + "(meetingId, employeeId) VALUES(?,?)";
        for(Employee employee : meeting.getAttendees()){
            jdbc.update(sql, meeting.getId(),employee.getId());
        }
    }

    @Override
    @Transactional
    public void updateMeeting(Meeting meeting) {
        final String UPDATE_MEETING = "UPDATE meeting "
                + "SET name =? time = ? rooId = ? WHERE id = ?";
        jdbc.update(UPDATE_MEETING, 
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId(),
                meeting.getId());
        
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, meeting.getId());
        insertMeetingEmployee(meeting);
    }

    @Override
    @Transactional
    public void deleteMeetingById(int id) {
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);
        
        final String DELETE_MEETING = "DELETE FROM meeting WHERE id = ?";
        jdbc.update(DELETE_MEETING, id);
    }

    @Override
    public List<Meeting> getMeetingsForRoom(Room room) {
        final String sql = "SELECT meeting WHERE roomId = ?";
        List<Meeting> meetings =
        jdbc.query(sql, new MeetingMapper(), room.getId());
        
        addRoomAndEmployeesToMeetings(meetings);
        
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsForEmployee(Employee employee) {
        final String sql = "SELECT * FROM meeting m "
                + "JOIN meeting_employee me ON m.id = me.meetingId WHERE me.employeeId = ?";
        List<Meeting> meetings = jdbc.query(sql, new MeetingMapper(), employee.getId());
        
        addRoomAndEmployeesToMeetings(meetings);
        
        return meetings;
    }
    
    public static class MeetingMapper implements RowMapper<Meeting> {

        @Override
        public Meeting mapRow(ResultSet rs, int index) throws SQLException {
           Meeting meet = new Meeting();
           meet.setId(rs.getInt("id"));
           meet.setName(rs.getString("name"));
           meet.setTime(rs.getTimestamp("time").toLocalDateTime());
           return meet;
        }
        
    }
}
