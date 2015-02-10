package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Activitati_remote extends Activitati_suport
implements Serializable{
//	 Integer idActivitateRemote;
	String obsAcivitateSuport;
	String durataActivitateSuport;
	
//	Integer idActivitateSuport;
//	@ManyToOne
//	private Activitati_suport activitatiSuport;

//	public Activitati_remote(Integer idActivitateRemote,
//			String obsAcivitateSuport, String durataActivitateSuport,
//			Integer idActivitateSuport) {
//		super();
//		this.idActivitateRemote = idActivitateRemote;
//		this.obsAcivitateSuport = obsAcivitateSuport;
//		this.durataActivitateSuport = durataActivitateSuport;
//		this.idActivitateSuport = idActivitateSuport;
//	}

	public Activitati_remote() {
		super();
	}

//	public Integer getIdActivitateRemote() {
//		return idActivitateRemote;
//	}

//	public void setIdActivitateRemote(Integer idActivitateRemote) {
//		this.idActivitateRemote = idActivitateRemote;
//	}

	public String getObsAcivitateSuport() {
		return obsAcivitateSuport;
	}

	public void setObsAcivitateSuport(String obsAcivitateSuport) {
		this.obsAcivitateSuport = obsAcivitateSuport;
	}

	public String getDurataActivitateSuport() {
		return durataActivitateSuport;
	}

	public void setDurataActivitateSuport(String durataActivitateSuport) {
		this.durataActivitateSuport = durataActivitateSuport;
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
//	public Activitati_suport getActivitatiSuport() {
//		return activitatiSuport;
//	}
//
//	public void setActivitatiSuport(Activitati_suport activitatiSuport) {
//		this.activitatiSuport = activitatiSuport;
//	}
	
	
}
