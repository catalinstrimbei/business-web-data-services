package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;


    @Entity
    public class Member {
	
	@Id
	private Integer MemberId;
	
	private String FirstName;
	private String LastName;
	
    @Enumerated
	private String Role;
	private String Team;
	
	public Integer getMemberId() {
		return MemberId;
	}

	public void setMemberId(Integer memberId) {
		MemberId = memberId;
	}

	public TaskExecution getTaskExecution() {
		return taskExecution;
	}

	public void setTaskExecution(TaskExecution taskExecution) {
		this.taskExecution = taskExecution;
	}


	@OneToOne
	private TaskExecution taskExecution;

	public void finalize() throws Throwable {

	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	

	public String getTeam() {
		return Team;
	}

	public void setTeam(String team) {
		Team = team;
	}

	public Member() {
		super();
	}
	
	

}