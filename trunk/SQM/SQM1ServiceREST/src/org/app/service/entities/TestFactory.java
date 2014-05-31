package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Singleton;



@Singleton
public class TestFactory {

	public Test1 buildTest(Integer testID, String numeTest, Integer ExecutieTestCount){
	Test1 test1=new Test1 ();
	List<ExecutieTest> executieTeste = new ArrayList<>();
	
	Date DataCreare=new Date();
	Long interval = (long) (301*24*60*60*1000);
	for (int i=0; i<ExecutieTestCount-1; i++){
	
	executieTeste.add(new ExecutieTest(null, new Date (DataCreare.getTime() + i * interval), 
			"R:" + test1.getTestId()+ "." + i, test1));
	}
	
	test1.setExecutieteste(executieTeste);
	return test1;
	}
}


