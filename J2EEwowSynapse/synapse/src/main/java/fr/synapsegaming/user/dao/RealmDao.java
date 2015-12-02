package fr.synapsegaming.user.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.user.entity.Realm;

/**
 * 
 * <b>RealmDao</b> is the public interface who describe the Realm DAO
 * 
 * @author Meidi
 * 
 */
public interface RealmDao extends Dao<Realm, Integer> {

    /**
     * Get all the realms availables ordered by name
     * 
     * @return a list of realms
     */
    public List<Realm> listRealmsOrderByName();
}
