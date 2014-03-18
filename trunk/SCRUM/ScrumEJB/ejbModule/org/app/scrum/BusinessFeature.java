package org.app.scrum;

import javax.persistence.Entity;

@Entity
public class BusinessFeature extends Feature {

	private String categorieFunctionala; // basic, improvement
	private String descriereUseCase; // scenariu-flux dialog utilizator final - aplicatie
	
	public String getCategorieFunctionala() {
		return categorieFunctionala;
	}
	public void setCategorieFunctionala(String categorieFunctionala) {
		this.categorieFunctionala = categorieFunctionala;
	}
	public String getDescriereUseCase() {
		return descriereUseCase;
	}
	public void setDescriereUseCase(String descriereUseCase) {
		this.descriereUseCase = descriereUseCase;
	}
	
	public BusinessFeature(Integer idCerinta, String denumire,
			String descriere, String categorieFunctionala,
			String descriereUseCase) {
		super(idCerinta, denumire, descriere);
		this.idCerinta = idCerinta;
		this.categorieFunctionala = categorieFunctionala;
		this.descriereUseCase = descriereUseCase;
		
		this.categorie = FeatureCategory.FUNCTIONALA;
	}
	
	public BusinessFeature(Integer idCerinta, String denumire,
			String descriere, 
			String descriereUseCase) {
		super(idCerinta, denumire, descriere);
		this.idCerinta = idCerinta;
		this.categorieFunctionala = categorieFunctionala;
		this.descriereUseCase = descriereUseCase;
		
		this.categorie = FeatureCategory.FUNCTIONALA;
	}	
	
	
	public BusinessFeature() {
		super();
		this.categorie = FeatureCategory.FUNCTIONALA;
	}
	
	@Override
	public String toString() {
		return super.toString() + " CerintaFunctionala [categorieFunctionala="
				+ categorieFunctionala + ", descriereUseCase="
				+ descriereUseCase + "]";
	}
	
	@Override
	public void setCategorie(FeatureCategory categorie) {
		throw new Error("Proprietatea categorie nu poate fi schimbata!");
	}
}
