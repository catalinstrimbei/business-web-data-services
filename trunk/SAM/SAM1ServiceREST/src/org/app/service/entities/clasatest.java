package org.app.service.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: clasatest
 *
 */
@Entity

public class clasatest implements Serializable {

	@Id
	private int id;
	private static final long serialVersionUID = 1L;

	public clasatest() {
		super();
	}
   
}
