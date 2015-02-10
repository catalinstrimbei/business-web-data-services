package org.app.service.entities;


import javax.persistence.*;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class ReportFeedback implements Serializable  {
	
	 @Id @GeneratedValue 
	 private Integer reportId;
	 private String reportName;
	 private String reportType;
		 
	@Temporal(TemporalType.DATE)
	private Date dateOfGeneration;
		 
	@OneToMany
	private List<Feedback> feedback=new ArrayList<Feedback>();	 
	 public Integer getReportId() {
		return reportId;
	}
	 public ReportFeedback ()
	 {}
	public ReportFeedback (Integer reportId,String reportName,String reportType,Date date){
	
		this.reportId=reportId;
		this.reportName=reportName;
		this.reportType=reportType;
		this.dateOfGeneration=date;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Date getDateOfGeneration() {
		return dateOfGeneration;
	}

	public void setDateOfGeneration(Date dateOfGeneration) {
		this.dateOfGeneration = dateOfGeneration;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

}
