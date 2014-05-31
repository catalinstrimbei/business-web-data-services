package org.app.service.entities;

import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Activitate implements Serializable{
	@Id
	@GeneratedValue
	private Integer idActivitate;
	@ManyToOne
	private Operator operator;
	private Date dataActivitate;
	private Integer oreFacturate;
	private String obiectActivitate;
	private String tipActivitate;
	
	@OneToMany
	(/*mappedBy="client"*/ cascade=ALL, /*fetch= EAGER*/orphanRemoval=false)
	protected List<Contract> contract = new ArrayList<Contract>();
	
	public static Activitate toDTOAggregate(Activitate activitate){
		if(activitate==null)
			return null;
		Activitate activitateDTO=activitate.toDTO();
		List<Contract> activitatiDTO= Contract.toDTOList(activitate.getContract());
		return activitateDTO;
}
	
	private List<Contract> getContract() {
		// TODO Auto-generated method stub
		return null;
	}

	private Activitate toDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Activitate(Integer idActivitate, Operator operator,
			Date dataActivitate, Integer oreFacturate, String obiectActivitate,
			String tipActivitate) {
		super();
		this.idActivitate = idActivitate;
		this.operator = operator;
		this.dataActivitate = dataActivitate;
		this.oreFacturate = oreFacturate;
		this.obiectActivitate = obiectActivitate;
		this.tipActivitate = tipActivitate;
	}

	
	public Activitate() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contract == null) ? 0 : contract.hashCode());
		result = prime * result
				+ ((dataActivitate == null) ? 0 : dataActivitate.hashCode());
		result = prime * result
				+ ((idActivitate == null) ? 0 : idActivitate.hashCode());
		result = prime
				* result
				+ ((obiectActivitate == null) ? 0 : obiectActivitate.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result
				+ ((oreFacturate == null) ? 0 : oreFacturate.hashCode());
		result = prime * result
				+ ((tipActivitate == null) ? 0 : tipActivitate.hashCode());
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
		Activitate other = (Activitate) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (dataActivitate == null) {
			if (other.dataActivitate != null)
				return false;
		} else if (!dataActivitate.equals(other.dataActivitate))
			return false;
		if (idActivitate == null) {
			if (other.idActivitate != null)
				return false;
		} else if (!idActivitate.equals(other.idActivitate))
			return false;
		if (obiectActivitate == null) {
			if (other.obiectActivitate != null)
				return false;
		} else if (!obiectActivitate.equals(other.obiectActivitate))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (oreFacturate == null) {
			if (other.oreFacturate != null)
				return false;
		} else if (!oreFacturate.equals(other.oreFacturate))
			return false;
		if (tipActivitate == null) {
			if (other.tipActivitate != null)
				return false;
		} else if (!tipActivitate.equals(other.tipActivitate))
			return false;
		return true;
	}


	public Integer getIdActivitate() {
		return idActivitate;
	}

	public void setIdActivitate(Integer idActivitate) {
		this.idActivitate = idActivitate;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Date getDataActivitate() {
		return dataActivitate;
	}

	public void setDataActivitate(Date dataActivitate) {
		this.dataActivitate = dataActivitate;
	}

	public Integer getOreFacturate() {
		return oreFacturate;
	}

	public void setOreFacturate(Integer oreFacturate) {
		this.oreFacturate = oreFacturate;
	}

	public String getObiectActivitate() {
		return obiectActivitate;
	}

	public void setObiectActivitate(String obiectActivitate) {
		this.obiectActivitate = obiectActivitate;
	}

	public String getTipActivitate() {
		return tipActivitate;
	}

	public void setTipActivitate(String tipActivitate) {
		this.tipActivitate = tipActivitate;
	}

	public static List<Activitate> toDTOList(Object activitate) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setContract(List<Contract> contract) {
		this.contract = contract;
	}

	public Activitate(Integer idActivitate, Operator operator,
			Date dataActivitate, Integer oreFacturate, String obiectActivitate,
			String tipActivitate, List<Contract> contract) {
		super();
		this.idActivitate = idActivitate;
		this.operator = operator;
		this.dataActivitate = dataActivitate;
		this.oreFacturate = oreFacturate;
		this.obiectActivitate = obiectActivitate;
		this.tipActivitate = tipActivitate;
		this.contract = contract;
	}
	
	
	
}
