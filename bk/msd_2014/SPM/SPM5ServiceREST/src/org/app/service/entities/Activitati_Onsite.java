package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Activitati_Onsite extends Activitati_suport
implements Serializable{

//	 Integer idActOnsite;
	String durataActOnsite;
	String obsActOnsite;
	
//	Integer idActivitateSuport;
//	@ManyToOne
//	private Activitati_suport activitatisuport;

//	public Activitati_Onsite(Integer idActOnsite, String durataActOnsite,
//			String obsActOnsite, Integer idActivitateSuport) {
//		super();
//		this.idActOnsite = idActOnsite;
//		this.durataActOnsite = durataActOnsite;
//		this.obsActOnsite = obsActOnsite;
//		this.idActivitateSuport = idActivitateSuport;
//		
//		
//	}

	public Activitati_Onsite() {
		super();
	}

//	public Integer getIdActOnsite() {
//		return idActOnsite;
//	}
//
//	public void setIdActOnsite(Integer idActOnsite) {
//		this.idActOnsite = idActOnsite;
//	}

	public String getDurataActOnsite() {
		return durataActOnsite;
	}

	public void setDurataActOnsite(String durataActOnsite) {
		this.durataActOnsite = durataActOnsite;
	}

	public String getObsActOnsite() {
		return obsActOnsite;
	}

	public void setObsActOnsite(String obsActOnsite) {
		this.obsActOnsite = obsActOnsite;
	}
//
//	public Integer getIdActivitateSuport() {
//		return idActivitateSuport;
//	}
//
//	public void setIdActivitateSuport(Integer idActivitateSuport) {
//		this.idActivitateSuport = idActivitateSuport;
//	}
//
//	public Activitati_suport getActivitatisuport() {
//		return activitatisuport;
//	}
//
//	public void setActivitatisuport(Activitati_suport activitatisuport) {
//		this.activitatisuport = activitatisuport;
//	}
//	
	
}
