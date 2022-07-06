/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;


import com.sg.superherosightings.dao.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.dao.LocationDaoDB.LocationMapper;
import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
public class SightingDaoDB implements SightingDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting getSightingById(int id) {
        try {
            final String SELECT_SIGHTING_BY_ID = "SELECT * FROM sighting WHERE id = ?";
            Sighting sighting = jdbc.queryForObject(SELECT_SIGHTING_BY_ID, new SightingMapper(), id);
        sighting.setHero(getHeroForSighting(id));
        sighting.setLocation(getLocationForSighting(id));
        return sighting;
        }catch(DataAccessException ex){
            return null;
        }
    }
//    -------helper-------
    private Location getLocationForSighting(int id) {
        final String SELECT_LOCATION_FOR_SIGHTING = "SELECT l.* FROM location l "
                + "JOIN sighting s ON s.locationId = l.id WHERE s.id = ?";
        return jdbc.queryForObject(SELECT_LOCATION_FOR_SIGHTING, new LocationMapper(), id);
    }
    
    private Hero getHeroForSighting(int id) {
        final String SELECT_HERO_FOR_SIGHTING = "SELECT h.* FROM hero h "
                + "JOIN sighting s ON s.heroId = h.id WHERE s.id = ?";
         Hero hero=jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroMapper(), id);
         hero.setSuperpower(getSuperpowerForHero(hero.getId()));
         hero.setOrganizations(getOrganizationsForHero(hero.getId()));
         return hero;
    }
//    -----helper for getHeroForSighting------
    private Superpower getSuperpowerForHero(int id) {
        final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.* FROM superpower s "
                + "JOIN hero h ON s.id = h.superpowerId WHERE h.id = ?";
        return jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerMapper(), id);
    }

    private List<Organization> getOrganizationsForHero(int id) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = 
                "SELECT o.* "
                + "FROM organization o "
                + "JOIN hero_organization ho "
                +       "ON o.id = ho.organizationId "
                + "JOIN hero h ON ho.heroId = h.id "
                + "WHERE h.id = ?";
                
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO, new OrganizationMapper(), id);
    }
    

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTINGS = "SELECT * FROM sighting";
        List<Sighting> sightings = jdbc.query(SELECT_ALL_SIGHTINGS, new SightingMapper());
        associateHeroAndLocation(sightings);
        return sightings;
    }
    
    private void associateHeroAndLocation(List<Sighting> sightings){
        for (Sighting sighting : sightings) {
            sighting.setHero(getHeroForSighting(sighting.getId()));
            sighting.setLocation(getLocationForSighting(sighting.getId()));
        }
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING = "INSERT INTO sighting(time, heroId, locationId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING, 
                sighting.getTime(),
                sighting.getHero().getId(),
                sighting.getLocation().getId());
        
        int newId = jdbc.queryForObject("SELECT LAST_iNSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_SIGHTING = "UPDATE sighting SET time=?, heroId=?, locationId=? "
                + "WHERE id=?";
        jdbc.update(UPDATE_SIGHTING, 
                sighting.getTime(),
                sighting.getHero().getId(),
                sighting.getLocation().getId(),
                sighting.getId());
    }

    @Override
    @Transactional
    public void deleteSightingById(int id) {
        final String DELETE_SIGHTING_BY_ID = "DELETE FROM sighting WHERE id = ?";
        jdbc.update(DELETE_SIGHTING_BY_ID, id);
    }

    @Override
    public List<Sighting> getSightingsForTime(LocalDateTime time){
        final String SELECT_SIGHTINGS_FOR_TIME = "SELECT FROM sighting WHERE time = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_TIME, new SightingMapper(), time);
        associateHeroAndLocation(sightings);
        return sightings;
    }

    
    
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int rowNum) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("id"));
            sighting.setTime(rs.getTimestamp("time").toLocalDateTime().toLocalDate());
            return sighting;
        }
        
    }
    
}
