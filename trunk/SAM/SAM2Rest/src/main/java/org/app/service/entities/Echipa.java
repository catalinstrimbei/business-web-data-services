package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
public class Echipa {
	
	@Id
	private Integer idEchipa;
	
	@Enumerated (EnumType.STRING)
	private SpecializareEchipa specializareechipa;

	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "echipa")
	private List<Proiect> proiecte = new ArrayList<>();
	
	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "echipa")
	private List<TrainingEchipe> trainingEchipeE = new ArrayList<>();

	@OneToMany(cascade=ALL,fetch=EAGER, orphanRemoval = false, mappedBy = "echipa")
	private List<MembruEchipa> membruEchipaE = new ArrayList<>();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEchipa == null) ? 0 : idEchipa.hashCode());
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
		Echipa other = (Echipa) obj;
		if (idEchipa == null) {
			if (other.idEchipa != null)
				return false;
		} else if (!idEchipa.equals(other.idEchipa))
			return false;
		return true;
	}

	public Integer getIdEchipa() {
		return idEchipa;
	}

	public void setIdEchipa(Integer idEchipa) {
		this.idEchipa = idEchipa;
	}

	public List<Proiect> getProiecte() {
		return proiecte;
	}

	public void setProiecte(List<Proiect> proiecte) {
		this.proiecte = proiecte;
	}

	public SpecializareEchipa getSpecializareechipa() {
		return specializareechipa;
	}

	public void setSpecializareechipa(SpecializareEchipa specializareechipa) {
		this.specializareechipa = specializareechipa;
	}

	public Echipa(Integer idEchipa, List<Proiect> proiecte,
			SpecializareEchipa specializareechipa) {
		super();
		this.idEchipa = idEchipa;
		this.proiecte = proiecte;
		this.specializareechipa = specializareechipa;
	}

	public Echipa() {
		super();
	}
	
	
}
