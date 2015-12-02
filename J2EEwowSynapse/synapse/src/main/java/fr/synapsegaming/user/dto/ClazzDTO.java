package fr.synapsegaming.user.dto;

import fr.synapsegaming.user.entity.Clazz;

public class ClazzDTO {
	
	private Clazz clazz;
	
	private Integer nbUsers;

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Integer getNbUsers() {
		return nbUsers;
	}

	public void setNbUsers(Integer nbUsers) {
		this.nbUsers = nbUsers;
	}
	
}
