package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;


@Entity
@XmlRootElement(name="test") 
@XmlAccessorType(XmlAccessType.NONE )

public class Test implements Serializable {

	private String acceptableLimit;
	private String description;
	
	@Id@GeneratedValue
	private int idTest;
	
	@ManyToOne
	private Task task;

	public Test(){

	}

	public void finalize() throws Throwable {

	}
	
	public Test toDTO(){
		return new Test(acceptableLimit,description,idTest,task.toDTO());
	}
	
	public static 	List<Test> toDTOList(List<Test> tests){
		List<Test> testDTOList= new ArrayList<>();
		for(Test t:tests ){
			testDTOList.add(t.toDTO());
		}
		return testDTOList;
	}
	

	public Test(String acceptableLimit, String description, int idTest,
			Task task) {
		this.acceptableLimit = acceptableLimit;
		this.description = description;
		this.idTest = idTest;
		this.task = task;
	}

	public String getAcceptableLimit() {
		return acceptableLimit;
	}

	public void setAcceptableLimit(String acceptableLimit) {
		this.acceptableLimit = acceptableLimit;
	}

	@XmlElement 
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement 
	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	public static String BASE_URL = Task.BASE_URL;
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ this.getTask().getIdTask() 
				+ "/tests/" 
				+ this.getIdTest();
        return new AtomLink(restUrl, "get-test");
    }	
	public void setLink(AtomLink link){}
	
}