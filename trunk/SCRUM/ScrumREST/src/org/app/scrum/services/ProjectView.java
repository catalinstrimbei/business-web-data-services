package org.app.scrum.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.patterns.AtomLink;
import org.app.scrum.project.Project;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectView {
	private static String BASE_URL = "http://localhost:8080/ScrumREST/projects/project/";
	
	private Integer projectNo;
	private String name;
	
	public Integer getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProjectView(Integer projectNo, String name) {
		super();
		this.projectNo = projectNo;
		this.name = name;
	}
	public ProjectView() {
		super();
	}
	
	public ProjectView(Project project) {
		this.setProjectNo(project.getProjectNo());
		this.setName(project.getName());
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
		ProjectView other = (ProjectView) obj;
		if (projectNo == null) {
			if (other.projectNo != null)
				return false;
		} else if (!projectNo.equals(other.projectNo))
			return false;
		return true;
	}	
	
	/* Rest Resource URL*/
	@XmlElement(name = "link", namespace = AtomLink.ATOM_NAMESPACE)
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getProjectNo();
        return new AtomLink(restUrl, "get-project");
    }
}

// http://www.w3.org/2005/Atom
