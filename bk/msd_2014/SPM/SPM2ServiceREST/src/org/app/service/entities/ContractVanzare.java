package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
@XmlRootElement(name="contract")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
//@MappedSuperclass
public class ContractVanzare extends Contract implements Serializable{
	@ManyToOne
	private ContractSuport contractSuport;

	public Contract toDTO(){
		return new ContractVanzare (nrContract, client, produs, dataContract, dataInceput, dataIncheiere, observatii,contractSuport);
	}
	public static List<ContractVanzare> toDTOList(List<ContractVanzare> contracte){
		List<ContractVanzare> contracteDTOList= new ArrayList<>();
		for(Contract c:contracte){
			contracteDTOList.add((ContractVanzare) c.toDTO());
		}
		return contracteDTOList;
	}

	public static String BASE_URL= Client.BASE_URL;
	@XmlElement(name="link")
	
	public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ this.getClient().getIdClient()
				+"/contract/"
				+this.getNrContract();
		return new AtomLink(restUrl, "get-contract");
	}
	
	public ContractVanzare(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii, ContractSuport contractSuport) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.contractSuport = contractSuport;
	}


	public ContractVanzare() {
		super();
		// TODO Auto-generated constructor stub
	}




	public ContractVanzare(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii) {
		super(nrContract, client, produs, dataContract, dataInceput, dataIncheiere,
				observatii);
		// TODO Auto-generated constructor stub
	}


	public ContractSuport getContractSuport() {
		return contractSuport;
	}

	public void setContractSuport(ContractSuport contractSuport) {
		this.contractSuport = contractSuport;
	}
	
	

}
