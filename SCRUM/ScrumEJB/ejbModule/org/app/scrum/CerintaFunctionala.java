package org.app.scrum;

import javax.persistence.Entity;

@Entity
public class CerintaFunctionala extends Cerinta {

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
	
	public CerintaFunctionala(Integer idCerinta, String denumire,
			String descriere, String categorieFunctionala,
			String descriereUseCase) {
		super(idCerinta, denumire, descriere);
		this.idCerinta = idCerinta;
		this.categorieFunctionala = categorieFunctionala;
		this.descriereUseCase = descriereUseCase;
		
		this.categorie = CategorieCerinta.FUNCTIONALA;
	}
	
	public CerintaFunctionala(Integer idCerinta, String denumire,
			String descriere, 
			String descriereUseCase) {
		super(idCerinta, denumire, descriere);
		this.idCerinta = idCerinta;
		this.categorieFunctionala = categorieFunctionala;
		this.descriereUseCase = descriereUseCase;
		
		this.categorie = CategorieCerinta.FUNCTIONALA;
	}	
	
	
	public CerintaFunctionala() {
		super();
		this.categorie = CategorieCerinta.FUNCTIONALA;
	}
	
	@Override
	public String toString() {
		return super.toString() + " CerintaFunctionala [categorieFunctionala="
				+ categorieFunctionala + ", descriereUseCase="
				+ descriereUseCase + "]";
	}
	
	@Override
	public void setCategorie(CategorieCerinta categorie) {
		throw new Error("Proprietatea categorie nu poate fi schimbata!");
	}
}
