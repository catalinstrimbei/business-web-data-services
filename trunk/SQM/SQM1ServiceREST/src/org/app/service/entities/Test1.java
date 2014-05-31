package org.app.service.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name="test1")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Test1 implements Serializable {


	@Id
	@GeneratedValue
	private Integer idTest;

	@Temporal(TemporalType.DATE)
	private Date dataCreare;

	@OneToMany(mappedBy = "test1", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	private List<ExecutieTest> executieteste = new ArrayList<>();

	private String numeTest;

	
	@XmlElement
	public Integer getIdTest() {
		return idTest;
	}

	public void setIdTest(Integer idTest) {
		this.idTest = idTest;
	}

	public Date getDataCreare() {
		return dataCreare;
	}

	public void setDataCreare(Date dataCreare) {
		this.dataCreare = dataCreare;
	}

	
	@XmlElementWrapper (name="executieteste") @XmlElement(name="ExecutieTest")
	public List<ExecutieTest> getExecutieteste() {
		return executieteste;
	}

	public void setExecutieteste(List<ExecutieTest> executieteste) {
		this.executieteste = executieteste;
	}

	public String getNumeTest() {
		return numeTest;
	}

	public void setNumeTest(String numeTest) {
		this.numeTest = numeTest;
	}

	public Test1(Integer idTest, Date dataCreare,
			List<ExecutieTest> executieteste, String numeTest) {
		super();
		this.idTest = idTest;
		this.dataCreare = dataCreare;
		this.executieteste = executieteste;
		this.numeTest = numeTest;
	}

	public Test1() {
		super();
	}

	public static Test1 toDTOAggregate(Test1 test1) {
		if (test1 == null)
			return null;
		Test1 testDTO = test1.toDTO();
		List<ExecutieTest> executieTestDTO = ExecutieTest.toDTOList(test1
				.getExecutieteste());
		testDTO.setExecutieteste(executieTestDTO);
		return testDTO;
	}

	public Test1 toDTO() {
		Test1 testDTO = new Test1(idTest, dataCreare, executieteste, numeTest);
		return testDTO;
	}

	public String getTestId() {
		// TODO Auto-generated method stub
		return null;
	}


	public static String BASE_URL= "http://localhost:8080/SQM1ServiceREST/teste/";
	@XmlElement (name= "link")
	public AtomLink getLink() throws Exception {
		String restUrl= BASE_URL +this.getIdTest();
		return new AtomLink(restUrl, "get-test1");
	
	
	}

	public static Collection<Test1> toDTOs(Collection<Test1> collection) {
		// TODO Auto-generated method stub
		return null;
	}

	//public static void main(String[] args) {
//}
}

/*
 * public Test(Integer testID, String string, Date date) { // TODO
 * Auto-generated constructor stub } public Integer getIdTest() { return idTest;
 * } public void setIdTest(Integer idTest) { this.idTest = idTest; } public
 * String getNumeTest() { return numeTest; } public void setNumeTest(String
 * numeTest) { this.numeTest = numeTest; } public List<Test> getListaTeste() {
 * return ListaTeste; } public void setListaTeste(List<Test> listaTeste) {
 * ListaTeste = listaTeste; } private String numeTest; List<Test>ListaTeste=new
 * ArrayList<Test>(); public static void adaugaTest(){ }
 */

