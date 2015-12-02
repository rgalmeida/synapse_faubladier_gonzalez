package fr.synapsegaming.social.dao.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.social.dao.ForumReplyDao;
import fr.synapsegaming.social.entity.ForumReply;

@Repository("ForumReplyDao")
public class ForumReplyDaoImpl extends AbstractDao<ForumReply, Long> implements
        ForumReplyDao {
    private static final Logger LOGGER = Logger.getLogger(ForumReplyDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<ForumReply> getAllReplies()
    {
    	List<ForumReply> burstForumReplyList=null;
    	Query query=null;
    	
    	Session session=this.getSession();
    	try{
    		query=session.createQuery("from ForumReply");
    		return query.list();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    		return Collections.EMPTY_LIST;
    	}
    	finally
    	{
    		session.close();
    	}
    	
    }
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listUsersActivityScore(Date intervalBegin, Date intervalEnd, int maxResults) 
	{
		// TODO Auto-generated method stub

		List<Object[]> queryCreatedReplies=null;
		List<Object[]> queryModifiedReplies=null;

		Query query=null;
		Session session=this.getSession();
		
		String queryString;
		try{
			System.out.println("intentando");
			//query=session.createQuery("select id from ForumReply forumreply");
			queryString = "select u.nickname, u.id, count(*) from ForumReply r join r.author as u ";
			if(intervalBegin!=null || intervalEnd!=null)
			{
				queryString+="where ";
				if(intervalBegin!=null && intervalEnd!=null)
				{
					queryString+="r.dateCreate between :dateBegin and :dateEnd ";
				}
				else
				{
					if(intervalBegin!=null)
					{
						queryString+="r.dateCreate >= :dateBegin ";
					}
					if(intervalEnd!=null)
					{
						queryString+="r.dateCreate < :dateEnd ";
					}
				}
			}
			queryString+= "group by u.id order by count(u.id) desc";
			
			query=session.createQuery(queryString);
			if(intervalBegin!=null)
			{
				query.setParameter("dateBegin", intervalBegin);
			}
			if(intervalEnd!=null)
			{
				query.setParameter("dateEnd", intervalEnd);
			}
			
			queryCreatedReplies=query.list();
			
			
			//setting up second query;
			queryString = "select u.nickname, u.id, count(*) from ForumReply r join r.author as u ";
			if(intervalBegin!=null || intervalEnd!=null)
			{
				queryString+="where ";
				if(intervalBegin!=null && intervalEnd!=null)
				{
					queryString+="r.dateUpdate between :dateBegin and :dateEnd ";
				}
				else
				{
					if(intervalBegin!=null)
					{
						queryString+="r.dateUpdate >= :dateBegin ";
					}
					if(intervalEnd!=null)
					{
						queryString+="r.dateUpdate < :dateEnd ";
					}
				}
			}
			queryString+= "group by u.id order by count(*) desc";
			
			query=session.createQuery(queryString);
			if(intervalBegin!=null)
			{
				query.setParameter("dateBegin", intervalBegin);
			}
			if(intervalEnd!=null)
			{
				query.setParameter("dateEnd", intervalEnd);
			}
			
			queryModifiedReplies=query.list();
			//return query.list();

		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			LOGGER.warn(e);
			return Collections.EMPTY_LIST;
		}
		finally
		{
			session.close();
		}
		
		System.out.println("se ha finalizado la consulta");
		for(Object[] o: queryCreatedReplies)
		{
			System.out.println("Linea : " + o[0].toString() + " " + o[1].toString() + " " + o[2].toString());
			for(Object[] p: queryModifiedReplies)
			{
				if(o[1]==p[1])
				{
					System.out.println("one reply was found, initial score: "+ o[2].toString() + " , adding : " + p[2].toString());
					o[2]=Integer.parseInt(o[2].toString())+Integer.parseInt(p[2].toString());
					System.out.println("score for user " + o[0] + " was modified to : "+o[2]);
				}
				
				
			}
		}
		
		//Adding scores replies modifications that are not showed in the first table
		boolean found;
		List<Object[]> toAdd=new ArrayList<Object[]>();
		for(Object[] p: queryModifiedReplies)
		{
			
			found=false;
			for(Object[] o: queryCreatedReplies)
			{
				if(p[1].toString().equals(o[1].toString())==true)
				{
					System.out.println("user "+ p[0] + " score is already registered");
					found=true;
				}
			}
			if(found==false)
			{
				System.out.println("user "+ p[0] + " was not registered adding to list toAdd");
				toAdd.add(p);
			}
			
		}
		
		for(Object[] itemToAdd : toAdd)
		{
			System.out.println("adding item user "+itemToAdd[0].toString());
			queryCreatedReplies.add(itemToAdd);
		}
		System.out.println("longitud de la lista: "+ queryCreatedReplies.size());
		return queryCreatedReplies;

	}

}
