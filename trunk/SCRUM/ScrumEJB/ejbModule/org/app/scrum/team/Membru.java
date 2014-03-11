package org.app.scrum.team;

public class Membru 
implements Comparable<Membru>{
	private Integer idMembru;
	private String numePrenume;
	private Rol rol;
	
	// bean accessors
	public Integer getIdMembru() {
		return idMembru;
	}
	public void setIdMembru(Integer idMembru) {
		this.idMembru = idMembru;
	}
	public String getNumePrenume() {
		return numePrenume;
	}
	public void setNumePrenume(String numePrenume) {
		this.numePrenume = numePrenume;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Membru(Integer idMembru, String numePrenume, Rol rol) {
		super();
		this.idMembru = idMembru;
		this.numePrenume = numePrenume;
		this.rol = rol;
	}
	public Membru() {
		super();
	}
	
	public Membru(Integer idMembru, String numePrenume, String rol) {
		super();
		this.idMembru = idMembru;
		this.numePrenume = numePrenume;
//		this.rol = rol;
	}	
	
	// caz supra-incarcare
	private String competente;
	
	public String getCompetente() {
		return competente;
	}
	public void setCompetente(String competente) {
		this.competente = competente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMembru == null) ? 0 : idMembru.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Membru other = (Membru) obj;
		if (idMembru == null) {
			if (other.idMembru != null)
				return false;
		} else if (!idMembru.equals(other.idMembru))
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Membru other) {
		if (this.equals(other))
			return 0;
		//return this.getNumePrenume().compareTo(other.getNumePrenume());
		return this.getIdMembru().compareTo(other.getIdMembru());
	}
	
	@Override
	public String toString() {
		return "Membru [idMembru=" + idMembru + ", numePrenume=" + numePrenume
				+ ", rol=" + rol + "]";
	}	
	
	
}