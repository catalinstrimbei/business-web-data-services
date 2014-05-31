package org.app.service.entities;

import java.util.ArrayList;
import java.util.List;

public class TestComponent {

	public Integer getId() {
		return Id;
	}



	public TestComponent() {
		super();
	}



	public TestComponent(Integer id, String nume, String descriere,
			List<TestComponent> componente) {
		super();
		Id = id;
		Nume = nume;
		Descriere = descriere;
		Componente = componente;
	}



	public void setId(Integer id) {
		Id = id;
	}





	public String getNume() {
		return Nume;
	}




	public void setNume(String nume) {
		Nume = nume;
	}




	public String getDescriere() {
		return Descriere;
	}



	public void setDescriere(String descriere) {
		Descriere = descriere;
	}




	public List<TestComponent> getComponente() {
		return Componente;
	}



	public void setComponente(List<TestComponent> componente) {
		Componente = componente;
	}



	private Integer Id;
	private String Nume;
	private String Descriere;
	private List<TestComponent> Componente= new ArrayList<TestComponent>();	









public void finalize() throws Throwable {

}

}