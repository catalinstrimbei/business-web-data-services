package org.app.service.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.app.service.sprint.Task;
import org.app.service.team.Member;

//abstract 

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Resursa implements Comparable<Resursa>, Serializable
{
	@Id @GeneratedValue
	protected Integer IdResursa;
	private Double PretUM;
	private String UM;
	
	@Enumerated
	protected FeatureCategory category;
	
	@Transient
	private List<Task> tasks = new ArrayList<>();
	
	public Integer getIdResursa() {
		return IdResursa;
	}
	public void setIdResursa(Integer IdResursa) {
		this.IdResursa = IdResursa;
	}
	public String getUM() {
		return UM;
	}
	public void setUM(String UM) {
		this.UM = UM;
	}
	public Double getPretUM() {
		return PretUM;
	}
	public void setPretUM(Double PretUM) {
		this.PretUM = PretUM;
	}
	public FeatureCategory getCategory() {
		return category;
	}
	public void setCategory(FeatureCategory category) {
		this.category = category;
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public Resursa() {
		super();
	}
	
	public Resursa(Integer IdResursa, Double PretUM, String UM,
			FeatureCategory category, List<Task> tasks) {
		super();
		this.IdResursa = IdResursa;
		this.PretUM = PretUM;
		this.UM = UM;
		this.category = category;
		this.tasks = tasks;
	}

	
	public Resursa(Integer IdResursa, Double PretUM, String UM) {
		super();
		this.IdResursa = IdResursa;
		this.PretUM = PretUM;
		this.UM = UM;

	}



	public enum FeatureCategory {
		BUSINESS, TECHNICAL;
	}

	@Override
	public int compareTo(Resursa other) {
		if (this.equals(other))
			return 0;
		return this.getPretUM().compareTo(other.getPretUM());
	}
	public Resursa(Integer IdResursa, Double PretUM) {
		super();
		this.IdResursa = IdResursa;
		this.PretUM = PretUM;
	}
	@Override
	public String toString() {
		return "Resursa [IdResursa=" + IdResursa + ", PretUM=" + PretUM
				+ ", UM=" + UM + ", category=" + category
				+ ", tasks=" + tasks + "]";
	}	
}