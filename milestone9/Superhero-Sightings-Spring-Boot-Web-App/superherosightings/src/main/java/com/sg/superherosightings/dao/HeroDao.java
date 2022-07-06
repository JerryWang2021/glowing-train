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
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface HeroDao {
    
    Hero getHeroById(int id);
    List<Hero> getAllHeros();
    Hero addHero(Hero hero);
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    
    List<Hero> getHerosForLocation(Location location);
    Hero getHeroForSighting(Sighting sighting);
    List<Hero> getHerosForOrganization(Organization organization);
}
