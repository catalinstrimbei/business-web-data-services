package org.app.scrum;

import javax.persistence.Entity;

@Entity
public class TechnicalFeature extends Feature {
	
	private String nivelArhitectural; // prezentare (UI), model si/sau logica afacerii, persistenta, baza de date
	private String competenteTehniceNecesare; // LP: Java, C#, JavaScript, BD: SQL, MongoDB, FMks: EJB, JPA, JSF, Angular, JQuery
	private String descriereFluxArhitecural; // colaborare inter-niveluri: UI - catre - Fmk.Logica/Model - catre - Sistem BD
	
	public String getNivelArhitectural() {
		return nivelArhitectural;
	}
	public void setNivelArhitectural(String nivelArhitectural) {
		this.nivelArhitectural = nivelArhitectural;
	}
	public String getCompetenteTehniceNecesare() {
		return competenteTehniceNecesare;
	}
	public void setCompetenteTehniceNecesare(String competenteTehniceNecesare) {
		this.competenteTehniceNecesare = competenteTehniceNecesare;
	}
	public String getDescriereFluxArhitecural() {
		return descriereFluxArhitecural;
	}
	public void setDescriereFluxArhitecural(String descriereFluxArhitecural) {
		this.descriereFluxArhitecural = descriereFluxArhitecural;
	}
	
	@Override
	public void setCategorie(FeatureCategory categorie) {
		throw new Error("Proprietatea categorie nu poate fi schimbata!");
	}
	
	public TechnicalFeature() {
		super();
		this.categorie = FeatureCategory.TEHNICA;
	}
	public TechnicalFeature(Integer idCerinta, String denumire, String descriere,
			String nivelArhitectural, String competenteTehniceNecesare,
			String descriereFluxArhitecural) {
		super(idCerinta, denumire, descriere);
		this.nivelArhitectural = nivelArhitectural;
		this.competenteTehniceNecesare = competenteTehniceNecesare;
		this.descriereFluxArhitecural = descriereFluxArhitecural;
		
		this.categorie = FeatureCategory.TEHNICA;
	}
	public TechnicalFeature(Integer idCerinta, String denumire, String descriere) {
		super(idCerinta, denumire, descriere);
	}
	
	
}
