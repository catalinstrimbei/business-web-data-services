package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@Entity
//@MappedSuperclass
public class ContractSuport extends Contract implements Serializable{
	@ManyToOne
	private Activitate activitateSuport;
	@OneToMany
	(/*mappedBy="client"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<ContractVanzare> contract = new ArrayList<ContractVanzare>();
	
	public Contract toDTO(){
		return new ContractSuport (nrContract, client, produs, dataContract, dataInceput, dataIncheiere, observatii,activitateSuport);
	}
	public static List<ContractSuport> toDTOList(List<ContractSuport> contracte){
		List<ContractSuport> contracteDTOList= new ArrayList<>();
		for(Contract c:contracte){
			contracteDTOList.add((ContractSuport) c.toDTO());
		}
		return contracteDTOList;
	}

	
	public ContractSuport(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii, Activitate activitateSuport) {
		super(nrContract, client, produs, dataContract, dataInceput,
				dataIncheiere, observatii);
		this.activitateSuport = activitateSuport;
	}


	public ContractSuport() {
		super();
		// TODO Auto-generated constructor stub
	}





	public ContractSuport(Integer nrContract, Client client,
			List<Produs> produs, Date dataContract, Date dataInceput,
			Date dataIncheiere, String observatii) {
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
