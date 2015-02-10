package org.app.service.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

public enum PrioritateEnum {
	P1, P2, P3, P4, P5;
	private PrioritateEnum prioritateEnum;

	@Column(name = "prioritate")
	@Enumerated(EnumType.STRING)
	public PrioritateEnum getPrioritateEnum() {
		return prioritateEnum;
	}
}
