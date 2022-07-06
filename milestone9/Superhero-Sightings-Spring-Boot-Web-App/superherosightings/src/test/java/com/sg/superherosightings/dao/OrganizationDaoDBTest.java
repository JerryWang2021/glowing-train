/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.entities.Hero;
import com.sg.superherosightings.entities.Location;
import com.sg.superherosightings.entities.Organization;
import com.sg.superherosightings.entities.Sighting;
import com.sg.superherosightings.entities.Superpower;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Jerry
 */
@SpringBootTest
public class OrganizationDaoDBTest {
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    public OrganizationDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Hero> heros = heroDao.getAllHeros();
        for(Hero hero : heros){
            heroDao.deleteHeroById(hero.getId());
        }
        
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations){
            locationDao.deleteLocationById(location.getId());
        }
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for(Organization organization : organizations){
            organizationDao.deleteOrganizationById(organization.getId());
        }
        
        List<Sighting> sightings = sightingDao.getAllSightings();
        for(Sighting sighting : sightings){
            sightingDao.deleteSightingById(sighting.getId());
        }
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower : superpowers){
            superpowerDao.deleteSuperpowerById(superpower.getId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addOrganization and getOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testAddAndGetOrganization() {
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setDescription("Test description");
        organization.setIsMember(true);
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization,fromDao);
    }

    /**
     * Test of getAllOrganizations method, of class OrganizationDaoDB.
     */
    @Test
    public void testGetAllOrganizations() {
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setDescription("Test description");
        organization.setIsMember(true);
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization = organizationDao.addOrganization(organization);
        
        Organization organization2 = new Organization();
        organization2.setName("Test name 2");
        organization2.setDescription("Test description 2");
        organization2.setIsMember(true);
        organization2.setAddress("Test address 2");
        organization2.setContact("Test contact 2");
        organization2 = organizationDao.addOrganization(organization);
        
        List<Organization> organizations = organizationDao.getAllOrganizations();
        
        assertEquals(2, organizations.size());
        assertTrue(organizations.contains(organization));
        assertTrue(organizations.contains(organization2));
        
    }

    
    /**
     * Test of updateOrganization method, of class OrganizationDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setDescription("Test description");
        organization.setIsMember(true);
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization = organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        assertEquals(organization, fromDao);
        
        organization.setName("New Test Name");
        organizationDao.updateOrganization(organization);
        
        assertNotEquals(organization, fromDao);
        
        fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertEquals(organization, fromDao);
        
    }

    /**
     * Test of deleteOrganizationById method, of class OrganizationDaoDB.
     */
    @Test
    public void testDeleteOrganizationById() {
        Superpower superpower = new Superpower();
        superpower.setName("Test Name");
        superpower.setDescription("Test Description");
        superpower = superpowerDao.addSuperpower(superpower);
        
        Organization organization = new Organization();
        organization.setName("Test name");
        organization.setDescription("Test description");
        organization.setIsMember(true);
        organization.setAddress("Test address");
        organization.setContact("Test contact");
        organization = organizationDao.addOrganization(organization);
        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        
        Hero hero = new Hero();
        hero.setName("Test Name");
        hero.setDescription("Test Description");
        hero.setSuperpower(superpower);
        hero.setOrganizations(organizations);
        hero = heroDao.addHero(hero);
        
        Location location = new Location();
        location.setName("Test Name");
        location.setDescription("Test Description");
        location.setAddress("Test Address");
        location.setLatitude(12.37778);
        location.setLongitude(-20.77777);
        location = locationDao.addLocation(location);
        
       
        Sighting sighting = new Sighting();
        sighting.setTime(LocalDate.now());
        sighting.setHero(hero);
        sighting.setLocation(location);
        sighting = sightingDao.addSighting(sighting);
        
        organizationDao.deleteOrganizationById(organization.getId());
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getId());
        
        assertNull(fromDao);
    }
}
