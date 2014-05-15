package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.xml.bind.annotation.XmlElement;

import static javax.persistence.InheritanceType.JOINED;

@Entity 
public class Optiuni implements Serializable {

	@Id @GeneratedValue
	private Integer idOptiune;
	private String denOptiune;
	private String descriere;
	@XmlElement
	public Integer getIdOptiune() {
		return idOptiune;
	}
	public void setIdOptiune(Integer idOptiune) {
		this.idOptiune = idOptiune;
	}
	public String getDenOptiune() {
		return denOptiune;
	}
	public void setDenOptiune(String denOptiune) {
		this.denOptiune = denOptiune;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
	
	
}
