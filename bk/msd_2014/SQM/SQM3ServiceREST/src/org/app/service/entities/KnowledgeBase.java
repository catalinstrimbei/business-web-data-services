package org.app.service.entities;

import javax.persistence.GeneratedValue.*;
import javax.persistence.*;
import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class KnowledgeBase  implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer topicId;
	private String solution;
	
	//@OneToMany(mappedBy="topicKnowledgeBase")
	//private List<Complaints> complaints=new ArrayList<Complaints>();
	
	public Integer getTopicId() {
		return topicId;
	}
	
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}

}
