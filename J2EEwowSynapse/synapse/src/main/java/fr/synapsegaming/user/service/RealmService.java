package fr.synapsegaming.user.service;

import java.util.List;

import fr.synapsegaming.user.entity.Realm;

/**
 * <b>RealmService</b> is the public interface for Realm business logic
 * 
 * @author Meidi
 * 
 */
public interface RealmService {

    /**
     * List every Realms
     * 
     * @return a list of Realms
     */
    public List<Realm> list();

    /**
     * Find a realm by id
     * 
     * @param id
     *            : technical identifier of the realm
     * @return
     */
    public Realm find(int id);

}
