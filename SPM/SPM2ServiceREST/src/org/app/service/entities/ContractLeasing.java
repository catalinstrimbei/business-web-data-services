package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
//@MappedSuperclass
public class ContractLeasing extends Contract implements Serializable{
	@ManyToOne
	private ContractSuport contractSuport;

	public Contract toDTO(){
		return new ContractLeasing (nrContract, client, produs, dataContract, dataInceput, dataIncheiere, observatii,contractSuport);
	}
	public static List<ContractLeasing> toDTOList(List<ContractLeasing> contracte){
		List<ContractLeasing> contracteDTOList= new ArrayList<>();
		for(Contract c:contracte){
			contracteDTOList.add((ContractLeasing) c.toDTO());
		}
		return contracteDTOList;
	}
	
	public ContractLeasing(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii, ContractSuport contractSuport) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.contractSuport = contractSuport;
	}


	public ContractLeasing() {
		super();
		// TODO Auto-generated constructor stub
	}





	public ContractLeasing(Integer nrContract, Client client,
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
