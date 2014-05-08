package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Responsabili implements Serializable{

	@Id
	@GeneratedValue Integer idResponsabil;
	String NumeResponsabil;
	String PrenumeResponsabil;
	String EmailResponsabil;
	Integer TelefonResponsabil;
	
	@OneToMany(mappedBy = "responsabili")
	private List<Activitati_suport> activitatiSP = new ArrayList<Activitati_suport>();
	@OneToMany(mappedBy = "Resp")
	private List<Repartizare_cerere> repartizare = new ArrayList<Repartizare_cerere>();
	
	
	public Integer getIdResponsabil() {
		return idResponsabil;
	}
	public void setIdResponsabil(Integer idResponsabil) {
		this.idResponsabil = idResponsabil;
	}
	public String getNumeResponsabil() {
		return NumeResponsabil;
	}
	public void setNumeResponsabil(String numeResponsabil) {
		NumeResponsabil = numeResponsabil;
	}
	public String getPrenumeResponsabil() {
		return PrenumeResponsabil;
	}
	public void setPrenumeResponsabil(String prenumeResponsabil) {
		PrenumeResponsabil = prenumeResponsabil;
	}
	public String getEmailResponsabil() {
		return EmailResponsabil;
	}
	public void setEmailResponsabil(String emailResponsabil) {
		EmailResponsabil = emailResponsabil;
	}
	public Integer getTelefonResponsabil() {
		return TelefonResponsabil;
	}
	public void setTelefonResponsabil(Integer telefonResponsabil) {
		TelefonResponsabil = telefonResponsabil;
	}
	public Responsabili(Integer idResponsabil, String numeResponsabil,
			String prenumeResponsabil, String emailResponsabil,
			Integer telefonResponsabil) {
		super();
		this.idResponsabil = idResponsabil;
		NumeResponsabil = numeResponsabil;
		PrenumeResponsabil = prenumeResponsabil;
		EmailResponsabil = emailResponsabil;
		TelefonResponsabil = telefonResponsabil;
	}
	public Responsabili() {
		super();
	}
	public List<Activitati_suport> getActivitatiSP() {
		return activitatiSP;
	}
	public void setActivitatiSP(List<Activitati_suport> activitatiSP) {
		this.activitatiSP = activitatiSP;
	}
	public List<Repartizare_cerere> getRepartizare() {
		return repartizare;
	}
	public void setRepartizare(List<Repartizare_cerere> repartizare) {
		this.repartizare = repartizare;
	}
	
}
