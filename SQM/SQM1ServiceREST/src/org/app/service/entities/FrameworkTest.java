package org.app.service.entities;

public class FrameworkTest {

	public Integer getIdFramework() {
		return idFramework;
	}
	public void setIdFramework(Integer idFramework) {
		this.idFramework = idFramework;
	}
	public String getNumeFramework() {
		return numeFramework;
	}
	public void setNumeFramework(String numeFramework) {
		this.numeFramework = numeFramework;
	}
	private Integer idFramework;
	public FrameworkTest() {
		super();
	}
	public FrameworkTest(Integer idFramework, String numeFramework) {
		super();
		this.idFramework = idFramework;
		this.numeFramework = numeFramework;
	}
	private String numeFramework;
	
}
