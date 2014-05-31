package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ExecutieTest")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class ExecutieTest<statusRezultat> implements Serializable {

	@Id
	@GeneratedValue
	private String idExecutieTest;

	public ExecutieTest(String idExecutieTest) {
		super();
		this.idExecutieTest = idExecutieTest;
	}

	@XmlElement
	public String getIdExecutieTest() {
		return idExecutieTest;
	}

	public void setIdExecutieTest(String string) {
		this.idExecutieTest = string;
	}

	private String descriereRezultat;

	@Temporal(TemporalType.DATE)
	private Date dataOraStartEnd;

	@ManyToOne
	private Test1 test1;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Erori> erori = new ArrayList<>();

	public ExecutieTest() {
		super();
	}

	public ExecutieTest(String descriereRezultat, Date dataOraStartEnd) {
		super();
		this.descriereRezultat = descriereRezultat;
		this.dataOraStartEnd = dataOraStartEnd;
	}

	public ExecutieTest(String idExecutieTest2, Date dataOraStartEnd2,
			String descriereRezultat2, Object dto) {
		
	}
	
	
	
	@XmlElement
	public String getDescriereRezultat() {
		return descriereRezultat;
	}

	public void setDescriereRezultat(String descriereRezultat) {
		this.descriereRezultat = descriereRezultat;
	}

	public Date getDataOraStartEnd() {
		return dataOraStartEnd;
	}

	public void setDataOraStartEnd(Date dataOraStartEnd) {
		this.dataOraStartEnd = dataOraStartEnd;
	}

	public void finalize() throws Throwable {

	}

	public statusRezultat StatusRezultat() {
		return null;
	}

	public ExecutieTest toDTO() {
		return new ExecutieTest(idExecutieTest, dataOraStartEnd,
				descriereRezultat, test1.toDTO());

	}

	public static List<ExecutieTest> toDTOList(List<ExecutieTest> executieTest) {
		List<ExecutieTest> executieTestDTOList = new ArrayList<>();
		for (ExecutieTest et : executieTest) {
			executieTestDTOList.add(et.toDTO());
		}
		return executieTestDTOList;
	}

	public static String BASE_URL= Test1.BASE_URL;
	@XmlElement(name="link")
	public AtomLink getLink() throws Exception {
		String restUrl= BASE_URL
				+ this.getTest1().getIdTest()
				+ "/executieteste/"
				+ this.getIdExecutieTest();
		return new AtomLink(restUrl, "get-ExecutieTest");
		
	}
	@XmlElement
	private Test1 getTest1() {
		return test1;
	}
	
	
}
