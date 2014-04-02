package org.app.patterns;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.app.scrum.project.Project;
import org.app.scrum.project.Release;

@Named
@RequestScoped
public class ProjectBuilder{
	public static Project buildProiect(Integer projectID, String name, Integer releaseCount){
		
		Project project = new Project(projectID, name + "." + projectID , new Date());
		List<Release> releasesProject = new ArrayList<>();
		
		Date dataPublicare = new Date();
		Long interval =  30l /*zile*/ * 24 /*ore*/ * 60 /*min*/ * 60 /*sec*/ * 1000 /*milisec*/;
		
		for (int i=0; i<=releaseCount-1; i++){
			releasesProject.add(new Release(null, "R: " + project.getProjectNo() + "." + i, 
					new Date(dataPublicare.getTime() + i * interval), project));
		}
		
		project.setReleases(releasesProject);
		
		return project;
	}
}
