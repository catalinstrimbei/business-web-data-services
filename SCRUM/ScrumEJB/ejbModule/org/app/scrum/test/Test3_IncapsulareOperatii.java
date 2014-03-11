package org.app.scrum.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.Cerinta;
import org.app.scrum.CerintaFunctionala;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.sprint.Task;

public class Test3_IncapsulareOperatii {
	
	public static void main(String[] args) {
		Date d1 = new Date();
		Long interval1 = 4l * 60 * 60 * 1000;
		
		Long df = d1.getTime() + interval1;
		
		Date d2 = new Date();
		d2.setTime(df);
		
		System.out.println(d1.getTime());
		System.out.println(d2.getTime());
		
		
		
		// Clasa Sprint: implementare operatie private getDurataEstimata()		
		// Clasa Sprint: implementare proprietate dataFinalizare prin op. publica getDataFinalizare()
		
		// Instantiere Sprint
		Sprint s1 = new Sprint();
		s1.setDataStart(new Date());
		
		List<Cerinta> cerinte = new ArrayList<>();
		Cerinta c1 = new CerintaFunctionala();
		
		
		
		List<Task> taskuri = new ArrayList<>();
		Task t1 = new Task();
		t1.setDataStart(new Date());
		t1.setTimpEstimat(10);
		
		Task t2 = new Task();
		t2.setDataStart(new Date());
		t2.setTimpEstimat(12);
		
		taskuri.add(t1);
		taskuri.add(t2);
		
		c1.setTaskuri(taskuri);
		cerinte.add(c1);
		
		s1.setCerinte(cerinte);
		
		// Access proprietate (get)dataFinalizare
		
		System.out.println("Data start:      " + s1.getDataStart());
		System.out.println("Data finalizare: " + s1.getDataFinalizare());
		
		
		
		
	}
}
