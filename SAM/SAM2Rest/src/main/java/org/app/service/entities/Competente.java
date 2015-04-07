package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Competente {
	
	@Id
	private Integer idCompetenta;
	private String numeCompetenta;
	
	@ManyToOne
	private CategorieCompetenta categoriecomp;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCompetenta == null) ? 0 : idCompetenta.hashCode());
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
		Competente other = (Competente) obj;
		if (idCompetenta == null) {
			if (other.idCompetenta != null)
				return false;
		} else if (!idCompetenta.equals(other.idCompetenta))
			return false;
		return true;
	}

	public Integer getIdCompetenta() {
		return idCompetenta;
	}

	public void setIdCompetenta(Integer idCompetenta) {
		this.idCompetenta = idCompetenta;
	}

	public String getNumeCompetenta() {
		return numeCompetenta;
	}

	public void setNumeCompetenta(String numeCompetenta) {
		this.numeCompetenta = numeCompetenta;
	}

	public CategorieCompetenta getCategoriecomp() {
		return categoriecomp;
	}

	public void setCategoriecomp(CategorieCompetenta categoriecomp) {
		this.categoriecomp = categoriecomp;
	}

	public Competente(Integer idCompetenta, String numeCompetenta,
			CategorieCompetenta categoriecomp) {
		super();
		this.idCompetenta = idCompetenta;
		this.numeCompetenta = numeCompetenta;
		this.categoriecomp = categoriecomp;
	}

	public Competente() {
		super();
	}

	
	
}
