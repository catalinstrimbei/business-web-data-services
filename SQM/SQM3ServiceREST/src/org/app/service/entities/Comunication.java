package org.app.service.entities;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static javax.persistence.CascadeType.ALL;
import java.util.*;
import java.io.Serializable;

enum ComunicationType{
	  phone,
	  fax,
	  email,
	  facetoface
}

@XmlRootElement(name="suport")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
public class Comunication  implements Serializable{
	

	@Id @GeneratedValue 
	private Integer comunicationId;  
	private ComunicationType comunicationType;
	@Temporal(TemporalType.DATE)
    private Date dateOfReporting;	
	private Integer productId;
	
	@ManyToOne
	private Client client;
	
	
	
	@OneToOne
    private Complaints complaint;
	
	public Complaints getComplaint() {
	    return complaint;
	}

	public void setComplaint(Complaints complaint) {
	   this.complaint = complaint;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	
	
	@OneToOne
	private Feedback feedback;
	
	public Integer getComunicationId() {
		return comunicationId;
	}

	public void setComunicationId(Integer comunicationId) {
		this.comunicationId = comunicationId;
	}

	public ComunicationType getComunicationType() {
		return comunicationType;
	}

	public void setComunicationType(ComunicationType comunicationType) {
		this.comunicationType = comunicationType;
	}

	public Date getDateOfReporting() {
		return dateOfReporting;
	}

	public void setDateOfReporting(Date dateOfReporting) {
		this.dateOfReporting = dateOfReporting;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
