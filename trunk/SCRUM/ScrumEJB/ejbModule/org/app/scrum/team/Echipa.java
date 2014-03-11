package org.app.scrum.team;

import java.util.ArrayList;
import java.util.List;

public class Echipa {
	private Integer idEchipa;
	private Specializare specializare;
	private String competente;
	private List<Membru> membri = new ArrayList<Membru>();
	private LiderEchipa liderEchipa;
	
	// properties from bean accessors
	public LiderEchipa getLiderEchipa() {
		return liderEchipa;
	}
	public void setLiderEchipa(LiderEchipa liderEchipa) {
		this.liderEchipa = liderEchipa;
	}
	public Integer getIdEchipa() {
		return idEchipa;
	}
	public void setIdEchipa(Integer idEchipa) {
		this.idEchipa = idEchipa;
	}
	public Specializare getSpecializare() {
		return specializare;
	}
	public void setSpecializare(Specializare specializare) {
		this.specializare = specializare;
	}
	public String getCompetente() {
		return competente;
	}
	public void setCompetente(String competente) {
		this.competente = competente;
	}
	public List<Membru> getMembri() {
		return membri;
	}
	public void setMembri(List<Membru> membri) {
		this.membri = membri;
	}
	public Echipa(Integer idEchipa, Specializare specializare, String competente) {
		super();
		this.idEchipa = idEchipa;
		this.specializare = specializare;
		this.competente = competente;
	}
	public Echipa() {
		super();
	}
	
	public Echipa(Integer idEchipa, Specializare specializare) {
		super();
		this.idEchipa = idEchipa;
		this.specializare = specializare;
	}
	// polimorfism parametrizare
	public void adaugaMembru(Membru membru){
		this.membri.add(membru);
	}
	
	public enum Specializare {
		BACKEND, FRONTEND, DATABASE;
	}	
}

