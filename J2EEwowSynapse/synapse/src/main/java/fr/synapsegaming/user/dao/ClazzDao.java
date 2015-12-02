package fr.synapsegaming.user.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.user.entity.Clazz;

/**
 * <b>ClazzDao</b> is the public interface who describe the Clazz DAO
 * 
 * @author Meidi
 * 
 */
public interface ClazzDao extends Dao<Clazz, Long> {

    /**
     * List all the classes owned by a Race
     * 
     * @param idRace
     *            : the race reference
     * @return a list of Clazz
     */
    public List<Clazz> listClassForRace(long idRace);

}
