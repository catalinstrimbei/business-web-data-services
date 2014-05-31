package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.02
 */
@Entity
public class Bug implements Serializable, IManagementBug {

	@Id
	@GeneratedValue
	private String idBug;
	private String titlu;
	private Enum<TipBugEnum> tipBug;
	private Enum<StatusEnum> status;
	private Enum<PrioritateEnum> prioritate;
	Integer nrAtasamente;

	@Temporal(TemporalType.DATE)
	private Date dataAdaugare;

	@Temporal(TemporalType.DATE)
	private Date dataInchidere;

	@Temporal(TemporalType.DATE)
	private Date dataScadenta;

	private String descriere;

	// DTO
	public Bug toDTO() {
		Bug bugDTO = new Bug(idBug, titlu, tipBug, status, prioritate,
				dataAdaugare, dataScadenta, descriere, nrAtasamente);
		return bugDTO;
	}

	public static Bug toDTOAggregate(Bug bug) {
		if (bug == null)
			return null;
		Bug bugDTO = bug.toDTO();
		List<Comentariu> comentariuDTO = Comentariu.toDTOList(bug
				.getComentarii());
		bugDTO.setComentarii(comentariuDTO);
		return bugDTO;
	}

	// DTOs
	public static Collection<Bug> toDTOs(Collection<Bug> collection) {
		List<Bug> bugDTOList = new ArrayList<>();

		for (Bug bugIter : collection) {
			bugDTOList.add(bugIter.toDTO());
		}
		return bugDTOList;
	}

	@OneToMany(mappedBy = "bug", cascade = ALL, fetch = EAGER, orphanRemoval = false)
	private List<Atasament> atasamente = new ArrayList<>();

	@OneToMany(mappedBy = "bug", cascade = ALL, fetch = EAGER, orphanRemoval = false)
	private List<Comentariu> comentarii;

	public Integer calc_nrAtasamente(String idBug) {
		// implementare metada
		return nrAtasamente;

	};

	public Bug() {

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param bug
	 */
	public StatusEnum getStatus(Bug bug) {
		return null;
	}

	/**
	 * 
	 * @param bug
	 */
	public Comentariu getComentariu(Bug bug) {
		return null;
	}

	/**
	 * 
	 * @param bug
	 */
	public Atasament getAtasament(Bug bug) {
		return null;
	}

	public void adauga() {

	}

	public Bug create() {
		return null;
	}

	public void adaugaAtasamnet() {

	}

	/**
	 * 
	 * @param bug
	 * @param comentariu
	 */
	public void adaugaComentariu(Bug bug, Comentariu comentariu) {

	}

	/**
	 * 
	 * @param bug
	 * @param status
	 */
	public void modificaStatus(Bug bug, StatusEnum status) {

	}

	/**
	 * 
	 * @param bug
	 */
	public void select(Bug bug) {

	}

	/**
	 * 
	 * @param bug
	 * @param prioritate
	 */
	public void setPrioritate(Bug bug, PrioritateEnum prioritate) {

	}

	/**
	 * 
	 * @param bug
	 */
	public void sterge(Bug bug) {

	}

	@Override
	public void notifica() {
		// TODO Auto-generated method stub

	}

	public String getIdBug() {
		return idBug;
	}

	public void setIdBug(String idBug) {
		this.idBug = idBug;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public Enum<TipBugEnum> getTipBug() {
		return tipBug;
	}

	public void setTipBug(Enum<TipBugEnum> tipBug) {
		this.tipBug = tipBug;
	}

	public Enum<StatusEnum> getStatus() {
		return status;
	}

	public void setStatus(Enum<StatusEnum> status) {
		this.status = status;
	}

	public Enum<PrioritateEnum> getPrioritate() {
		return prioritate;
	}

	public void setPrioritate(Enum<PrioritateEnum> prioritate) {
		this.prioritate = prioritate;
	}

	public Date getDataAdaugare() {
		return dataAdaugare;
	}

	public void setDataAdaugare(Date dataAdaugare) {
		this.dataAdaugare = dataAdaugare;
	}

	public Date getDataInchidere() {
		return dataInchidere;
	}

	public void setDataInchidere(Date dataInchidere) {
		this.dataInchidere = dataInchidere;
	}

	public Date getDataScadenta() {
		return dataScadenta;
	}

	public void setDataScadenta(Date dataScadenta) {
		this.dataScadenta = dataScadenta;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public List<Atasament> getAtasamente() {
		return atasamente;
	}

	public void setAtasamente(List<Atasament> atasamente) {
		this.atasamente = atasamente;
	}

	public List<Comentariu> getComentarii() {
		return comentarii;
	}

	public void setComentarii(List<Comentariu> comentarii) {
		this.comentarii = comentarii;
	}

	public Integer getNrAtasamente() {
		return nrAtasamente;
	}

	public void setNrAtasamente(Integer nrAtasamente) {
		this.nrAtasamente = nrAtasamente;
	}

	public Bug(String idBug, String titlu, Enum<TipBugEnum> tipBug,
			Enum<StatusEnum> status, Enum<PrioritateEnum> prioritate,
			Date dataAdaugare, Date dataInchidere, Date dataScadenta,
			String descriere, List<Atasament> atasamente, Integer nrAtasamente,
			List<Comentariu> comentarii) {
		super();
		this.idBug = idBug;
		this.titlu = titlu;
		this.tipBug = tipBug;
		this.status = status;
		this.prioritate = prioritate;
		this.dataAdaugare = dataAdaugare;
		this.dataInchidere = dataInchidere;
		this.dataScadenta = dataScadenta;
		this.descriere = descriere;
		this.atasamente = atasamente;
		this.comentarii = comentarii;
		this.nrAtasamente = nrAtasamente;
	}

	public Bug(String idBug, String titlu, Enum<TipBugEnum> tipBug,
			Enum<StatusEnum> status, Enum<PrioritateEnum> prioritate,
			Date dataAdaugare, Date dataScadenta, String descriere,
			Integer nrAtasamente) {
		super();
		this.idBug = idBug;
		this.titlu = titlu;
		this.tipBug = tipBug;
		this.status = status;
		this.prioritate = prioritate;
		this.dataAdaugare = dataAdaugare;
		this.dataScadenta = dataScadenta;
		this.descriere = descriere;
		this.nrAtasamente = nrAtasamente;
	}

	public Bug(String idBug, String titlu) {
		this.idBug = idBug;
		this.titlu = titlu;
	}

}