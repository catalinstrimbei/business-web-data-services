package org.app.scrum.team;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private Integer idEchipa;
	private Specializare specializare;
	private String competente;
	private List<Member> membri = new ArrayList<Member>();
	private TeamLeader liderEchipa;
	
	// properties from bean accessors
	public TeamLeader getLiderEchipa() {
		return liderEchipa;
	}
	public void setLiderEchipa(TeamLeader liderEchipa) {
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
	public List<Member> getMembri() {
		return membri;
	}
	public void setMembri(List<Member> membri) {
		this.membri = membri;
	}
	public Team(Integer idEchipa, Specializare specializare, String competente) {
		super();
		this.idEchipa = idEchipa;
		this.specializare = specializare;
		this.competente = competente;
	}
	public Team() {
		super();
	}
	
	public Team(Integer idEchipa, Specializare specializare) {
		super();
		this.idEchipa = idEchipa;
		this.specializare = specializare;
	}
	// polimorfism parametrizare
	public void adaugaMembru(Member membru){
		this.membri.add(membru);
	}
	
	public enum Specializare {
		BACKEND, FRONTEND, DATABASE;
	}	
}

