package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public class ContractSuport extends Contract{
	@ManyToOne
	private Activitate activitateSuport;

	public ContractSuport(Integer nrContract, Client client, Produs produs,
			Date dataContract, Date dataInceput, Date dataIncheiere,
			String observatii, Activitate activitateSuport) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.activitateSuport = activitateSuport;
	}

	
	public ContractSuport() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ContractSuport(Integer nrContract, Client client, Produs produs,
			Date dataContract, Date dataInceput, Date dataIncheiere,
			String observatii) {
		super(nrContract, client, produs, dataContract, dataInceput, dataIncheiere,
				observatii);
		// TODO Auto-generated constructor stub
	}


	public Activitate getActivitateSuport() {
		return activitateSuport;
	}

	public void setActivitateSuport(Activitate activitateSuport) {
		this.activitateSuport = activitateSuport;
	}
	
	

}
