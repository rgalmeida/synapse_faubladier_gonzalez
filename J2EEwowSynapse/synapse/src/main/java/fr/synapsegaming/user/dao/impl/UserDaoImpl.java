package fr.synapsegaming.user.dao.impl;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import fr.synapsegaming.commons.dao.AbstractDao;
import fr.synapsegaming.stats.entity.TypeAndQuantity;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.dto.ClazzDTO;
import fr.synapsegaming.user.entity.User;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
	

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public User findByMail(String mail) {
        return (User) DataAccessUtils.uniqueResult(getHibernateTemplate().find("from User u where u.mail = ?", mail));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<User> listByGroups(List<Integer> groups) {
        Session session = this.getSession();
        try {
            Query query = session.createQuery("select u.id, g.id from User u join fetch u.group g where g.id in (:groupIds)");
            System.out.println(query.getQueryString());
            query.setParameterList("groupIds", groups);
            
            return query.list();
        } catch (Exception e) {
            LOGGER.warn(e);
            System.out.println(e.toString());
            return Collections.emptyList();
        } finally {
            session.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<User> listByClass(List<Integer> clazz){
    	Session session = this.getSession();
    	try{
    		Query query= session.createQuery("from User u join fetch u.clazz c where c.id in (:groupClazz)");
    		query.setParameterList("groupIds", clazz);
    		return query.list();
    	}catch (Exception e){
    		LOGGER.warn(e);
    		System.out.println(e.toString());
    		return Collections.emptyList();
    	}finally{
    		session.close();
    	}
    }

    
    /**
     * Lists top n class played.
     * 
     * @param n integer is the number of items to display. The function assumes that n will be positive greater than 0. 
     */
   
    @SuppressWarnings("unchecked")
	@Override
	public List<Object> listTopClassPlayed(int n) {
		
    	// TODO Auto-generated method stub
    		List<Object> list;
    			list = null;
    			String[] firstLine=new String[2];
    			
    			firstLine[0]="Name of the class";
    			firstLine[1]="Quantity";
    			//lista.add((Object)firstLine);
    			Session session=this.getSession();
    			try{
    				
    				/*select classes.name as name,users.id_class,count(users.id_class) as cuenta 
    				  from users,classes 
    				  where  users.id_class like classes.id_class 
    				  group by id_class order by cuenta desc*/
    				System.out.println("top class played:");
    				Query query=session.createQuery("select c.name, count(*) from User u join u.clazz as c group by c.id order by count(*) desc");
    				query.toString();
    				query.setMaxResults(n);
    				list=query.list();			
    				
    			}catch(Exception e){
    				LOGGER.warn(e);
    				System.out.println(e.toString());
    				return Collections.EMPTY_LIST;
    			}finally{
    				session.close();
    			}
    			
    			return list;
		
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Object> Test() {
		// TODO Auto-generated method stub
		//System.out.println("hola mundo :: userDaoImpl Test");
		List<Object> lista;
		
		Session session=this.getSession();
		try{
			
			/*select classes.name as name,users.id_class,count(users.id_class) as cuenta 
			  from users,classes 
			  where  users.id_class like classes.id_class 
			  group by id_class order by cuenta desc*/
			Query query=session.createQuery("select count(*), g.name, u.group.name from User u join u.group as g group by g.id order by count(*) desc");
			//query.setMaxResults(5);
			lista=query.list();
						
			
		}catch(Exception e){
			LOGGER.warn(e);
			System.out.println(e.toString());
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
		return lista;
	}
    
    
    /**
     * Lists of players using the default image avatar.
     *  
     */
    @SuppressWarnings("unchecked")
	@Override
	public List<User> listUsersWithDefaultAvatar() {
		// TODO Auto-generated method stub
    	//System.out.println("HOLAMUNDO");
    	Session session=this.getSession();
    	try{
    		Query query=session.createQuery("from User u where u.forumAvatar = (:avatarForumLink)");
            query.setParameter("avatarForumLink",User.USER_LINK_DEFAULT_AVATAR);
            System.out.println("size of the query"+query.list().size());
            return query.list();

    	}catch(Exception e){
    		LOGGER.warn(e);
    		System.out.println(e.toString());
    		return Collections.EMPTY_LIST;
    	}finally{
    		session.close();
    	}
	}
    
    
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Object> listTopRacesPlayed(int n) {
		// TODO Auto-generated method stub

		/* extreme programming explained*/
    	List<Object> lo;
		Session session=this.getSession();
		try{
			Query query=session.createQuery("select r.name, count(*) from User u join u.race as r group by r.id order by count(*) desc");
			query.setMaxResults(n);
			return query.list();
			/*
			lo=query.list();
			
			for(Object o: lo)
			{
				System.out.println(o.toString());
			}
			
			return lo;*/
		}catch(Exception e){
			LOGGER.warn(e);
			System.out.println(e.toString());
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
	}
    @SuppressWarnings("unchecked")
	@Override
	public List<Object> listTopSpecializationsPlayed(int n) {
		// TODO Auto-generated method stub
		/* extreme programming explained*/
		Session session=this.getSession();
		try{
			//System.out.println("intentando la query de most used specializations");
			Query query=session.createQuery("select s.name, count(*) from User u join u.spec as s group by s.id order by count(*) desc");
			query.setMaxResults(n);
			return query.list();
		}catch(Exception e){
			LOGGER.warn(e);
			System.out.println(e.toString());
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
	}
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Object> orderedListByFieldRepetition(String tableClassName,String repetitionField)
    {
    	List<Object[]> list2Return;
    	Session session=this.getSession();
    	try{
    		//Query query=session.createQuery("Select T.(:repetitionfield) ,count(*) from (:tablename) T group by (:repetitionField) order by count(*) desc");
    		Query query=session.createQuery("Select u.clazz.id ,count(*) from User u join u.clazz group by u.clazz.id order by count(*) desc");
    		query.setParameter("tablename", tableClassName);
    		query.setParameter("repetitionfield", repetitionField);
    		list2Return=(List<Object[]>)query.list();
    		/*************************/
    		System.out.println("hola mundo");
    		for(Object[] tmp : list2Return)
    		{
    			System.out.println("hola mundo");
    			System.out.println("-> [0] "+tmp[0]+" <-> [1]" + tmp[1]);
    		}
    		return query.list();
    		
    	}catch(Exception e){
    		System.out.println("algo ha pasado");
    		LOGGER.warn(e);
    		return Collections.EMPTY_LIST;
    	}finally{
    		
    		session.close();
    	}
    }

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listTopUsedbyType(String type, int maxResults)  {
		// TODO Auto-generated method stub
		User tmpUser = null;
		Session session=this.getSession();
		//String typeOfFieldSearched = null;
		/*
		System.out.println("Te estoy buscando!");
		try {
			typeOfFieldSearched = tmpUser.getClass().getField(type);
		} catch (NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			System.out.println("no te he encontrado :_(");
			e1.printStackTrace();
		} catch (Exception e){
			System.out.println("no te he encontrado :_(");

			e.printStackTrace();
		}
		List<Object> returnList = null;
		System.out.println("Te encontre!" + typeOfFieldSearched.getClass());*/

		try{
			Query query=session.createQuery("Select t.name, count(*) from User u join u."+type+" as t group by t.id order by count(*) desc");
			query.setMaxResults(maxResults);
			
			return query.list();
		}catch(Exception e){
			System.out.println(e.toString());
			LOGGER.warn(e);
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listUsersActivityScore(Date intervalBegin,Date intervalEnd,int maxResults) {
		// TODO Auto-generated method stub
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
		Session session=this.getSession();
		List<Object> queryCreatedPosts=null;
		List<Object> queryUpdatedPosts=null;
		Query query=null;
		try{
			query=session.createQuery("select fp.author as activity_user, count(fp.author) as user_score from ForumPost fp group by fp.author order by count(fp.author) desc");
			if(maxResults!=0)
			{
				query.setMaxResults(maxResults);
			}
			queryCreatedPosts=query.list();
			
			query=session.createQuery("select fp.lastUpdateUser as activity_user, count(fp.lastUpdateUser) as user_score from ForumPost fp group by fp.lastUpdateUser order by count(fp.lastUpdateUser) desc");
			if(maxResults!=0)
			{
				query.setMaxResults(maxResults);
			}
			queryUpdatedPosts=query.list();
			
			
			
			
		}catch(Exception e){
			System.out.println(e.toString());
			LOGGER.warn(e);
			return Collections.EMPTY_LIST;
		}finally{
			session.close();
		}
		
		
		queryCreatedPosts.add(queryUpdatedPosts);
		
		int n=0;
		System.out.println("-------------------------- -");
		for(n=0;n<queryCreatedPosts.size();n++)
		{
			System.out.println(" linea " + n + ":");
			for(Object o: (Object[])queryCreatedPosts.get(n) )
			{
				System.out.print(o.toString()+" ");
			}
		}
		return null;
	}

	@Override
	public boolean holaMundo() {
		// TODO Auto-generated method stub
		return false;
	}
}
