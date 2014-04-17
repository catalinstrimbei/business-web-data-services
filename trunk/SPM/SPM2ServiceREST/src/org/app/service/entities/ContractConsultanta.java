package org.app.service.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
//@MappedSuperclass
public class ContractConsultanta extends Contract{
	@ManyToOne @JoinColumn(name="id_contractVanzare")
	private ContractVanzare contractVanzare;
	@ManyToOne @JoinColumn(name="id_contractLeasing")
	private ContractLeasing contractLeasing;
	@ManyToOne @JoinColumn(name="id_activitateConsultanta")
	private Activitate activitateConsultanta;
	
	public ContractConsultanta(Integer nrContract, Client client,
			Produs produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii,
			ContractVanzare contractVanzare, ContractLeasing contractLeasing,
			Activitate activitateConsultanta) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.contractVanzare = contractVanzare;
		this.contractLeasing = contractLeasing;
		this.activitateConsultanta = activitateConsultanta;
	}
	
	

	public ContractConsultanta() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ContractConsultanta(Integer nrContract, Client client,
			Produs produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii) {
		super(nrContract, client, produs, dataContract, dataInceput, dataIncheiere,
				observatii);
		// TODO Auto-generated constructor stub
	}



	public ContractVanzare getContractVanzare() {
		return contractVanzare;
	}

	public void setContractVanzare(ContractVanzare contractVanzare) {
		this.contractVanzare = contractVanzare;
	}

	public ContractLeasing getContractLeasing() {
		return contractLeasing;
	}

	public void setContractLeasing(ContractLeasing contractLeasing) {
		this.contractLeasing = contractLeasing;
	}

	public Activitate getActivitateConsultanta() {
		return activitateConsultanta;
	}

	public void setActivitateConsultanta(Activitate activitateConsultanta) {
		this.activitateConsultanta = activitateConsultanta;
	}
	
	
	
}
