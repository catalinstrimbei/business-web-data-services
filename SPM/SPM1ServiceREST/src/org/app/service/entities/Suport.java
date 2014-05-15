package org.app.service.entities;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

@XmlRootElement(name="suport")
@XmlAccessorType(XmlAccessType.NONE)
@Entity 
public class Suport implements Serializable{

	@Id @GeneratedValue
	private Integer idSuport;
	private String denSuport;
	
	@ManyToOne
	private Serviciu serviciu;
	
	@XmlElement
	public Integer getIdSuport() {
		return idSuport;
	}
	public void setIdSuport(Integer idSuport) {
		this.idSuport = idSuport;
	}
	public String getDenSuport() {
		return denSuport;
	}
	public void setDenSuport(String denSuport) {
		this.denSuport = denSuport;
	}
	
	
	public Serviciu getServiciu() {
		return serviciu;
	}
	public void setServiciu(Serviciu serviciu) {
		this.serviciu = serviciu;
	}
///????????????????????????????????????????tb???

	public static String BASE_URL = Produs.BASE_URL;
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getServiciu().getIdServiciu() + "/suporturi/" + this.getIdSuport();
			return new AtomLink(restUrl, "get-suport");
	}
}
