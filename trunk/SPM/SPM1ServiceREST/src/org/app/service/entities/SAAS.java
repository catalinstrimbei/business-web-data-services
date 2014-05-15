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

@XmlRootElement(name="SAAS")
@XmlAccessorType(XmlAccessType.NONE)
@Entity 
@Inheritance(strategy = JOINED)
public class SAAS implements Serializable{

	@Id @GeneratedValue
	private Integer idSAAS;
	private String denSAAS;
	
	@ManyToOne
	private Serviciu serviciu;
	
	@XmlElement
	public Integer getIdSAAS() {
		return idSAAS;
	}
	public void setIdSAAS(Integer idSAAS) {
		this.idSAAS = idSAAS;
	}
	public String getDenSAAS() {
		return denSAAS;
	}
	public void setDenSAAS(String denSAAS) {
		this.denSAAS = denSAAS;
	}
	
	public Serviciu getServiciu() {
		return serviciu;
	}
	public void setServiciu(Serviciu serviciu) {
		this.serviciu = serviciu;
	}

	public static String BASE_URL = Produs.BASE_URL;
	@XmlElement(name= "link")
	public AtomLink getLink() throws Exception{
		String restUrl = BASE_URL + this.getServiciu().getIdServiciu() + "/saas-uri/" + this.getIdSAAS();
			return new AtomLink(restUrl, "get-saas");
	}
	
}
