package org.app.service.entities;
import javax.persistence.GeneratedValue.*;
import javax.persistence.*;

import java.util.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Feedback  implements Serializable {
	
	  @Id @GeneratedValue 
	  private Integer feedbackId;
	  private String description;
	  
	 
	@OneToOne(mappedBy="feedback")
	  private Comunication comunication;

	  @ManyToOne
	  private ReportFeedback reportFeedback;
	  
	  
	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public Feedback()
	{}
	
	public Feedback(Integer feedbackId, String description, ReportFeedback reportFeedback) {
		super();
		this.feedbackId = feedbackId;
		this.description = description;
		this.reportFeedback = reportFeedback;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Comunication getComunication() {
		return comunication;
	}

	public void setComunication(Comunication comunication) {
		this.comunication = comunication;
	}
	 public ReportFeedback getReportFeedback() {
			return reportFeedback;
		}

		public void setReportFeedback(ReportFeedback reportFeedback) {
			this.reportFeedback = reportFeedback;
		}

}
