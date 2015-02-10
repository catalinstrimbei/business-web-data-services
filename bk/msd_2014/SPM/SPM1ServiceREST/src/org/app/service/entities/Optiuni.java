package org.app.service.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.xml.bind.annotation.XmlElement;

import org.app.service.rest.AtomLink;

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
	@XmlElement
	public String getDenOptiune() {
		return denOptiune;
	}
	public void setDenOptiune(String denOptiune) {
		this.denOptiune = denOptiune;
	}
	@XmlElement
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	
//	public static String BASE_URL = Produs.BASE_URL;
//	@XmlElement(name= "link")
//	public AtomLink getLink() throws Exception{
//		String restUrl = BASE_URL + this.+"/editii/"+this.getIdEditie();
//			return new AtomLink(restUrl, "get-editie");
//			
//	}
	
}
