package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Proiect {
@Id
	int idproiect;
	String numeproiect;
	int numarmodule;
}
