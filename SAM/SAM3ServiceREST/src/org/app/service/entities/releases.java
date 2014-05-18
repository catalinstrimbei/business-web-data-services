package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import sun.util.calendar.BaseCalendar.Date;

@Entity
public class releases {
	@Id
	@GeneratedValue
	private int IdRelease;
	private Date ProdDate;
	private Date ReleaseDate;
	private int SP;
	@ManyToOne
	public tasks m_Tasks;

	public releases(){

	}

	public int getIdRelease() {
		return IdRelease;
	}

	public void setIdRelease(int idRelease) {
		IdRelease = idRelease;
	}

	public Date getProdDate() {
		return ProdDate;
	}

	public void setProdDate(Date prodDate) {
		ProdDate = prodDate;
	}

	public Date getReleaseDate() {
		return ReleaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		ReleaseDate = releaseDate;
	}

	public int getSP() {
		return SP;
	}

	public void setSP(int sP) {
		SP = sP;
	}

	public tasks getM_Tasks() {
		return m_Tasks;
	}

	public void setM_Tasks(tasks m_Tasks) {
		this.m_Tasks = m_Tasks;
	}

}
