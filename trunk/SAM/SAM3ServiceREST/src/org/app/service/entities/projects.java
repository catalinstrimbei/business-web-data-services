package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class projects {
	@Id
	@GeneratedValue
	private char ProjectId = 1000;
	private int ProjectManager;
	private char ProjetcTitle;
	private char StartDate;
	@ManyToOne
	public releases m_Releases;

	public projects(){

	}

	public void finalize() throws Throwable {

	}

	public char getProjectId() {
		return ProjectId;
	}

	public void setProjectId(char projectId) {
		ProjectId = projectId;
	}

	public int getProjectManager() {
		return ProjectManager;
	}

	public void setProjectManager(int projectManager) {
		ProjectManager = projectManager;
	}

	public char getProjetcTitle() {
		return ProjetcTitle;
	}

	public void setProjetcTitle(char projetcTitle) {
		ProjetcTitle = projetcTitle;
	}

	public char getStartDate() {
		return StartDate;
	}

	public void setStartDate(char startDate) {
		StartDate = startDate;
	}

	public releases getM_Releases() {
		return m_Releases;
	}

	public void setM_Releases(releases m_Releases) {
		this.m_Releases = m_Releases;
	}

}
