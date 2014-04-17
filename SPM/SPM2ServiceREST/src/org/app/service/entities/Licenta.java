package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Licenta {
	@Id
	@GeneratedValue
	private Integer idLicenta;
	private String tipLicenta;
	private Date dataInceput;
	private Date dataSfarsit;
	private Double pret;
	
	public Licenta(String tipLicenta, Date dataInceput, Date dataSfarsit,
			Double pret) {
		super();
		this.tipLicenta = tipLicenta;
		this.dataInceput = dataInceput;
		this.dataSfarsit = dataSfarsit;
		this.pret = pret;
	}
	


	public Licenta() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLicenta == null) ? 0 : idLicenta.hashCode());
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
		Licenta other = (Licenta) obj;
		if (idLicenta == null) {
			if (other.idLicenta != null)
				return false;
		} else if (!idLicenta.equals(other.idLicenta))
			return false;
		return true;
	}



	public String getTipLicenta() {
		return tipLicenta;
	}

	public void setTipLicenta(String tipLicenta) {
		this.tipLicenta = tipLicenta;
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

	public Double getPret() {
		return pret;
	}

	public void setPret(Double pret) {
		this.pret = pret;
	}
	
	
	

}
