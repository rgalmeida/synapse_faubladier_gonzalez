package fr.synapsegaming.social.dao;

import java.util.Date;
import java.util.List;

import fr.synapsegaming.commons.dao.Dao;
import fr.synapsegaming.social.entity.ForumReply;

/**
 * <b>ForumReplyDao</b> is the public interface who describe the ForumReply DAO
 * 
 * @author Meidi
 * 
 */
public interface ForumReplyDao extends Dao<ForumReply, Long> {
	
	public List<Object[]> listUsersActivityScore(Date intervalBegin,Date intervalEnd,int maxResults) ;
	


	public List<ForumReply> getAllReplies();

}
