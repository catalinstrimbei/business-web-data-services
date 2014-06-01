package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Team {

	private String competence;
	@Id@GeneratedValue
	private int idTeam;
	private Specialization m_Specialization;

	public Team(){

	}

	public void finalize() throws Throwable {

	}

	public Team(String competence, int idTeam, Specialization m_Specialization) {
		this.competence = competence;
		this.idTeam = idTeam;
		this.m_Specialization = m_Specialization;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public int getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(int idTeam) {
		this.idTeam = idTeam;
	}

	public Specialization getM_Specialization() {
		return m_Specialization;
	}

	public void setM_Specialization(Specialization m_Specialization) {
		this.m_Specialization = m_Specialization;
	}
	

}