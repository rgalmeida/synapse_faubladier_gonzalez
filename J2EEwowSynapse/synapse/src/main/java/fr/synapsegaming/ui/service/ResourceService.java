package fr.synapsegaming.ui.service;

import java.util.List;

import fr.synapsegaming.ui.entity.Resource;

/**
 * <b>ResourceService</b> is the public interface for Resource business logic
 * 
 * @author Meidi
 * 
 */
public interface ResourceService {

    /**
     * List all the resources available
     * 
     * @return a list of Resources
     */
    public List<Resource> list();

    /**
     * List all the resources to display to the visitors
     * 
     * @return a list of Resources
     */
    public List<Resource> listMainMenu();

    /**
     * List all the resources that are specific to the user group
     * 
     * @param idGroup
     *            : the group id
     * @return a list of Resources
     */
    public List<Resource> listUserResources(int idGroup);

    /**
     * List all the resources except those for everyone
     * 
     * @param idGroup
     * @return a list of restricted resources
     */
    public List<Resource> listUserRestrictedResources(int idGroup);

}
