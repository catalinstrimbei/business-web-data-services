package org.app.scrum.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.app.patterns.DataRepositoryBean;
import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.project.Feature;
import org.app.scrum.project.Project;
import org.app.scrum.project.ProjectBuilder;
import org.app.scrum.project.Release;

@Path("projects") /* http://localhost:8080/ScrumREST/projects */
@Stateless @LocalBean
public class ProjectSprintDataServiceEJB extends EntityRepositoryBase<Project> 
	implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJB.class.getName());
	
	@Inject @DataRepositoryBean(entityType=Release.class)
	private EntityRepository<Release> releaseEntityRepository;
	
	public Project createNewProject(){
		Project project = ProjectBuilder.buildProiect(1001, "NEW Project", 3);
		this.add(project);
		return getProjectDTOAggregate(project);
	}
	
	@GET @Produces("application/xml")
	public Project[] getProjectList(){
		return getProjectDTOList();
	}	
	
	@GET @Path("project/{id}") @Produces("application/xml")
	public Project getProjectById(@PathParam("id") Integer id){
		Project project = super.getById(id);
		return getProjectDTOAggregate(project);
	}	
	
	@GET @Path("project/{projectid}/release/{releaseid}") @Produces("application/xml")
	public Release getReleaseById(
			@PathParam("projectid") Integer projectid,
			@PathParam("releaseid") Integer releaseid){
		
		logger.info("*** DEBUG: accessed URL " + "project/" + projectid + "/release/" + releaseid);
		Release release = releaseEntityRepository.getById(releaseid);
		logger.info("*** DEBUG: accessed release from releaseEntityRepository: *** " + release);
		
		return release.getReleaseDTO();
	}
	
	
	public Project addProject(Project project){
		// restore project
		// merge projectDTO with project
		
		// save project
		project = this.add(project);
		return getProjectDTOAggregate(project);
	}
	
	public ProjectView getProjectViewById(Integer id){
		Project project = super.getById(id);
		return new ProjectView(project);
	}
	
	/* DTO assembler business logic */
	private Project getProjectDTOAggregate(Project project){
		if (project == null)
			return null;
		Project projectDTO = project.getProjectDTO();
		logger.info("+++++++++++++ DEBUG projectDTOAggregate - releases before " + project.getReleases());
		
		List<Release> releasesDTO = getReleaseDTOList(project.getReleases());
		
		logger.info("+++++++++++++ DEBUG projectDTOAggregate - releases after" + releasesDTO);
		projectDTO.setReleases(releasesDTO);
		
		return projectDTO;
	}
	
	private Project[] getProjectDTOList(){
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: this.toCollection()){
			projectDTOList.add(p.getProjectDTO());
		}
		return projectDTOList.toArray(new Project[0]);
	}	
	
	private List<Release> getReleaseDTOList(List<Release> releases){
		List<Release> releaseDTOList = new ArrayList<>();
		for(Release r: releases){
			releaseDTOList.add(r.getReleaseDTO());
		}
		return releaseDTOList;
	}
	
	/* dummy validation rest */
	@GET @Path("/test")
	@Produces("text/html")
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}
}