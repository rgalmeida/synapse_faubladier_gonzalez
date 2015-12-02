package fr.synapsegaming.user.service;

import java.util.List;

import fr.synapsegaming.user.entity.Race;

/**
 * <b>RaceService</b> is the public interface for Race business logic
 * 
 * @author Meidi
 * 
 */
public interface RaceService {

    /**
     * List every Races
     * 
     * @return a list of Races
     */
    public List<Race> list();
}
