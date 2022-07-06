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
public class HeroDaoDBTest {
    
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
    
    public HeroDaoDBTest() {
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
     * Test of getHeroById and addHero method, of class HeroDaoDB.
     */
    @Test
    public void testAddAndGetHero() {
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
        
        Hero fromDao = heroDao.getHeroById(hero.getId());
        
        assertEquals(hero, fromDao);
    }

    /**
     * Test of getAllHeros method, of class HeroDaoDB.
     */
    @Test
    public void testGetAllHeros() {
        
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
        
        List<Hero> heros = heroDao.getAllHeros();
        
        assertEquals(1, heros.size());
        assertTrue(heros.contains(hero));
        
    }

    
    /**
     * Test of updateHero method, of class HeroDaoDB.
     */
    @Test
    public void testUpdateHero() {
        
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
        
        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
        
        hero.setName("Test Name2");
        heroDao.updateHero(hero);
        assertNotEquals(fromDao, hero);
        
        fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
    }

    /**
     * Test of deleteHeroById method, of class HeroDaoDB.
     */
    @Test
    public void testDeleteHeroById() {
        
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
        
        
        Hero fromDao = heroDao.getHeroById(hero.getId());
        assertEquals(hero, fromDao);
        
        heroDao.deleteHeroById(hero.getId());
        
        fromDao = heroDao.getHeroById(hero.getId());
        assertNull(fromDao);
        
    }

  }
