package org.app.service.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import sun.util.calendar.BaseCalendar.Date;

	@Entity
	public class Releases implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	//private static final CascadeType[] ALL = null;
	
	@Id @GeneratedValue
	private Integer IdRelease;
	private String CodeName; 
	private String indicative;
	private String SP;
	@Temporal(TemporalType.DATE)
	private Date ReleaseDate;
	
	@ManyToOne
	private Projects project;
	@OneToMany(mappedBy = "releases" )
	private List<Tasks> tasks = new ArrayList<>();
	
	public Releases toDTO(){
		return new Releases();
		}
	/*
	 * 
	 */
	public static List<Releases> toDTOList(List<Releases> releases){
		List<Releases> releaseDTOList = new ArrayList<>();
		for (Releases r: releases){
			releaseDTOList.add(r.toDTO());
		}
		return releaseDTOList;
	}
	
	public Integer getIdRelease() {
		return IdRelease;
	}


	public void setIdRelease(Integer idRelease) {
		IdRelease = idRelease;
	}

	public Date getReleaseDate() {
		return ReleaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		ReleaseDate = releaseDate;
	}


	public Projects getProject() {
		return project;
	}


	public void setProject(Projects project) {
		this.project = project;
	}


	public List<Tasks> getTasks() {
		return tasks;
	}


	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}


//	public static CascadeType[] getAll() {
	//	return ALL;
	//}


	public String getSP() {
		return SP;
	}


	public void setSP(String sP) {
		SP = sP;
	}


	public String getCodeName() {
		return CodeName;
	}


	public void setCodeName(String CodeName) {
		this.CodeName = CodeName;
	}

	 public Projects getProjects() {
	        return project;
	    }
	    
	    public void setProjects(Projects project) {
	        this.project = project;
	    }

	    public String toString() {
	        return "Releases id: " + getIdRelease() + " name: " + getCodeName() + 
	               " with " + getProjects();
	    }


		public String getIndicative() {
			return indicative;
		}


		public void setIndicative(String indicative) {
			this.indicative = indicative;
		}


	//	@Override
	//	public char charAt(int arg0) {
			// TODO Auto-generated method stub
	//		return 0;
	//	}


	//	@Override
	//	public int length() {
			// TODO Auto-generated method stub
	//		return 0;
	//	}


	//	@Override
	//	public CharSequence subSequence(int arg0, int arg1) {
			// TODO Auto-generated method stub
	//		return null;
		}


