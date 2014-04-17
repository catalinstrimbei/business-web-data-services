package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Activitati_suport {
@Id
 Integer IdActivitateSuport;
	Date dataActivitateSuport;
	String tipActivitateSuport;
	String obsActivitateSuport;
	
	
//	private Integer idProdus;
//	Integer idResponsabil;
	
	 @ManyToOne
	 private Produse produse;
	 
//	 @OneToMany(mappedBy = "activitatiSuport")
//	private List <Activitati_remote> actRemote = new ArrayList<Activitati_remote>();
//	
//	@OneToMany
//	private List <Activitati_Onsite> actOnsite = new ArrayList<Activitati_Onsite>();
	 
	@ManyToOne
	private Responsabili responsabili;
	 
	public Integer getIdActivitateSuport() {
		return IdActivitateSuport;
	}
	public void setIdActivitateSuport(Integer idActivitateSuport) {
		IdActivitateSuport = idActivitateSuport;
	}
	public Date getDataActivitateSuport() {
		return dataActivitateSuport;
	}
	public void setDataActivitateSuport(Date dataActivitateSuport) {
		this.dataActivitateSuport = dataActivitateSuport;
	}
	public String getTipActivitateSuport() {
		return tipActivitateSuport;
	}
	public void setTipActivitateSuport(String tipActivitateSuport) {
		this.tipActivitateSuport = tipActivitateSuport;
	}
	public String getObsActivitateSuport() {
		return obsActivitateSuport;
	}
	public void setObsActivitateSuport(String obsActivitateSuport) {
		this.obsActivitateSuport = obsActivitateSuport;
	}
//	public Integer getIdProdus() {
//		return idProdus;
//	}
//	public void setIdProdus(Integer idProdus) {
//		this.idProdus = idProdus;
//	}
//	public Integer getIdResponsabil() {
//		return idResponsabil;
//	}
//	public void setIdResponsabil(Integer idResponsabil) {
//		this.idResponsabil = idResponsabil;
//	}
//	public Activitati_suport(Integer idActivitateSuport,
//			Date dataActivitateSuport, String tipActivitateSuport,
//			String obsActivitateSuport, Integer idProdus, Integer idResponsabil) {
//		super();
//		IdActivitateSuport = idActivitateSuport;
//		this.dataActivitateSuport = dataActivitateSuport;
//		this.tipActivitateSuport = tipActivitateSuport;
//		this.obsActivitateSuport = obsActivitateSuport;
//		this.idProdus = idProdus;
//		this.idResponsabil = idResponsabil;
//	}
	public Activitati_suport() {
		super();
	}
//	public List <Activitati_remote> getActRemote() {
//		return actRemote;
//	}
//	public void setActRemote(List <Activitati_remote> actRemote) {
//		this.actRemote = actRemote;
//	}
//	public List <Activitati_Onsite> getActOnsite() {
//		return actOnsite;
//	}
//	public void setActOnsite(List <Activitati_Onsite> actOnsite) {
//		this.actOnsite = actOnsite;
//	}
	public Responsabili getResponsabili() {
		return responsabili;
	}
	public void setResponsabili(Responsabili responsabili) {
		this.responsabili = responsabili;
	}

}
