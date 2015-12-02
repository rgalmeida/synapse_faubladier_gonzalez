package fr.synapsegaming.social.dao;

import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.social.entity.Forum;

/**
 * <b>ForumDao</b> is the public interface who describe the Forum DAO
 * 
 * @author Meidi
 * 
 */
public interface ForumDao extends Dao<Forum, Long> {

    /**
     * Get all the forums for the group
     * 
     * @param idGroup
     *            : the id of the current user's group
     * @return a list of the forums for a group
     */
    public List<Forum> list(int idGroup);

}