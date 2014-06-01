package org.app.service.entities;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.service.rest.AtomLink;

import com.sun.xml.internal.txw2.annotation.XmlElement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;


@XmlRootElement(name="comunication")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Client implements Serializable{
	
	  @Id @GeneratedValue 
	  private Integer clientId;
	  private String cnp;
	  private String firstName;
	  private String lastName;
	  
	  public Client() {
		super();
	}

	@Temporal(TemporalType.DATE)
	  private Date dateOfBirth;
	  private String adress;
	  private String city;
	  private String phoneNumber;
	  
	  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	  private List<Comunication> comunication=new ArrayList<Comunication>();
	  
	  public Client(Integer clientId, String firstName, Date dateOfBirth) {
		super();
		this.clientId = clientId;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public Client toDTO(){
		  Client clientDTO = new Client(clientId, firstName, dateOfBirth);
			return clientDTO;
		}
	
   public static String BASE_URL = "http://localhost:8081/SPM5ServiceREST/produse/";
	
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getClientId();
		return new AtomLink(restUrl, "get_clinti");
	}
	
 /* public static Client toDTOAggregate(Client client){
			if (client == null)
				return null;
			Client clientDTO = client.toDTO();
			List<Comunication> comunicationDTO = Comunication.toDTOList(client.getActivitati());
			clientDTO.setActivitati(activitati_suportDTO);
			return clientDTO;
		}
	public Client() {
	super();
}*/
	@Override
	public String toString() {
		return "Clienti [idClient=" + clientId + ", NumeClient="
				+ firstName + ", Data of birthday =" + dateOfBirth + "]";
	}
	public static Collection<Client> toDTOs(Collection<Client> lst) {
		List<Client> clients = new ArrayList<>();
		for(Client c: lst){
			clients.add(c.toDTO());
		}
		return clients;
	}

	@XmlElement
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	@XmlElement
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Comunication> getComunication() {
			return comunication;
		}
	public void setComunication(List<Comunication> comunication) {
			this.comunication = comunication;
		}
	  	
}
