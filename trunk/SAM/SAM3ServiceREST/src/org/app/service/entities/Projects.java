package org.app.service.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;
import sun.util.calendar.BaseCalendar.Date;

import org.app.service.entities.Releases;

	@Entity
	public class Projects implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1388711985398106321L;

		//private static final FetchType EAGER = null;
		
	@Id @GeneratedValue
	private Integer ProjectId ;
	private String ProjectTitle;		
	@Temporal(TemporalType.DATE)
	private Date StartDate;	
	@OneToMany(mappedBy="project", cascade=ALL, orphanRemoval = false)
	private List<Releases> releases = new ArrayList<Releases>(); 
	

	public Projects(){
	}

		public Integer getProjectId() {
		return ProjectId;
	}

	public void setProjectId(Integer projectId) {
		ProjectId = projectId;
	}

	public String getProjectTitle() {
		return ProjectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		ProjectTitle = projectTitle;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public String toString() {
		return "Project id: " + getProjectId() + ", name: " + getProjectTitle();
	}

	public void setReleases(List<Releases> releasesProject) {
		// TODO Auto-generated method stub
		
	}

	
	public Projects toDTO() {
		Projects projectDTO = new Projects();
		//projectDTO.setReleases(null);
		return projectDTO;
	}
	
	public static Projects toDTOAggregate(Projects project) {
		if (project == null)
			return null;
		Projects projectDTO = project.toDTO();
		List<Releases> releasesDTO = Releases.toDTOList(project.getReleases());
		projectDTO.setReleases(releasesDTO);
		return projectDTO;
	}

	public List<Releases> getReleases() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Collection<Projects> toDTOs(Collection<Projects> collection) {
		// TODO Auto-generated method stub
		return null;
	}


	}

