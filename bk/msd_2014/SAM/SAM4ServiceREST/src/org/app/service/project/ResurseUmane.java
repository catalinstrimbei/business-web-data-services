package org.app.service.project;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ResurseUmane extends Resursa implements Serializable{

	private Integer IdResursaU; // basic, improvement
	private Double SalariuOrar; // scenariu-flux dialog utilizator final - aplicatie
	
	public Integer getIdResursaU() {
		return IdResursaU;
	}
	public void setIdResursaU(Integer IdResursaU) {
		this.IdResursaU = IdResursaU;
	}
	public Double getSalariuOrar() {
		return SalariuOrar;
	}
	public void setSalariuOrar(Double SalariuOrar) {
		this.SalariuOrar = SalariuOrar;
	}
	public ResurseUmane(Integer IdResursaU, Double SalariuOrar) {
		super();
		this.IdResursaU = IdResursaU;
		this.SalariuOrar = SalariuOrar;
	}
	public ResurseUmane() {
		super();
	}
	@Override
	public String toString() {
		return "ResurseUmane [IdResursaU=" + IdResursaU
				+ ", SalariuOrar=" + SalariuOrar + "]";
	}
}
