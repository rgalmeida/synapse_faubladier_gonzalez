package fr.synapsegaming.raid.service;

import fr.synapsegaming.raid.entity.Extension;

/**
 * <b>ExtensionService</b> is the public interface for Extension business logic
 * 
 * @author Meidi
 * 
 */
public interface ExtensionService {

    /**
     * Get the last wow extension
     * 
     * @return
     */
    public Extension getLastExtension();

}
