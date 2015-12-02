package fr.synapsegaming.user.service;

/**
 * <b>ClazzService</b> is the public interface for Clazz business logic
 * @author Meidi
 *
 */
import java.util.List;

import fr.synapsegaming.user.entity.Clazz;

public interface ClazzService {

    /**
     * List every Clazz
     * 
     * @return a list of Clazz
     */
    public List<Clazz> list();

    /**
     * List every Clazz owned by a Race
     * 
     * @param idRace
     *            : the race provided
     * @return a list of Clazz
     */
    public List<Clazz> listClassForRace(long idRace);

}
