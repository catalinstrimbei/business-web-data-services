package org.app.scrum.team;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member 
	implements Comparable<Member>, Serializable{
	
	@Id
	private Integer memberID;
	
	private String name;
	private Role role;
		
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Member() {
		super();
	}
	public Member(Integer memberID, String name) {
		super();
		this.memberID = memberID;
		this.name = name;
	}
	public Member(Integer memberID, String name, Role role) {
		super();
		this.memberID = memberID;
		this.name = name;
		this.role = role;
	}


	// caz supra-incarcare
	private String abilities;
	
	public String getAbilities() {
		return abilities;
	}
	public void setAbilities(String abilities) {
		this.abilities = abilities;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((memberID == null) ? 0 : memberID.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
//		if (getClass() != obj.getClass())
//			return false;
		Member other = (Member) obj;
		if (memberID == null) {
			if (other.memberID != null)
				return false;
		} else if (!memberID.equals(other.memberID))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Member other) {
		if (this.equals(other))
			return 0;
		return this.getMemberID().compareTo(other.getMemberID());
	}
	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", name=" + name + "]";
	}	
	
	public enum Role{
		MANAGER, PRODUCT_OWNER, SCRUM_MASTER, DEVELOPER, ANALYST, TESTER;
	}	
}