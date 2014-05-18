package org.app.scrum.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.app.scrum.project.Project;
import org.jboss.resteasy.util.GenericType;

@ManagedBean
public class FormProjects {
	private static Logger logger = Logger.getLogger(FormProjects.class.getName());
			
	private List<Project> projects = new ArrayList<>();
	private Project project = null;
	
	public FormProjects() throws Exception {
		super();
		init();
	}
	
	private void init() throws Exception{
		System.out.println("DEBUG START FORM ...");
		RESTfullResource<Collection<Project>> resource = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/",
				"application/xml",
				new GenericType<Collection<Project>>(){});			
		
		Collection<Project> projectsRest = resource.get();	
		this.projects.addAll(projectsRest);
		
		for(Project p: projects)
			logger.info("DEBUG testGetProjects: queried project" + p);
		
		this.project = this.projects.get(0);
		
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Project> getProjects() {
		return projects;
	}	
	
	public Integer getProjectNo(){
		System.out.println("DEBUG getNrProiect: " + this.project.getProjectNo());
		return this.project.getProjectNo();
	}
	
	public void setProjectNo(Integer projectNo){
		System.out.println("DEBUG setNrProiect: " + projectNo);
		Integer idx = this.projects.indexOf(new Project(projectNo, null, null));
		this.project = this.projects.get(idx);
	}
	
	// pentru selectie din grid
	public void selectProject(ActionEvent evt){
		Integer selectedId = Integer.valueOf(evt.getComponent().getAttributes().get("selectedId").toString());
		this.setProjectNo(selectedId);
	}
}
