package org.app.service.entities;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;


@Singleton
public class ProjectFactory {
	
	public Projects buildProject(Integer ProjectId, String ProjectTitle, Integer releaseCount){
		Projects project = new Projects();
		List<Releases> releasesProject = new ArrayList<>();
		
	//	Date dataPublicare = new Date();
	//	Long interval = (long) (301 * 24 * 60 * 60 * 1000);
		
		for (int i=0; i<releaseCount-1; i++){
			releasesProject.add(new Releases());
		}
		project.setReleases(releasesProject);
		return project;
		
		
	}
}
