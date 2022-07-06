/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Superpower;
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
public class SuperpowerDaoDB implements SuperpowerDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Superpower getSuperpowerById(int id) {
        try {
            final String SELECT_SUPERPOWER_BY_ID = "SELECT * FROM superpower WHERE id = ?";
            return jdbc.queryForObject(SELECT_SUPERPOWER_BY_ID, new SuperpowerMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        final String SELECT_ALL_SUPERPOWERS = "SELECT * FROM superpower";
        return jdbc.query(SELECT_ALL_SUPERPOWERS, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower addSuperpower(Superpower superpower) {
        final String INSERT_SUPERPOWER = "INSERT INTO superpower(name, description) VALUES(?,?)";
        jdbc.update(INSERT_SUPERPOWER, 
                superpower.getName(),
                superpower.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setId(newId);
        return superpower;
    }

    @Override
    @Transactional
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpower SET name = ?, "
                + "description = ? WHERE id = ?";
        jdbc.update(UPDATE_SUPERPOWER, 
                superpower.getName(),
                superpower.getDescription(),
                superpower.getId());
    }

    @Override
    @Transactional
    public void deleteSuperpowerById(int id) {
        final String DELETE_HERO_ORGANIZATION_BY_SUPERPOWER = "DELETE ho.* FROM hero_organization ho "
                + "JOIN hero h ON ho.heroId = h.id WHERE h.superpowerId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION_BY_SUPERPOWER, id);
        
        final String DELETE_SIGHTING_BY_SUPERPOWER = "DELETE s.* FROM sighting s "
                + "JOIN hero h ON s.heroId = h.id  WHERE h.superpowerId = ?";
        jdbc.update(DELETE_SIGHTING_BY_SUPERPOWER, id);
        
        final String DELETE_HERO_BY_SUPERPOWER = "DELETE h.*FROM hero h WHERE h.superpowerId = ?";
        jdbc.update(DELETE_HERO_BY_SUPERPOWER, id);
    
        final String DELETE_SUPERPOWER_BY_ID = "DELETE FROM superpower WHERE id = ?";
        jdbc.update(DELETE_SUPERPOWER_BY_ID, id);
    }
    
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int rowNum) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setId(rs.getInt("id"));
            superpower.setName(rs.getString("name"));
            superpower.setDescription(rs.getString("description"));
            
            return superpower;
        }
        
    }
    
}
