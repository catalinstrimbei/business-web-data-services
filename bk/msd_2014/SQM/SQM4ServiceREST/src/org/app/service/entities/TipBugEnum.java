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

public enum TipBugEnum {
	IMPLEMENTARE, TESTARE;

	private TipBugEnum tipBugEnum;

	@Column(name = "tipBug")
	@Enumerated(EnumType.STRING)
	public TipBugEnum getTipBugEnum() {
		return tipBugEnum;
	}
}