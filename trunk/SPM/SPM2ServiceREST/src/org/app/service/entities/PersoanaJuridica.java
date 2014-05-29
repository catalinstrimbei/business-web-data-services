package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

//import com.sun.xml.internal.txw2.annotation.XmlElement;
@XmlRootElement(name="client")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
//@MappedSuperclass
public class PersoanaJuridica extends Client implements Serializable{
	private String denumire;
	private Integer CUI;
	private String nrRegCom;
	
	public PersoanaJuridica toDTO(){
		PersoanaJuridica clientDTO= new PersoanaJuridica (adresa, telefon, email,contBanca,denumire, CUI,nrRegCom);
		return clientDTO;
		
	}
	public static PersoanaJuridica[] toDTOList(Collection<PersoanaJuridica> client){
		List<PersoanaJuridica> clientDTOList= new ArrayList<>();
		for(PersoanaJuridica cl:client){
			clientDTOList.add(cl.toDTO());
		} 
		return clientDTOList.toArray(new PersoanaJuridica[0]);
	}
	
	public static PersoanaJuridica toDTOAggregate(PersoanaJuridica client){
		if(client==null)
			return null;
		PersoanaJuridica clientDTO=client.toDTO();
		List<Contract> contracteDTO= Contract.toDTOList(client.getContract());
		return clientDTO;
	}
		
	
	
	/*private Object getContract() {
		// TODO Auto-generated method stub
		return null;
	}*/
	public PersoanaJuridica(String adresa, String telefon, String email,
			String contBanca, String denumire, Integer cUI, String nrRegCom) {
		super(adresa, telefon, email, contBanca);
		this.denumire = denumire;
		CUI = cUI;
		this.nrRegCom = nrRegCom;
	}

	
	public PersoanaJuridica() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PersoanaJuridica(String adresa, String telefon, String email,
			String contBanca) {
		super(adresa, telefon, email, contBanca);
		// TODO Auto-generated constructor stub
	}


	public PersoanaJuridica(String denumire2, Integer cUI2, String nrRegCom2) {
		// TODO Auto-generated constructor stub
	}
	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Integer getCUI() {
		return CUI;
	}

	public void setCUI(Integer cUI) {
		CUI = cUI;
	}

	public String getNrRegCom() {
		return nrRegCom;
	}

	public void setNrRegCom(String nrRegCom) {
		this.nrRegCom = nrRegCom;
	}
	public static Collection<Client> toDTOs(Collection<Client> collection) {
		// TODO Auto-generated method stub
		return null;
	}
	@XmlElement()
	public Integer getIdClient() {
		return idClient;
	}
	@XmlElementWrapper(name="contract") @XmlElement(name="contract")
	public List<Contract> getContract() {
		return contract;
	}
}
