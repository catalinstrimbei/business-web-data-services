package org.app.scrum.sprint;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.app.scrum.team.Member;

public class Task {
	private Integer idTask;
	private String denumire;
	private String descriere;
	
	// timing
	private Date dataStart;
	private Integer timpEstimat; // initial, exprimat in ore
	private Integer timpRamas; // actualizat, exprimat in ore
	private Integer timpEfectiv;	
	private TaskStatus statusTask;
	
	// assessment
	private Member responsabil;
	private TaskCategory categorieTask;
	
	// Burn down
	private Map<Date, Integer> burnDownRecords = new HashMap<>();
	
	public Integer getIdTask() {
		return idTask;
	}
	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
	public Date getDataStart() {
		return dataStart;
	}
	public void setDataStart(Date dataStart) {
		this.dataStart = dataStart;
	}
	public Integer getTimpEstimat() {
		return timpEstimat;
	}
	public void setTimpEstimat(Integer timpEstimat) {
		this.timpEstimat = timpEstimat;
	}
	public Integer getTimpRamas() {
		return timpRamas;
	}
	public void setTimpRamas(Integer timpRamas) {
		this.timpRamas = timpRamas;
		burnDownRecords.put(new Date(), timpRamas);
	}
	public TaskStatus getStatusTask() {
		return statusTask;
	}
	public void setStatusTask(TaskStatus statusTask) {
		this.statusTask = statusTask;
	}
	public TaskCategory getCategorieTask() {
		return categorieTask;
	}
	public void setCategorieTask(TaskCategory categorieTask) {
		this.categorieTask = categorieTask;
	}
	public Integer getTimpEfectiv() {
		return timpEfectiv;
	}
	public void setTimpEfectiv(Integer timpEfectiv) {
		this.timpEfectiv = timpEfectiv;
	}
	
	
	// interfete si polimorfism 
	public Member getResponsabil() {
		return responsabil;
	}
	public void setResponsabil(Member responsabil) {
		this.responsabil = responsabil;
	}
	public Map<Date, Integer> getBurnDownRecords() {
		return burnDownRecords;
	}
	public void setBurnDownRecords(Map<Date, Integer> burnDownRecords) {
		this.burnDownRecords = burnDownRecords;
	}
	
	public Task(Integer idTask, String denumire, String descriere,
			Date dataStart, Integer timpEstimat, TaskStatus statusTask,
			TaskCategory categorieTask) {
		super();
		this.idTask = idTask;
		this.denumire = denumire;
		this.descriere = descriere;
		this.dataStart = dataStart;
		this.timpEstimat = timpEstimat;
		this.statusTask = statusTask;
		this.categorieTask = categorieTask;
	}
	public Task() {
		super();
	}
	public Task(Integer idTask, String denumire, String descriere,
			Date dataStart, Integer timpEstimat, Integer timpRamas,
			Integer timpEfectiv, TaskStatus statusTask, Member responsabil,
			TaskCategory categorieTask) {
		super();
		this.idTask = idTask;
		this.denumire = denumire;
		this.descriere = descriere;
		this.dataStart = dataStart;
		this.timpEstimat = timpEstimat;
		this.timpRamas = timpRamas;
		this.timpEfectiv = timpEfectiv;
		this.statusTask = statusTask;
		this.responsabil = responsabil;
		this.categorieTask = categorieTask;
	}
	public Task(Integer idTask, String denumire, Date dataStart,
			Integer timpEstimat) {
		super();
		this.idTask = idTask;
		this.denumire = denumire;
		this.dataStart = dataStart;
		this.timpEstimat = timpEstimat;
	}
	public Task(Integer idTask, String denumire, Date dataStart,
			Integer timpEstimat, Member responsabil) {
		super();
		this.idTask = idTask;
		this.denumire = denumire;
		this.dataStart = dataStart;
		this.timpEstimat = timpEstimat;
		this.responsabil = responsabil;
	}
	
	
}