package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
//@MappedSuperclass
public class ContractConsultanta extends Contract implements Serializable{
	@ManyToOne @JoinColumn(name="id_contractVanzare")
	private ContractVanzare contractVanzare;
	@ManyToOne @JoinColumn(name="id_contractLeasing")
	private ContractLeasing contractLeasing;
	@ManyToOne @JoinColumn(name="id_activitateConsultanta")
	private Activitate activitateConsultanta;
	
	public Contract toDTO(){
		return new ContractConsultanta (nrContract, client, produs, dataContract, dataInceput, dataIncheiere, observatii,contractVanzare,contractLeasing, activitateConsultanta);
	}
	public static List<ContractConsultanta> toDTOList(List<ContractConsultanta> contracte){
		List<ContractConsultanta> contracteDTOList= new ArrayList<>();
		for(Contract c:contracte){
			contracteDTOList.add((ContractConsultanta) c.toDTO());
		}
		return contracteDTOList;
	}
	public ContractConsultanta(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
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
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii) {
		super(nrContract, client, produs, dataContract, dataInceput, dataIncheiere,
				observatii);
		// TODO Auto-generated constructor stub
	}



	public ContractConsultanta(Object object, String string, Client client) {
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
