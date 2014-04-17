package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class ContractVanzare extends Contract{
	@ManyToOne
	private ContractSuport contractSuport;

	public ContractVanzare(Integer nrContract, Client client, Produs produs,
			Date dataContract, Date dataInceput, Date dataIncheiere,
			String observatii, ContractSuport contractSuport) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.contractSuport = contractSuport;
	}

	
	public ContractVanzare() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ContractVanzare(Integer nrContract, Client client, Produs produs,
			Date dataContract, Date dataInceput, Date dataIncheiere,
			String observatii) {
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
