package fr.synapsegaming.ui.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.ui.entity.Resource;

/**
 * <b>ResourceDao</b> is the public interface who describe the resource DAO
 * 
 * @author Meidi
 * 
 */
public interface ResourceDao extends Dao<Resource, Long> {

    /**
     * List all the resources from guest group
     * 
     * @param : the group's id
     * @return
     */
    public List<Resource> listResourcesForGroup(int idGroup);

}
