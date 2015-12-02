package fr.synapsegaming.social.dao.impl;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.social.dao.ForumPostDao;
import fr.synapsegaming.social.entity.ForumPost;

@Repository("ForumPostDao")
public class ForumPostDaoImpl extends AbstractDao<ForumPost, Long> implements
        ForumPostDao {
	class tempClass{
		String uno;
		Integer dos;
		Integer tres;
	}
    private static final Logger LOGGER = Logger.getLogger(ForumPostDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listUsersActivityScore(Date intervalBegin,Date intervalEnd,int maxResults) {
		// TODO Auto-generated method stub

		Session session=this.getSession();
		List<Object[]> queryCreatedPosts=null;
		
		List<Object[]> queryUpdatedPosts=null;
		
		Query query=null;
		String queryString;
		int n=0;
		if(intervalBegin!=null)System.out.println("interval begin: "+intervalBegin.toString());
		if(intervalEnd!=null)System.out.println("interval end: "+intervalEnd.toString());
		try{
			queryString = "select u.nickname, u.id, count(*) from ForumPost p join p.author as u ";
			if((intervalBegin !=null) || (intervalEnd != null))
			{
				
				queryString+= "where ";
				if((intervalBegin !=null) && (intervalEnd != null))
				{
					queryString+= "p.dateCreate between :dateBegin and :dateEnd ";
				}
				else
				{
					if(intervalBegin !=null)
					{
						queryString+= "p.dateCreate >= :dateBegin ";
					}
					if(intervalEnd != null)
					{
						queryString+= "p.dateEnd < :dateEnd ";
					}
				}
			}
			queryString+="group by u.id order by count(*) desc";
			System.out.println("ahi vamos");
			query=session.createQuery(queryString);
			if(intervalBegin!=null)
			{
				query.setParameter("dateBegin", intervalBegin);
			}
			if(intervalEnd!= null)
			{
				query.setParameter("dateEnd", intervalEnd);
			}
			if(maxResults!=0)
			{
				query.setMaxResults(maxResults);
			}
			queryCreatedPosts=query.list();
			
			
			
			
			
		}catch(Exception e){
			System.out.println(e.toString());
			LOGGER.warn(e);
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
		
		
		
		
		
		return queryCreatedPosts;
	}
	/*
	 *  
	 *  select id_user as id_user, count(id_user) as num_posts 
	 *  from f_posts 
	 *  group by id_user order by count(id_user) desc
	 *  
	 *  
	 *  
	 	select t.id_user as id_user, count(t.id_user) as num_posts
		from 
			(select f.id_user as id_user, f.id_post as num_post 
			from f_posts f ) t
		group by t.id_user order by count(t.id_user) desc
		
		
		select * from f_posts 
			where 
				date_create between "2015-04-01" and "2015-05-01" 
			or 
				date_update between "2015-04-01" and "2015-05-01"
				
		subconsulta 2 tabla 1;
		select id_user 
		from f_posts 
		where date_create between "2015-04-01" and "2015-05-01" 
		order by id_user
		
		select postsUpdate.id_user_update as id_user 
		from f_posts postsUpdate 
		where postsUpdate.date_update between "2015-04-01" and "2015-05-01" 
		order by id_user

---- SUMA DE AMBAS -------
		select st1.id_user 
from 	(select id_user 
	from f_posts 
	where date_create between "2015-04-01" and "2015-05-01" 
	order by id_user) st1,

	(select postsUpdate.id_user_update as id_user 
	from f_posts postsUpdate 
	where postsUpdate.date_update between "2015-04-01" and "2015-05-01" 
	order by id_user) st2
		
		
		
		
	select st1.id_user, st1.id_post
from 	(select id_user as id_user ,id_post as id_post
		from f_posts 
		where date_create between "2015-04-01" and "2015-05-01" 
		
     union all
		select postsUpdate.id_user_update as id_user, id_post as id_post 
		from f_posts postsUpdate 
		where postsUpdate.date_update between "2015-04-01" and "2015-05-01" 
		) st1
	
	USERS ACTIVITY POSTS SCORES TIME RANGE
	select usersActivity.id_user as id_user, count (usersActivity.id_user) as user_score
	from 	(select id_user as id_user ,id_post as id_post
				from f_posts 
				where date_create between "2015-04-01" and "2015-05-01" 
				
	         union all
				select postsUpdate.id_user_update as id_user, id_post as id_post 
				from f_posts postsUpdate 
				where postsUpdate.date_update between "2015-04-01" and "2015-05-01" 
				) usersActivity 
	group by id_user
	order by user_score desc
	*/
	
	

	


}
