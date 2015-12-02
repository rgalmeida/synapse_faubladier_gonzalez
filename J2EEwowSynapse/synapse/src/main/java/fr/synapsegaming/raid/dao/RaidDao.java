package fr.synapsegaming.raid.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.raid.entity.Extension;
import fr.synapsegaming.raid.entity.Raid;

/**
 * <b>RaidDao</b> is the public interface who describe the Raid DAO
 * 
 * @author Meidi
 * 
 */
public interface RaidDao extends Dao<Raid, Long> {

    /**
     * List every raid for an extension
     * 
     * @param extension
     *            : the extension containing the raids
     * @return a list of raids
     */
    public List<Raid> listForExtension(Extension extension);

}
