package org.app.service.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
@Entity
public class TeamMembers {

	private String dataEntry;
	@Id@GeneratedValue
	private Team idTeam;
	private Member m_Member;

	public TeamMembers(){

	}

	public void finalize() throws Throwable {

	}

	public String getDataEntry() {
		return dataEntry;
	}

	public void setDataEntry(String dataEntry) {
		this.dataEntry = dataEntry;
	}

	public Team getIdTeam() {
		return idTeam;
	}

	public void setIdTeam(Team idTeam) {
		this.idTeam = idTeam;
	}

	public Member getM_Member() {
		return m_Member;
	}

	public void setM_Member(Member m_Member) {
		this.m_Member = m_Member;
	}

	public TeamMembers(String dataEntry, Team idTeam, Member m_Member) {
		this.dataEntry = dataEntry;
		this.idTeam = idTeam;
		this.m_Member = m_Member;
	}
	

}