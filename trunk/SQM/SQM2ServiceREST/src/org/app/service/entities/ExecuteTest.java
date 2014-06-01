package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class ExecuteTest extends Test {

	private String dateOfExecutions;
	private char idTestExecuted;
	private OptimizationSolutions m_OptimizationSolutions;

	public ExecuteTest(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public ExecuteTest(String dateOfExecutions, char idTestExecuted,
			OptimizationSolutions m_OptimizationSolutions) {
		this.dateOfExecutions = dateOfExecutions;
		this.idTestExecuted = idTestExecuted;
		this.m_OptimizationSolutions = m_OptimizationSolutions;
	}

	public String getDateOfExecutions() {
		return dateOfExecutions;
	}

	public void setDateOfExecutions(String dateOfExecutions) {
		this.dateOfExecutions = dateOfExecutions;
	}

	public char getIdTestExecuted() {
		return idTestExecuted;
	}

	public void setIdTestExecuted(char idTestExecuted) {
		this.idTestExecuted = idTestExecuted;
	}

	public OptimizationSolutions getM_OptimizationSolutions() {
		return m_OptimizationSolutions;
	}

	public void setM_OptimizationSolutions(
			OptimizationSolutions m_OptimizationSolutions) {
		this.m_OptimizationSolutions = m_OptimizationSolutions;
	}
	

}