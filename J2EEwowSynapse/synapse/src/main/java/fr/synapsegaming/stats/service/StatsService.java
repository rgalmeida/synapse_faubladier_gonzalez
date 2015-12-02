package fr.synapsegaming.stats.service;

import java.util.List;

import fr.synapsegaming.user.entity.User;

public interface StatsService {
	
	
	
	
	public List<Object> listTopClassPlayed(int n);

	public List<Object> getTopRacesPlayed(int n);
	
	public List<Object> listTopSpecializationsPlayed(int n);
    
    public List<User> listUsersWithDefaultAvatar();

	public List<Object[]>listUsersActivityScore(String dateBegin,String dateEnd, int numResults,int index);
	
	public List<Object[]>orderTableByFieldIndex(List<Object[]> table,int numField,boolean ascendent);
	

}
