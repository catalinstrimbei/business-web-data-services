package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CategorieCompetenta {
	
	@Id
	private Integer idCategorie;
	
	private String numeCategorie;
	
	private String rol;
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "categoriecomp")
	private List<Competente> competente = new ArrayList<>();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCategorie == null) ? 0 : idCategorie.hashCode());
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
		CategorieCompetenta other = (CategorieCompetenta) obj;
		if (idCategorie == null) {
			if (other.idCategorie != null)
				return false;
		} else if (!idCategorie.equals(other.idCategorie))
			return false;
		return true;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNumeCategorie() {
		return numeCategorie;
	}

	public void setNumeCategorie(String numeCategorie) {
		this.numeCategorie = numeCategorie;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<Competente> getCompetente() {
		return competente;
	}

	public void setCompetente(List<Competente> competente) {
		this.competente = competente;
	}

	public CategorieCompetenta(Integer idCategorie, String numeCategorie,
			String rol, List<Competente> competente) {
		super();
		this.idCategorie = idCategorie;
		this.numeCategorie = numeCategorie;
		this.rol = rol;
		this.competente = competente;
	}

	public CategorieCompetenta() {
		super();
	}

	
	
}
