package org.app.scrum.project;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;
import org.app.scrum.team.ProjectManager;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Entity
public class Project implements Serializable{
	@Id
	private Integer projectNo;

	@NotNull
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Transient
	private ProjectManager projectManager;
	
	// Added
//	@XmlElement(required = true)
	@OneToMany(mappedBy="project", cascade = ALL, fetch = EAGER, orphanRemoval = false)
	private List<Release> releases = new ArrayList<>();
	
	@OneToOne(cascade = ALL)
	private Release currentRelease;
	
//	@XmlElement
	public Integer getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}
	
//	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	@XmlTransient
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startName) {
		this.startDate = startName;
	}
	public ProjectManager getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}
	public Project(Integer projectNo, String name, Date startDate) {
		super();
		this.projectNo = projectNo;
		this.name = name;
		this.startDate = startDate;
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	@XmlTransient
	@XmlElementWrapper(name = "releases")
    @XmlElement(name = "release")
	public List<Release> getReleases() {
		return releases;
	}
	public void setReleases(List<Release> releases) {
		this.releases = releases;
	}
	
//	@XmlTransient
	public Release getCurrentRelease() {
		return currentRelease;
	}
	public void setCurrentRelease(Release currentRelease) {
		this.currentRelease = currentRelease;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((projectNo == null) ? 0 : projectNo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectNo == null) {
			if (other.projectNo != null)
				return false;
		} else if (!projectNo.equals(other.projectNo))
			return false;
		return true;
	}
	public Project(Integer nrProiect, String numeProiect) {
		super();
		this.projectNo = nrProiect;
		this.name = numeProiect;
	}
	@Override
	public String toString() {
		return "Project [projectNo=" + projectNo + ", name=" + name + "]";
	}
	
	/* Rest Resource URL*/
	private static String BASE_URL = "http://localhost:8080/ScrumREST/projects/project/";
	@XmlElement(name = "link", namespace = AtomLink.ATOM_NAMESPACE)
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getProjectNo();
        return new AtomLink(restUrl, "get-project");
    }	
	
	public Project getProjectDTO(){
		return new Project(projectNo, name);
	}
}
