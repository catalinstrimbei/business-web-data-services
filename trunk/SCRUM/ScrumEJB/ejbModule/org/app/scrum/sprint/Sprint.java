package org.app.scrum.sprint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.Feature;

public class Sprint {
	private Integer sprintID;
	private String objective;
	private List<Feature> features = new ArrayList<>();
	private Date startDate;
	private String review;
	
	
	public Integer getSprintID() {
		return sprintID;
	}

	public void setSprintID(Integer sprintID) {
		this.sprintID = sprintID;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// --------------------------------------------------------------------- //
	private Long getEstimatedTimeFeature(Feature feature){
		Long estimatedTimeFeature = 0l; // 0 long
		for(Task t: feature.getTasks()){
			estimatedTimeFeature += t.getEstimatedTime() * 60 * 60 * 1000;
		}
		return estimatedTimeFeature;
	}
	
	private Long getEstimatedTimeSprint() {
		Long estimatedTimeSprint = 0l; // 0 long
		for (Feature c: this.features){
			estimatedTimeSprint += getEstimatedTimeFeature(c);
		}
		
		return estimatedTimeSprint;
	}	
	
	// prop dataFinalizare
	public Date getFinalDate() {
		Long t1 = this.startDate.getTime() + this.getEstimatedTimeSprint();
		
		Date finalDate = new Date(t1);
		
		return finalDate;
	}	
	// --------------------------------------------------------------------- //
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	public Sprint() {
		super();
	}

	public Sprint(Integer sprintID, String objective, Date startDate) {
		super();
		this.sprintID = sprintID;
		this.objective = objective;
		this.startDate = startDate;
	}
}
