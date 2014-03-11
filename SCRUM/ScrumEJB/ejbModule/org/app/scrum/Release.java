package org.app.scrum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.GeneratedValue;


@Entity
public class Release {
	@Id
	@GeneratedValue
	private Integer idRelease;
	private String numeCod; // NEW born
	private String indicativ; // vers 1.1
	private String descriere;
	
	@Temporal(TemporalType.DATE)
	private Date dataPublicare; // dataEstimarePublicare
	
	// Added
	@ManyToOne
	private Proiect proiect;
	
	@OneToMany(cascade = ALL)
	private List<Cerinta> cerinte = new ArrayList<>();
	
	public Integer getIdRelease() {
		return idRelease;
	}
	public void setIdRelease(Integer idRelease) {
		this.idRelease = idRelease;
	}
	public String getNumeCod() {
		return numeCod;
	}
	public void setNumeCod(String numeCod) {
		this.numeCod = numeCod;
	}
	
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public Date getDataPublicare() {
		return dataPublicare;
	}
	public void setDataPublicare(Date dataPublicare) {
		this.dataPublicare = dataPublicare;
	}
	
	// Added
	public String getIndicativ() {
		return indicativ;
	}
	public void setIndicativ(String indicativ) {
		this.indicativ = indicativ;
	}
	public Proiect getProiect() {
		return proiect;
	}
	public void setProiect(Proiect proiect) {
		this.proiect = proiect;
	}
	
	public List<Cerinta> getCerinte() {
		return cerinte;
	}
	public void setCerinte(List<Cerinta> cerinte) {
		this.cerinte = cerinte;
	}
	public Release() {
		super();
	}
	public Release(Integer idRelease, String numeCod, String indicativ,
			String descriere, Date dataPublicare, Proiect proiect) {
		super();
		this.idRelease = idRelease;
		this.numeCod = numeCod;
		this.indicativ = indicativ;
		this.descriere = descriere;
		this.dataPublicare = dataPublicare;
		this.proiect = proiect;
	}
	
	
	public Release(Integer idRelease, String indicativ, Date dataPublicare,
			Proiect proiect) {
		super();
		this.idRelease = idRelease;
		this.indicativ = indicativ;
		this.dataPublicare = dataPublicare;
		this.proiect = proiect;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idRelease == null) ? 0 : idRelease.hashCode());
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
		Release other = (Release) obj;
		if (idRelease == null) {
			if (other.idRelease != null)
				return false;
		} else if (!idRelease.equals(other.idRelease))
			return false;
		return true;
	}
	
	
}
