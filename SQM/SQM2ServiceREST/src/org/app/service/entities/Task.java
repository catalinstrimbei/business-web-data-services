package org.app.service.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.CascadeType.ALL;

@XmlRootElement(name="task") 
@XmlAccessorType(XmlAccessType.NONE ) 
@Entity
public class Task implements Serializable {

	private String description;
	private int estimateDurationDays;
	@Id@GeneratedValue
	private int idTask;
	private String name;
	private Date startDate;
	private Feature IdFeature;
	
	@OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "task")
	private List<Test> tests;
	
	@OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "task")
	private List<Optimize> optimizations;
	
	@OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true, mappedBy = "task")
	private List<Member> members;
	
	public Task(){

	}

	public void finalize() throws Throwable {

	}
	
	public static Task toDTOAggregate(Task task) {
		if(task==null)
		{
			return null;
		}
		Task taskDTO=task.toDTO();
		List<Test> testDTO=Test.toDTOList(task.getTests());
		taskDTO.setTests(testDTO);
		
		return taskDTO;
	}
	
	public Task toDTO() {
		Task taskTDO=new Task(idTask,name,startDate);
		return taskTDO;
	}
	
	public static Collection<Task> toDTOs(Collection<Task> tasks){
		List<Task> taskDTOList = new ArrayList<>();
		for(Task t: tasks){
			taskDTOList.add(t.toDTO());
		}
		//return projectDTOList.toArray(new Project[0]);
		return taskDTOList;
	}	

	public Task(String description, int estimateDurationDays, int idTask,
			String name, Date startDate, Feature idFeature, List<Test> tests,
			List<Member> members) {
		this.description = description;
		this.estimateDurationDays = estimateDurationDays;
		this.idTask = idTask;
		this.name = name;
		this.startDate = startDate;
		IdFeature = idFeature;
		this.tests = tests;
		this.members = members;
	}

	/* Rest Resource URL*/
	public static String BASE_URL = "http://localhost:8080/SQM2ServiceREST/tasks/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdTask();
        return new AtomLink(restUrl, "get-task");
    }	
	
	public void setLink(AtomLink link){}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 
	
	public int getEstimateDurationDays() {
		return estimateDurationDays;
	}

	public void setEstimateDurationDays(int estimateDurationDays) {
		this.estimateDurationDays = estimateDurationDays;
	}

	@XmlElement
	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Feature getIdFeature() {
		return IdFeature;
	}

	public void setIdFeature(Feature idFeature) {
		IdFeature = idFeature;
	}

	@XmlElementWrapper(name="tests") @XmlElement(name="test") 
	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	@XmlElementWrapper(name="members") @XmlElement(name="member") 
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	

	public List<Optimize> getOptimizations() {
		return optimizations;
	}

	public void setOptimizations(List<Optimize> optimizations) {
		this.optimizations = optimizations;
	}

	public Task(List<Optimize> optimizations) {
		this.optimizations = optimizations;
	}

	public Task(int idTask, String name, Date startDate) {
		this.idTask = idTask;
		this.name = name;
		this.startDate = startDate;
	}
	

	public Task(String description, int estimateDurationDays, int idTask,
			String name, Date startDate, Feature idFeature, List<Test> tests,
			List<Optimize> optimizations, List<Member> members) {
		this.description = description;
		this.estimateDurationDays = estimateDurationDays;
		this.idTask = idTask;
		this.name = name;
		this.startDate = startDate;
		IdFeature = idFeature;
		this.tests = tests;
		this.optimizations = optimizations;
		this.members = members;
	}

	public Task(int idTask, String name, Date startDate, List<Test> tests) {
		this.idTask = idTask;
		this.name = name;
		this.startDate = startDate;
		this.tests = tests;
	}

	public Task(String description, int idTask, String name, Date startDate,
			List<Optimize> optimizations) {
		this.description = description;
		this.idTask = idTask;
		this.name = name;
		this.startDate = startDate;
		this.optimizations = optimizations;
	}
	
	
	
}