package org.app.service.entities;

import javax.persistence.GeneratedValue.*;
import javax.persistence.*;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;
enum Level{
	  First,
	  Middle,
	  Third,
	  Expert
	}

@Entity
public class EmployeeLevel implements Serializable {

	@Id @GeneratedValue
	private Integer levelId;
	private Level level;
	private String job_description;
	
	@OneToMany(mappedBy="employeeLevel")
	private List<Employee> employee=new ArrayList<Employee>();

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
}
