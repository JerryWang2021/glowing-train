/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.HeroDaoDB.HeroMapper;
import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Organization;
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
public class OrganizationDaoDB implements OrganizationDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrganizationById(int id) {
        try {
           final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE id = ?";
           Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), id);
           List<Hero> heros = getHerosForOrganization(organization);
           organization.setHeros(heros);
           return organization;
        }catch(DataAccessException ex) {
            return null;
        }
    }
    
//    -----helper-----
     private List<Hero> getHerosForOrganization(Organization organization) {
         final String SELECT_HEROS_FOR_ORGANIZATION = "SELECT h.* FROM Hero h "
                 + "JOIN hero_organization ho ON  h.id = ho.heroId WHERE ho.organizationId = ?";
         return jdbc.query(SELECT_HEROS_FOR_ORGANIZATION, new HeroMapper(), organization.getId());
     }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM organization";
        return jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION = "INSERT INTO organization(name, description, isMember, address, contact) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION, 
                organization.getName(),
                organization.getDescription(),
                organization.isIsMember(),
                organization.getAddress(),
                organization.getContact());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        return organization;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION = "UPDATE organization SET "
                + "name = ?, description = ?, isMember = ?, address = ?, contact = ? "
                + "WHERE id = ?";
        jdbc.update(UPDATE_ORGANIZATION, 
                organization.getName(),
                organization.getDescription(),
                organization.isIsMember(),
                organization.getAddress(),
                organization.getContact(),
                organization.getId());
    }

    @Override
    @Transactional
    public void deleteOrganizationById(int id) {
        final String DELETE_HERO_ORGANIZATION = "DELETE FROM hero_organization WHERE organizationId = ?";
        jdbc.update(DELETE_HERO_ORGANIZATION, id);
        
        final String DELETE_ORGANIZATION = "DELETE FROM organization WHERE id = ?";
        jdbc.update(DELETE_ORGANIZATION, id);
    }

    @Override
    public List<Organization> getOrganizationsForHero(Hero hero) {
       return hero.getOrganizations();
    }

   
    
    
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setIsMember(rs.getBoolean("isMember"));
            organization.setAddress(rs.getString("address"));
            organization.setContact(rs.getString("contact"));
            return organization;
        }
        
    }
    
}
