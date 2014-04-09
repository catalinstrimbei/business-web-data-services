package org.app.scrum.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.app.scrum.project.Project;

@XmlRootElement(name="project")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ProjectView {
	private Integer projectNo;
	private String name;
	private String restUrl;
	
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
	
	@XmlAttribute(name="link")
	public String getRestUrl() {
		return restUrl;
	}
	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
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
		this.setRestUrl("http://localhost:8080/ScrumREST/projects/project/" + project.getProjectNo());
	}
	
	
	
}
