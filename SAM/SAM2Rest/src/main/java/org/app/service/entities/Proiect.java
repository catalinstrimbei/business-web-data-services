package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import static javax.persistence.TemporalType.DATE;

@Entity
public class Proiect {
	
	@Id
	private Integer nrProiect;
	
	private String numeProiect;
	
	@Temporal(DATE)
	private Date dataStartProiect;
	@Temporal(DATE)
	private Date dataFinalizareProiect;
	
	@ManyToOne
	private Echipa echipa;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nrProiect == null) ? 0 : nrProiect.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proiect other = (Proiect) obj;
		if (nrProiect == null) {
			if (other.nrProiect != null)
				return false;
		} else if (!nrProiect.equals(other.nrProiect))
			return false;
		return true;
	}

	public Integer getNrProiect() {
		return nrProiect;
	}

	public void setNrProiect(Integer nrProiect) {
		this.nrProiect = nrProiect;
	}

	public String getNumeProiect() {
		return numeProiect;
	}

	public void setNumeProiect(String numeProiect) {
		this.numeProiect = numeProiect;
	}

	public Date getDataStartProiect() {
		return dataStartProiect;
	}

	public void setDataStartProiect(Date dataStartProiect) {
		this.dataStartProiect = dataStartProiect;
	}

	public Date getDataFinalizareProiect() {
		return dataFinalizareProiect;
	}

	public void setDataFinalizareProiect(Date dataFinalizareProiect) {
		this.dataFinalizareProiect = dataFinalizareProiect;
	}

	public Echipa getEchipa() {
		return echipa;
	}

	public void setEchipa(Echipa echipa) {
		this.echipa = echipa;
	}

	public Proiect(Integer nrProiect, String numeProiect,
			Date dataStartProiect, Date dataFinalizareProiect, Echipa echipa) {
		super();
		this.nrProiect = nrProiect;
		this.numeProiect = numeProiect;
		this.dataStartProiect = dataStartProiect;
		this.dataFinalizareProiect = dataFinalizareProiect;
		this.echipa = echipa;
	}

	public Proiect() {
		super();
	}
	

}
