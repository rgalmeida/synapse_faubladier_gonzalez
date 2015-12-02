package fr.synapsegaming.stats.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.synapsegaming.social.dao.ForumPostDao;
import fr.synapsegaming.social.dao.ForumReplyDao;
import fr.synapsegaming.social.entity.ForumPost;
import fr.synapsegaming.social.entity.ForumReply;
import fr.synapsegaming.stats.service.StatsService;
import fr.synapsegaming.user.dao.UserDao;
import fr.synapsegaming.user.entity.User;

@Service("StatsService")
@Transactional

public class StatsServiceImpl implements StatsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ForumPostDao forumPostDao;

	@Autowired
	private ForumReplyDao forumReplyDao;

	@Override
	public List<Object> listTopSpecializationsPlayed(int n) {

		return userDao.listTopSpecializationsPlayed(n);
	}

	@Override
	public List<Object> listTopClassPlayed(int n) {
		// TODO Auto-generated method stub
		return userDao.listTopClassPlayed(n);

	}

	@Override
	public List<Object> getTopRacesPlayed(int n) {
		// TODO Auto-generated method stub
		return userDao.listTopRacesPlayed(n);
	}

	@Override
	public List<User> listUsersWithDefaultAvatar() {
		// TODO Auto-generated method stub

		return userDao.listUsersWithDefaultAvatar();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> listUsersActivityScore(String dateBegin,String dateEnd, int numResultsOnPage,int index) 
	{
		// TODO Auto-generated method stub
		List<Object[]> scoreForumPosts=null;
		List<Object[]> scoreForumReplies=null;
		List<Object[]> scoresList=null;
		SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE);
		System.out.println("about date Begin: "+dateBegin+" , dateEnd "+dateEnd + " and maxResults ");
		Date dateOfInit=null;
		Date dateOfEnd=null;
		
		//parsing dates
		if(dateBegin.equals("")==false)
		{
			try {
				dateOfInit=dateFormatter.parse(dateBegin);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(dateEnd.equals("")==false)
		{
			try {
				dateOfEnd=dateFormatter.parse(dateEnd);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			//Date dateInit=dateInitSF.parse(" 2015-03-01 ");
			//Date dateEnd=dateEndSF.parse(" 2015-05-01 ");
			//dateInit.setDate(date);
			
			
			//if(dateOfInit!=null)System.out.println("date init: "+ dateOfInit.toString());
			//if(dateOfEnd!=null)System.out.println("date end : "+ dateOfEnd.toString());
			scoreForumPosts = forumPostDao.listUsersActivityScore(dateOfInit,dateOfEnd, 0);
			scoreForumReplies = forumReplyDao.listUsersActivityScore(dateOfInit, dateOfEnd, 0);
			if(scoreForumPosts==null)
			{
				//System.out.println("the result of the query for posts was null");
				scoreForumPosts= new ArrayList<Object[]>();
			}
			else
			{
				//just debugging 'a l'ancienne', all theese elses can be removed.
				//System.out.println("the result of the query for posts was a list of size: "+scoreForumPosts.size());
			}
			if(scoreForumReplies==null)
			{
				//System.out.println("the result of the query for replys was null");
				scoreForumReplies=new ArrayList<Object[]>();
			}
			else
			{
				//System.out.println("the result of the query for replies was a list of size: "+scoreForumReplies.size());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		 * IF! the number of posts resulted of a query is 0... 
		 * 		we will have an exception adding the scores for the Replies.
		 * 	solution: in the case that, for one period of time, one user has only posted replies...
		 * 			the score will be only the replies. That's the table that will be returned.
		 * 
		 * in the case that both tables are 0: we will return null
		 */
		if(scoreForumPosts.size()!=0)
		{
		
			//score multiplex
			boolean found;
			List<Object[]> toAdd= new ArrayList<Object[]>();
			for(Object[] scoreForumReply: scoreForumReplies)
			{
				
				//System.out.println("score for user "+scoreForumReply[0]);
				found=false;
				for(Object[] scoreForumPost: scoreForumPosts)
				{
					if(scoreForumPost[1].toString().equals(scoreForumReply[1].toString()))
					{
						found=true;
						scoreForumPost[2]=Integer.parseInt(scoreForumPost[2].toString())+Integer.parseInt(scoreForumReply[2].toString());
						//System.out.println("Added score to user "+scoreForumPost[0].toString());
					}
				}
				if(found==false)
				{
					//System.out.println("adding item to stack for user " + scoreForumReply[0].toString());
					toAdd.add(scoreForumReply);
				}
			}
			
			System.out.println("toAdd stack size :" + toAdd.size());
			for(Object[] itemToAdd: toAdd)
			{
				System.out.println("adding item from stack : " + itemToAdd[0] + " : " + itemToAdd[1] + " : " + itemToAdd[2]);
				scoreForumPosts.add(itemToAdd);
			}
			scoreForumPosts= orderTableByFieldIndex(scoreForumPosts,2,false);
			
			if(numResultsOnPage!=0)
			{
				
				scoresList=new ArrayList<Object[]>();
				int begin,end,i,maxI;
				
				
				begin=(numResultsOnPage*index);
				end=(numResultsOnPage*(index+1));
				maxI=scoreForumPosts.size();
				for(i=begin;((i<end)&&(i<maxI));i++)
				{
					scoresList.add(scoreForumPosts.get(i));
				}
			}
			else
			{
				scoresList= scoreForumPosts;
			}
		}
		//numResultsOnPage=5;
		//index=2;
		if(scoreForumReplies.size()!=0)
		{
			scoreForumReplies=orderTableByFieldIndex(scoreForumReplies,2,false);
			if(numResultsOnPage!=0)
			{
				
				scoresList=new ArrayList<Object[]>();
				int begin,end,i,maxI;
				
				
				begin=(numResultsOnPage*index);
				end=(numResultsOnPage*(index+1));
				maxI=scoreForumPosts.size();
				for(i=begin;((i<end)&&(i<maxI));i++)
				{
					scoresList.add(scoreForumReplies.get(i));
				}
			}
			else
			{
				scoresList= scoreForumReplies;
			}
		}
		return scoresList;
	}

	
	
	
	
	
	
	/*
	 *  To be able to order one table, this class has been created, 
	 *  	to apply one of the methods of the 'Collections' class.
	 *  It has to implement the Comparable <T> interface, and the compareTo 
	 *  the compareTo() method will indicate witch of the fields is going to be ordered.
	 *  
	 *  This class has been created to be the most generic as possible, but for this
	 *  	practice, only the Integer field is needed. 
	 *  
	 *  NOTE : if numbers are treated as strings... "10" will be considered lower than "9"
	 */
	public enum TypeOfField { tInteger, tDouble, tString, tDate};
	private class SortableIndexRow implements Comparable<SortableIndexRow> {
		public final TypeOfField TYPE_INTEGER=TypeOfField.tInteger;
		public final TypeOfField TYPE_DOUBLE=TypeOfField.tDouble;
		public final TypeOfField TYPE_STRING=TypeOfField.tString;
		public final TypeOfField TYPE_DATE=TypeOfField.tDate;

		
		public int index;
		
		public TypeOfField type;

		public Integer fieldToSortInteger;
		public Double fieldToSortDouble;
		public String fieldToSortString;
		protected SimpleDateFormat sfDateFormatter;
		public Date fieldToSortDate;
		
		public boolean setDateFormat(String s)
		{
			try{
				sfDateFormatter=new SimpleDateFormat(s,Locale.FRANCE);
			}catch(Exception e)
			{
				sfDateFormatter=new SimpleDateFormat("dd/MM/yyyy",Locale.FRANCE);
				return false;
			}
			return true;
		}
		
		public boolean set(int i,Object o)
		{
			index=i;
			switch(type)
			{
			case tInteger:
				try{
					
					fieldToSortInteger=Integer.parseInt(o.toString());
				}
				catch(NumberFormatException e)
				{
					fieldToSortInteger=0;
				}
				break;
			case tString:
				fieldToSortString=o.toString();
				break;
			case tDate:
				if(sfDateFormatter==null)
				{
					//setting up time format by default
					setDateFormat("dd/MM/yyyy");
				}
				try {
					fieldToSortDate=sfDateFormatter.parse(o.toString());
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
					
				break;
			case tDouble:
				try{
					fieldToSortDouble=Double.parseDouble(o.toString());
				}catch(NumberFormatException e)
				{
					fieldToSortDouble=(double) 0;
					return false;
				}
				break;
			}
			return true;
		}
		
		@SuppressWarnings("unused")
		public SortableIndexRow(TypeOfField t)
		{
			type=t;
			sfDateFormatter=null;
		}
		@Override
		public int compareTo(SortableIndexRow other) {
			// TODO Auto-generated method stub
			if(type==TYPE_INTEGER)
			{
				return fieldToSortInteger.compareTo(other.fieldToSortInteger);
			}
			if(type==TYPE_STRING)
			{
				return fieldToSortString.compareTo(other.fieldToSortString);
			}
			if(type==TYPE_DATE)
			{
				return fieldToSortDate.compareTo(other.fieldToSortDate);
			}
			if(type==TYPE_DOUBLE)
			{
				return fieldToSortDouble.compareTo(other.fieldToSortDouble);
			}
			return fieldToSortString.compareTo(other.fieldToSortString);
		}

	}

	@Override
	public List<Object[]> orderTableByFieldIndex(List<Object[]> table, int numField, boolean ascendent) {
		// TODO Auto-generated method stub
		List<Object[]> orderedTable = new ArrayList<Object[]>();

		List<SortableIndexRow> indexTable = new ArrayList<SortableIndexRow>();
		SortableIndexRow newRow;
		int index = 0;
		//System.out.println("entering in Sortable index");

		for (Object[] row : table) {

			newRow = new SortableIndexRow(TypeOfField.tInteger);
			newRow.set(index,row[numField].toString());
			System.out.println("current insertion for " + newRow.index + " : [" + newRow.fieldToSortInteger + "]");
			indexTable.add(newRow);
			index++;
		}
		if (ascendent == false) {
			Collections.sort(indexTable, Collections.reverseOrder());
		} else {
			Collections.sort(indexTable);
		}
		for (SortableIndexRow row : indexTable) {
			System.out.println("current row: " + row.index + " [" + row.fieldToSortInteger + "]");
			orderedTable.add(table.get(row.index));
		}

		return orderedTable;
	}

}
