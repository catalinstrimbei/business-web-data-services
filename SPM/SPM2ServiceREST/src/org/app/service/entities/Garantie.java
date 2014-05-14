package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Garantie implements Serializable{
	@Id
	@GeneratedValue
	private Integer idGarantie;
	private Date dataInceput;
	private Date dataSfarsit;
	private Date dataInterventie;
	private String observatii;
	
	@OneToMany(/*mappedBy="contract"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	private List<Produs> produs = new ArrayList<Produs>();
	
	public Garantie(Date dataInceput, Date dataSfarsit, Date dataInterventie,
			String observatii) {
		super();
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.dataInterventie = dataInterventie;
		this.observatii = observatii;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idGarantie == null) ? 0 : idGarantie.hashCode());
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
		Garantie other = (Garantie) obj;
		if (idGarantie == null) {
			if (other.idGarantie != null)
				return false;
		} else if (!idGarantie.equals(other.idGarantie))
			return false;
		return true;
	}



	public Garantie() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Date getDataInceput() {
		return dataInceput;
	}

	public void setDataInceput(Date dataInceput) {
		this.dataInceput = dataInceput;
	}

	public Date getDataSfarsit() {
		return dataSfarsit;
	}

	public void setDataSfarsit(Date dataSfarsit) {
		this.dataSfarsit = dataSfarsit;
	}

	public Date getDataInterventie() {
		return dataInterventie;
	}

	public void setDataInterventie(Date dataInterventie) {
		this.dataInterventie = dataInterventie;
	}

	public String getObservatii() {
		return observatii;
	}

	public void setObservatii(String observatii) {
		this.observatii = observatii;
	}
	
	
	
	
	
	
}
