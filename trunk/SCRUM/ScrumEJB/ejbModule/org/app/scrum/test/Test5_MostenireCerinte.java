package org.app.scrum.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.app.scrum.CategorieCerinta;
import org.app.scrum.Cerinta;
import org.app.scrum.CerintaFunctionala;
import org.app.scrum.CerintaTehnica;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.sprint.Task;

public class Test5_MostenireCerinte {
	
	public static void main(String[] args) {

		Cerinta c1 = new CerintaTehnica(1, "Cerinta 1", "cerinta test");
		c1.setCategorie(CategorieCerinta.TEHNICA);
		
		CerintaFunctionala c2 = new CerintaFunctionala(1, "Cerinta 2", "cerinta test mostenire", "basic", "use case generic");
//		c2.setCategorie(CategorieCerinta.FUNCTIONALA);
		
		System.out.println("c1: " + c1);
		System.out.println("c2: " + c2);
	}
	
}
