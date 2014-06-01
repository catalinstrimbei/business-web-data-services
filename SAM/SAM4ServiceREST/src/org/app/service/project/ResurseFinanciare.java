package org.app.service.project;

import javax.persistence.Entity;

@Entity
public class ResurseFinanciare extends Resursa {
	
	private Integer IdResursaFin; 
	private Double Valoare; 
	
	
	public Integer getIdResursaFin() {
		return IdResursaFin;
	}

	public void setIdResursaFin(Integer IdResursaFin) {
		this.IdResursaFin = IdResursaFin;
	}

	public Double getValoare() {
		return Valoare;
	}

	public void setValoare(Double Valoare) {
		this.Valoare = Valoare;
	}


	@Override
	public void setCategory(FeatureCategory category) {
		throw new Error("Proprietatea categorie nu poate fi schimbata!");
	}
	
	public ResurseFinanciare() {
		super();
		this.category = FeatureCategory.TECHNICAL;
	}

	public ResurseFinanciare(Integer IdResursa, String UM, Double PretUM,
			Integer IdResursaFin, Double Valoare ) {
		super(IdResursa,PretUM,UM);
		this.IdResursaFin = IdResursaFin;
		this.Valoare = Valoare;
		
		this.category = FeatureCategory.TECHNICAL;
	}

	public ResurseFinanciare(Integer IdResursa, String UM, Double PretUM) {
		super(IdResursa,PretUM,UM);
	}
}
