/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.OrganizationDaoDB.OrganizationMapper;
import com.sg.superherosightings.dao.SuperpowerDaoDB.SuperpowerMapper;
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
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
public class HeroDaoDB implements HeroDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero getHeroById(int id) {
        try {
            final String SELECT_HERO_BY_ID = "SELECT * FROM hero WHERE id = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_BY_ID, new HeroMapper(), id);
            hero.setSuperpower(getSuperpowerForHero(hero));
            hero.setOrganizations(getOrganizationsForHero(hero));
            return hero;
        }catch(DataAccessException ex) {
            return null;
        }
    }
    
//    -----helper-----
    private Superpower getSuperpowerForHero(Hero hero) {
         final String SELECT_SUPERPOWER_FOR_HERO = "SELECT s.* FROM superpower s "
                 + "JOIN hero ON  s.id = hero.superpowerId WHERE hero.id = ?";
         return jdbc.queryForObject(SELECT_SUPERPOWER_FOR_HERO, new SuperpowerMapper(), hero.getId());
     }

    private List<Organization> getOrganizationsForHero(Hero hero) {
        final String SELECT_ORGANIZATIONS_FOR_HERO = "SELECT o.* FROM organization o "
                + "JOIN hero_organization ho ON o.id = ho. organizationId WHERE ho.heroId = ?";
        return jdbc.query(SELECT_ORGANIZATIONS_FOR_HERO, new OrganizationMapper(), hero.getId());
    }
    
     private void addSuperpowerAndOrganizationsToHeros(List<Hero> heros) {
        for(Hero hero : heros) {
            hero.setSuperpower(getSuperpowerForHero(hero));
            hero.setOrganizations(getOrganizationsForHero(hero));
        }
    }
     
    private void insertHeroOrganization(Hero hero) {
         final String INSERT_HERO_ORGANIZATION = "INSERT INTO hero_organization"
                 + "(heroId, organizationId) VALUES(?,?)";
         for(Organization organization : hero.getOrganizations()){
             jdbc.update(INSERT_HERO_ORGANIZATION, hero.getId(), organization.getId());
         }
     }

    
    @Override
    public List<Hero> getAllHeros() {
        final String SELECT_ALL_HEROS = "SELECT * FROM hero";
        List<Hero> heros = jdbc.query(SELECT_ALL_HEROS, new HeroMapper());
        
        addSuperpowerAndOrganizationsToHeros(heros);
        
        return heros;
    }

    @Override
    @Transactional
    public Hero addHero(Hero hero) {
        final String INSERT_HERO = "INSERT INTO hero(name, description, superpowerId) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_HERO, 
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        hero.setId(newId);
        insertHeroOrganization(hero);
        return hero;
    }

    @Override
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero SET name=?, description=?, superpowerId=? WHERE id=?";
        jdbc.update(UPDATE_HERO, 
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower().getId(),
                hero.getId());
        
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM hero_organization WHERE heroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, hero.getId());
        
        insertHeroOrganization(hero);
    }

    @Override
    public void deleteHeroById(int id) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM hero_organization WHERE heroId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_SIGHTING = "DELETE FROM sighting s WHERE s.heroId = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_HERO = "DELETE FROM  hero WHERE id = ?";
        jdbc.update(DELETE_HERO, id);
    }

    @Override
    public List<Hero> getHerosForLocation(Location location) {
        final String SELECT_HEROS_FOR_LOCATION = "SELECT * FROM hero "
                + "JOIN sighting s ON hero.id = s.heroId WHERE s.locationId = ?";
        List<Hero> heros = jdbc.query(SELECT_HEROS_FOR_LOCATION, new HeroMapper(), location.getId());
        
        addSuperpowerAndOrganizationsToHeros(heros);
        
        return heros;
    }

    @Override
    public Hero getHeroForSighting(Sighting sighting) {
        try {
            final String SELECT_HERO_FOR_SIGHTING = "SELECT * FROM hero "
                + "JOIN sighting s ON  hero.id = s.heroId WHERE s.id = ?";
            Hero hero = jdbc.queryForObject(SELECT_HERO_FOR_SIGHTING, new HeroMapper(), sighting.getId());
            hero.setSuperpower(getSuperpowerForHero(hero));
            hero.setOrganizations(getOrganizationsForHero(hero));
            return hero;
        }catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> getHerosForOrganization(Organization organization) {
        List<Hero> heros = organization.getHeros();
        addSuperpowerAndOrganizationsToHeros(heros);
        return heros;
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("id"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            return hero;
        }
        
    }
}
