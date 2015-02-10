package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TestResult {

	@Id@GeneratedValue
	private int testResulId;
	private String description;
	private int idResult;
	private Test m_Test;

	public TestResult(){

	}

	public void finalize() throws Throwable {

	}

	public int getTestResulId() {
		return testResulId;
	}

	public void setTestResulId(int testResulId) {
		this.testResulId = testResulId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdResult() {
		return idResult;
	}

	public void setIdResult(int idResult) {
		this.idResult = idResult;
	}

	public Test getM_Test() {
		return m_Test;
	}

	public void setM_Test(Test m_Test) {
		this.m_Test = m_Test;
	}

	public TestResult(int testResulId, String description, int idResult,
			Test m_Test) {
		this.testResulId = testResulId;
		this.description = description;
		this.idResult = idResult;
		this.m_Test = m_Test;
	}
	

}