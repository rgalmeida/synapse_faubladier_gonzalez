package fr.synapsegaming.raid.service;

import fr.synapsegaming.raid.entity.Patch;

/**
 * <b>PatchService</b> is the public interface for Patch business logic
 * 
 * @author Meidi
 * 
 */
public interface PatchService {

    /**
     * Return the last patch provided by blizzard
     * 
     * @return
     */
    public Patch getLastPatch();

}
