package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//import com.sun.xml.internal.txw2.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
//@XmlRootElement(name="client")
//@XmlAccessorType(XmlAccessType.NONE)

@Entity
//@MappedSuperclass
public class Client implements Serializable{
	public static String BASE_URL= "http://localhost:8080/SPM2ServiceREST/clienti/";
	@Id
	@GeneratedValue
	private Integer idClient;
	private String nume;
	private String adresa;
	private String telefon;
	private String email;
	private String contBanca;
	private Integer CUI;
	private String nrRegCom;
	@OneToMany(mappedBy="client" ,cascade=ALL, fetch= FetchType.EAGER, orphanRemoval=false)
	private List<Contract> contracte = new ArrayList<>();
	
	public Client toDTO(){
		Client clientDTO= new Client (idClient, nume, adresa, telefon, email,contBanca, CUI, nrRegCom);
		return clientDTO;
		
	}
	public static Client toDTOAggregate(Client client){
		if(client==null)
			return null;
		Client clientDTO=client.toDTO();
		List<Contract> contracteDTO= Contract.toDTOList(client.getContracte());
		clientDTO.setContracte(contracteDTO);
		return clientDTO;
	}
	public static Client[] toDTOList(Collection<Client> clienti){
		List<Client> clientDTOList= new ArrayList<>();
		for(Client cl:clienti){
			clientDTOList.add(cl.toDTO());
		} 
		return clientDTOList.toArray(new Client[0]);
	}
	
	@XmlElement(name="link")
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdClient();
		return new AtomLink(restUrl, "get-client");
	}
	public static Collection<Client> toDTOs(Collection<Client> collection) {
		// TODO Auto-generated method stub
		return null;
	}
	@XmlElementWrapper(name="contracte") @XmlElement(name="contract")
	public List<Contract> getContracte() {
		return contracte;
	}

	public Client(List<Contract> contract) {
		super();
		this.contracte = contract;
	}


	public Client(Integer idClient,String nume, String adresa, String telefon, String email, String contBanca,Integer CUI, String nrRegCom) {
		super();
		this.idClient= idClient;
		this.nume= nume;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
		this.contBanca = contBanca;
		this.CUI= CUI;
		this.nrRegCom=nrRegCom;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CUI == null) ? 0 : CUI.hashCode());
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result
				+ ((contBanca == null) ? 0 : contBanca.hashCode());
		result = prime * result
				+ ((contracte == null) ? 0 : contracte.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((idClient == null) ? 0 : idClient.hashCode());
		result = prime * result
				+ ((nrRegCom == null) ? 0 : nrRegCom.hashCode());
		result = prime * result + ((nume == null) ? 0 : nume.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
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
		Client other = (Client) obj;
		if (CUI == null) {
			if (other.CUI != null)
				return false;
		} else if (!CUI.equals(other.CUI))
			return false;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
			return false;
		if (contBanca == null) {
			if (other.contBanca != null)
				return false;
		} else if (!contBanca.equals(other.contBanca))
			return false;
		if (contracte == null) {
			if (other.contracte != null)
				return false;
		} else if (!contracte.equals(other.contracte))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idClient == null) {
			if (other.idClient != null)
				return false;
		} else if (!idClient.equals(other.idClient))
			return false;
		if (nrRegCom == null) {
			if (other.nrRegCom != null)
				return false;
		} else if (!nrRegCom.equals(other.nrRegCom))
			return false;
		if (nume == null) {
			if (other.nume != null)
				return false;
		} else if (!nume.equals(other.nume))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		return true;
	}

	@XmlElement()
	public Integer getIdClient() {
		return idClient;
	}

	
	public Client() {
		super();
			// TODO Auto-generated constructor stub
		}
	public String getNume(){
		return nume;
	}
	public void setNume(String nume){
		this.nume = nume;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContBanca() {
		return contBanca;
	}
	public void setContBanca(String contBanca) {
		this.contBanca = contBanca;
	}
	public Integer getCUI(){
		return CUI;
	}
	public void setCUI(Integer CUI){
		this.CUI = CUI;
	}
	public String getNrRegCom(){
		return nrRegCom;
	}
	public void setNrRegCom(String nrRegCom){
		this.nrRegCom= nrRegCom;
	}
	
	

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}



 void setContracte(List<Contract> contracteClient) {
		// TODO Auto-generated method stub
		
	}
public Client(Integer idClient, String nume, String adresa, String telefon,
		String email, String contBanca, Integer cUI, String nrRegCom,
		List<Contract> contracte) {
	super();
	this.idClient = idClient;
	this.nume = nume;
	this.adresa = adresa;
	this.telefon = telefon;
	this.email = email;
	this.contBanca = contBanca;
	CUI = cUI;
	this.nrRegCom = nrRegCom;
	this.contracte = contracte;
}
public Client(Integer id, String string, Integer contractCount) {
	// TODO Auto-generated constructor stub
}
 

	
}
