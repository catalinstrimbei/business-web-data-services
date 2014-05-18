package org.app.service.entities;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class member {

	public static void main(String[] args)
	{
		member obj= new member();
		System.out.println("Member Id: " + obj.generateUniqueKey());
	}
	public String generateUniqueKey(){
		String MemberId=UUID.randomUUID().toString();
		return MemberId;
	}
	private char FirstName;
	private char LastName;
	@Enumerated
	private role Role;
	private char Team;
	@ManyToOne
	public taskExecution m_taskExecution;

	public member(){

	}

	public void finalize() throws Throwable {

	}

	public char getFirstName() {
		return FirstName;
	}

	public void setFirstName(char firstName) {
		FirstName = firstName;
	}

	public char getLastName() {
		return LastName;
	}

	public void setLastName(char lastName) {
		LastName = lastName;
	}

	public role getRole() {
		return Role;
	}

	public void setRole(role role) {
		Role = role;
	}

	public char getTeam() {
		return Team;
	}

	public void setTeam(char team) {
		Team = team;
	}

	public taskExecution getM_taskExecution() {
		return m_taskExecution;
	}

	public void setM_TaskExecution(taskExecution m_taskExecution) {
		this.m_taskExecution = m_taskExecution;
	}

}
