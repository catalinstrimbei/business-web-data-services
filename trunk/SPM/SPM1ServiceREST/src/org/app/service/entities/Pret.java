package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.xml.bind.annotation.XmlElement;

@Entity 
public class Pret implements Serializable{
	@Id @GeneratedValue
	private Integer idPret;
	private Integer nrUtilizatori;
	private Double pretPerUtilizator;
	private Double valoare;
	
	@XmlElement
	public Integer getIdPret() {
		return idPret;
	}
	public void setIdPret(Integer idPret) {
		this.idPret = idPret;
	}
	public Integer getNrUtilizatori() {
		return nrUtilizatori;
	}
	public void setNrUtilizatori(Integer nrUtilizatori) {
		this.nrUtilizatori = nrUtilizatori;
	}
	public Double getPretPerUtilizator() {
		return pretPerUtilizator;
	}
	public void setPretPerUtilizator(Double pretPerUtilizator) {
		this.pretPerUtilizator = pretPerUtilizator;
	}
	public Double getValoare() {
		return valoare;
	}
	public void setValoare(Double valoare) {
		this.valoare = valoare;
	}
	
	
}
