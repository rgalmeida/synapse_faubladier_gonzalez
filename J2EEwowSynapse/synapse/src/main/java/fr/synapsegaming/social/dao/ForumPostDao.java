package fr.synapsegaming.social.dao;

import java.util.Date;
import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.social.entity.ForumPost;

/**
 * <b>ForumPostDao</b> is the public interface who describe the ForumPost DAO
 * 
 * @author Meidi
 * 
 */
public interface ForumPostDao extends Dao<ForumPost, Long> {
	
	
	
	public List<Object[]> listUsersActivityScore(Date intervalBegin,Date intervalEnd,int maxResults) ;


}
