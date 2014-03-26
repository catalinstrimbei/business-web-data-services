package org.app.scrum.test;

public class TextX_Scrum_Works {

	public static void main(String[] args) {
		// - Creare proiect nou
		
			// - Stabileste ProductBacklog pentru proiect: proiect.setProductBacklog(productBacklog)
			
			// - Stabileste Echipa pentru proiect: proiect.setEchipa(echipa)
		
		// + Planifica desfasurare proiect
		
			// - Creare release nou cu ReleaseBacklog: transfer din main Backlog (ProductBacklog)
		
		// + Proiect Start release: proiect.startRelease(release)
			
			// - Stabilire release curent: proiect.setReleaseCurent(release)
			
			// + Planificare release curent
				
				// - Creare sprint nou cu SprintBacklog: preluare din Release Backlog: sprint.setSprintBacklog(sprintBacklog)
				
				// - Creare task nou pentru Story din SprintBacklog cu asignare catre membru echipa
			
			// + Release start Sprint execution
			
				// + Sprint start task
				
					// - Task actualizeaza status in progress
					
					// - Task actualizeaza staus complete/done
				
				// - Sprint inchide Task
				
				// - Sprint get burndown		
			
			// - Release inchide Sprint
		
			// - Release get burnup
		
		// - Proiect inchide Release 
		
		
	}

}
