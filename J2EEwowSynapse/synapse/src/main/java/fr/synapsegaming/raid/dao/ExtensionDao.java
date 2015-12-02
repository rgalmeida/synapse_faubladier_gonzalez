package fr.synapsegaming.raid.dao;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Extension;

/**
 * <b>ExtensionDao</b> is the public interface who describe the Extension DAO
 * 
 * @author Meidi
 * 
 */
public interface ExtensionDao extends Dao<Extension, Long> {

    /**
     * Get the last extension
     * 
     * @return
     */
    public Extension getLastExtension();

}
