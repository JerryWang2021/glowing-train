/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Room;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class RoomDaoDB implements RoomDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Room> getAllRooms() {
        final String sql = "SELECT * FROM room";
        return jdbc.query(sql, new RoomMapper());
    }

    @Override
    public Room getRoomById(int id) {
        try {
            final String sql = "SELECT * FROM room WHERE id = ?";
            return jdbc.queryForObject(sql, new RoomMapper(), id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    @Transactional
    public Room addRoom(Room room) {
        final String sql = "INSERT INTO room(name, description) VALUES(?,?)";
        jdbc.update(sql, 
                room.getName(),
                room.getDescription());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        room.setId(newId);
        return room;
    }

    @Override
    @Transactional
    public void updateRoom(Room room) {
        final String sql = "UPDATE room SET name = ?, description = ? WHERE id = ?";
        jdbc.update(sql,
                room.getName(),
                room.getDescription(),
                room.getId());
    }

    @Override
    @Transactional
    public void deleteRoomById(int id) {
        final String sql = "DELETE me.* FROM meeting_employee me "
                + "JOIN meeting m ON me.meetingId = m.id WHERE roomId = ?";
        jdbc.update(sql, id);
        
        final String DELETE_MEETING_BY_ROOM_ID = "DELETE FROM meeting WHERE roomId = ?";
        jdbc.update(DELETE_MEETING_BY_ROOM_ID, id);
        
        final String DELETE_ROOM = "DELETE FROM room WHERE ID = ?";
        jdbc.update(DELETE_ROOM, id);
    }
    
    public static final class RoomMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int index) throws SQLException {
            Room rm = new Room();
            rm.setId(rs.getInt("id"));
            rm.setName(rs.getString("name"));
            rm.setDescription(rs.getString("description"));
            return rm;
        }
        
    }
    
}
