package org.app.service.entities;

public class Responsabil {
	private Integer responsabilId;
	private String nume;
	private String rol;

public Responsabil() {
		super();
	}


public Responsabil(Integer responsabilId, String nume, String rol) {
		super();
		this.responsabilId = responsabilId;
		this.nume = nume;
		this.rol = rol;
	}


public Integer getResponsabilId() {
		return responsabilId;
	}


	public void setResponsabilId(Integer responsabilId) {
		this.responsabilId = responsabilId;
	}


	public String getNume() {
		return nume;
	}


	public void setNume(String nume) {
		this.nume = nume;
	}


	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}




public void finalize() throws Throwable {

}

}
