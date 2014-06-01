package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class OptimizationSolutions {

	private String activOptimization;
	@Id@GeneratedValue
	private int idOptimizedSolution;

	public OptimizationSolutions(){

	}

	public void finalize() throws Throwable {

	}

	public String getActivOptimization() {
		return activOptimization;
	}

	public void setActivOptimization(String activOptimization) {
		this.activOptimization = activOptimization;
	}

	public int getIdOptimizedSolution() {
		return idOptimizedSolution;
	}

	public void setIdOptimizedSolution(int idOptimizedSolution) {
		this.idOptimizedSolution = idOptimizedSolution;
	}

	public OptimizationSolutions(String activOptimization,
			int idOptimizedSolution) {
		this.activOptimization = activOptimization;
		this.idOptimizedSolution = idOptimizedSolution;
	}
	
}