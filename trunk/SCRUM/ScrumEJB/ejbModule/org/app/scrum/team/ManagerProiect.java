package org.app.scrum.team;


public class ManagerProiect extends Membru{
//	private Integer id;
//	private String numePrenume;

	private Integer experientaManageriala; // in ani
	private String competenteManageriale; // agile, cascade, RUP
	
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
//	public String getNumePrenume() {
//		return numePrenume;
//	}
//	public void setNumePrenume(String numePrenume) {
//		this.numePrenume = numePrenume;
//	}
	
	public Integer getExperientaManageriala() {
		return experientaManageriala;
	}
	public void setExperientaManageriala(Integer experientaManageriala) {
		this.experientaManageriala = experientaManageriala;
	}
	public String getCompetenteManageriale() {
		return competenteManageriale;
	}
	public void setCompetenteManageriale(String competenteManageriale) {
		this.competenteManageriale = competenteManageriale;
	}
	
	public ManagerProiect() {
		super();
		super.setRol(Rol.MANAGER);
	}
	
	public ManagerProiect(Integer id, String numePrenume,
			Integer experientaManageriala, String competenteManageriale) {
//		super();
//		this.id = id;
//		this.numePrenume = numePrenume;
		
		super(id, numePrenume, Rol.MANAGER);
		this.experientaManageriala = experientaManageriala;
		this.competenteManageriale = competenteManageriale;
	}
	
	
	// caz supra-incarcare
	public String getCompetente(TipCompetenta tip) {
		if (TipCompetenta.MANAGERIAL.equals(tip))
			return "manageriale: " + competenteManageriale;
		if (TipCompetenta.TEHNIC.equals(tip))
			return "tehnice: " + getCompetente();
		return null;
	}	
	
	// caz supra-scriere
	@Override
	public void setRol(Rol rol) {
		throw new Error("Proprietatea rol nu poate fi schimbata!");
	}
	
	public enum TipCompetenta {MANAGERIAL, TEHNIC};
}
