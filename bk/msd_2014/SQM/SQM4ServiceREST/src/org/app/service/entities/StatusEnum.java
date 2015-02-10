package org.app.service.entities;

import javax.persistence.*;

/**
 * @author oradbtc
 * @version 1.0
 * @created 20-mag-2014 11.56.03
 */

public enum StatusEnum {
	CREATED, OPENED, CLOSED, FIXED, REOPENED, RESOLVED;

	private StatusEnum statusEnum;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

}