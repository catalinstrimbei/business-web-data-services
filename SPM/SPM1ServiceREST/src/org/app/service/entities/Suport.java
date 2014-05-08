package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity 
public class Suport implements Serializable{

	@Id @GeneratedValue
	private Integer idSuport;
	private String denSuport;
	public Integer getIdSuport() {
		return idSuport;
	}
	public void setIdSuport(Integer idSuport) {
		this.idSuport = idSuport;
	}
	public String getDenSuport() {
		return denSuport;
	}
	public void setDenSuport(String denSuport) {
		this.denSuport = denSuport;
	}
	
}
