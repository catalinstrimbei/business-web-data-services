package org.app.service.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Modul {
@Id
	int idmodul;
	String denumiremodul;
	Date datapublicare=new Date();
}
