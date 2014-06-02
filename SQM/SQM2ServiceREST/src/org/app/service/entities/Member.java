

package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
public class Member {

	private String fisrtName;
	@Id@GeneratedValue
	private int idMember;
	private int lastNamerole;
	private Role m_Role;

	@ManyToOne
	private Task task;
	
	public Member(){

	}

	public void finalize() throws Throwable {

	}

	public Member(String fisrtName, int idMember, int lastNamerole,
			Role m_Role, Task task) {
		this.fisrtName = fisrtName;
		this.idMember = idMember;
		this.lastNamerole = lastNamerole;
		this.m_Role = m_Role;
		this.task = task;
	}

	
	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}
  
	public int getIdMember() {
		return idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public int getLastNamerole() {
		return lastNamerole;
	}

	public void setLastNamerole(int lastNamerole) {
		this.lastNamerole = lastNamerole;
	}

	public Role getM_Role() {
		return m_Role;
	}

	public void setM_Role(Role m_Role) {
		this.m_Role = m_Role;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}


}