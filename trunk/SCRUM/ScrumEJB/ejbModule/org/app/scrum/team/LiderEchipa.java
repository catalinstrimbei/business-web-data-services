package org.app.scrum.team;

public class LiderEchipa extends Membru{
//	private Integer id;
//	private String numePrenume;
	private String competenteTehnologice; // JEE, Spring, .NET, JS/Node.js, Ruby_Rails
	
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
	
	public String getCompetenteTehnologice() {
		return competenteTehnologice;
	}
	private void setCompetenteTehnologice(String competenteTehnologice) {
		this.competenteTehnologice = competenteTehnologice;
	}
	public LiderEchipa(Integer id, String numePrenume,
			String competenteTehnologice) {
//		super();
//		this.id = id;
//		this.numePrenume = numePrenume;
		super(id, numePrenume, Rol.MANAGER);
		this.competenteTehnologice = competenteTehnologice;
	}
	public LiderEchipa() {
		super();
	}
	
	// Polimorfism
	@Override
	public void setCompetente(String competente) {
		this.setCompetenteTehnologice(competente);
	}	
	
	// Supraincarcare
	public void setCompetente(String competente, TipCompetente tip) {
		if (tip.equals(TipCompetente.MANAGERIALE))
			super.setCompetente(competente);
		
		if (tip.equals(TipCompetente.TEHNOLOGICE))
			setCompetenteTehnologice(competente);
		
	}
	
	public enum TipCompetente {MANAGERIALE, TEHNOLOGICE}
}
