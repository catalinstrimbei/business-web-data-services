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
	protected List<ContractSuport> contract = new ArrayList<ContractSuport>();
	
	
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
				+ ((idActivitate == null) ? 0 : idActivitate.hashCode());
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
		if (idActivitate == null) {
			if (other.idActivitate != null)
				return false;
		} else if (!idActivitate.equals(other.idActivitate))
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
	
	
	
}
