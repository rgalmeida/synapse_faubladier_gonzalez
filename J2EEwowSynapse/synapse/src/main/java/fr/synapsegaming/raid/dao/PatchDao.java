package fr.synapsegaming.raid.dao;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Patch;

/**
 * <b>PatchDao</b> is the public interface who describe the Patch DAO
 * 
 * @author Meidi
 * 
 */
public interface PatchDao extends Dao<Patch, Long> {

    /**
     * Get the last Patch provided by Blizzard
     * 
     * @return Patch
     */
    public Patch getLastPatch();

}
