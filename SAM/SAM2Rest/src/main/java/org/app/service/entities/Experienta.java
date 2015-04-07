package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import static javax.persistence.TemporalType.DATE;

@Entity
public class Experienta {
	
	@Id
	private Integer codExperienta;
	
	private String numeProiect;
	private String descriereProiect;
	private String rol;
	
	@Temporal(DATE)
	private Date dataInceput;
	
	@Temporal(DATE)
	private Date dataSfarsit;
	
	@ManyToOne
	private MembruOrganizatie membruorg;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codExperienta == null) ? 0 : codExperienta.hashCode());
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
		Experienta other = (Experienta) obj;
		if (codExperienta == null) {
			if (other.codExperienta != null)
				return false;
		} else if (!codExperienta.equals(other.codExperienta))
			return false;
		return true;
	}

	public Integer getCodExperienta() {
		return codExperienta;
	}

	public void setCodExperienta(Integer codExperienta) {
		this.codExperienta = codExperienta;
	}

	public String getNumeProiect() {
		return numeProiect;
	}

	public void setNumeProiect(String numeProiect) {
		this.numeProiect = numeProiect;
	}

	public String getDescriereProiect() {
		return descriereProiect;
	}

	public void setDescriereProiect(String descriereProiect) {
		this.descriereProiect = descriereProiect;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
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

	public MembruOrganizatie getMembruorg() {
		return membruorg;
	}

	public void setMembruorg(MembruOrganizatie membruorg) {
		this.membruorg = membruorg;
	}

	public Experienta(Integer codExperienta, String numeProiect,
			String descriereProiect, String rol, Date dataInceput,
			Date dataSfarsit, MembruOrganizatie membruorg) {
		super();
		this.codExperienta = codExperienta;
		this.numeProiect = numeProiect;
		this.descriereProiect = descriereProiect;
		this.rol = rol;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.membruorg = membruorg;
	}

	public Experienta() {
		super();
	}

	
	
}
