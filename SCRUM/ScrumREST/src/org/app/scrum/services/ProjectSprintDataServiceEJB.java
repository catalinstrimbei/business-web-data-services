package org.app.scrum.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.app.patterns.EntityRepositoryBase;
import org.app.patterns.ProjectBuilder;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;

@Path("projects") /* http://localhost:8080/ScrumREST/projects */
@Stateless @LocalBean
public class ProjectSprintDataServiceEJB extends EntityRepositoryBase<Project> 
	implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJB.class.getName());
	
	
	public Project createNewProject(){
		Project project = ProjectBuilder.buildProiect(1001, "NEW Project", 3);
		this.add(project);
		return getProjectAggregateDTO(project);
	}
	
//	@GET
//	@Produces("application/xml")
//	public ProjectView[] getProjectList(){
//		return getProjectViewList(this.toCollection()).toArray(new ProjectView[0]);
//	}
	
	@GET
	@Produces("application/xml")
	public Project[] getProjectList(){
		return getProjectDTOList();
	}	
	
	public Project addProject(Project project){
		// restore project
		// merge projectDTO with project
		
		// save project
		project = this.add(project);
		return getProjectAggregateDTO(project);
	}
	
	public ProjectView getProjectViewById(Integer id){
		Project project = super.getById(id);
		return new ProjectView(project);
	}
	
	@GET @Path("project/{id}") @Produces("application/xml")
	public Project getProjectById(@PathParam("id") Integer id){
		Project project = super.getById(id);
		return getProjectAggregateDTO(project);
	}	
	
	/* DTO assembler business logic */
	private Project getProjectAggregateDTO(Project project){
		if (project == null)
			return null;
		
		List<Release> releasesDTO = new ArrayList<>();
		releasesDTO.addAll(project.getReleases());
		project.setReleases(releasesDTO);
		
		return project;
	}
	
	private List<Project> getProjectListDTO(Collection<Project> projects){
		for(Project p: projects){
			p.setCurrentRelease(null);
			p.setProjectManager(null);
			p.setReleases(null);
		}
		return new ArrayList<>(projects);
	}

	private List<ProjectView> getProjectViewList(Collection<Project> projects){
		List<ProjectView> projectList = new ArrayList<>();
		for(Project p: projects){
			projectList.add(new ProjectView(p));
		}
		return projectList;
	}	
	
	private Project[] getProjectDTOList(){
		Project[] projects = this.toCollection().toArray(new Project[0]);
		for(Project p: projects){
			p.setCurrentRelease(null);
			p.setProjectManager(null);
			p.setReleases(null);
		}
		return projects;
	}	
	
	// dummy validation rest
	@GET @Path("/test")
	@Produces("text/html")
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}
}


