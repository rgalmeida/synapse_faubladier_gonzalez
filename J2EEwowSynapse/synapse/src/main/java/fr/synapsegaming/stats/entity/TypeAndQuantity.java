package fr.synapsegaming.stats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import fr.synapsegaming.user.entity.Clazz;


public class TypeAndQuantity {
	String name;
	String referredEntity;
	Clazz clazz;
	long quantity;
	/**
     * Technical unique identifier of a User
     */

    
    
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getReferredEntity() {
		return referredEntity;
	}
	public void setReferredEntity(String referredEntity) {
		this.referredEntity = referredEntity;
	}

}
