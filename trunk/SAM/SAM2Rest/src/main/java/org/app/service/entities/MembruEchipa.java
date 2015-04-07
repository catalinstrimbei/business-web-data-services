package org.app.service.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MembruEchipa {
	
	@Id
	private Integer idMembruEchipa;
	
	@Enumerated (EnumType.STRING)
	private RolMembru rolmembru;
	
	@ManyToOne
	private MembruOrganizatie membruorg;
	
	@ManyToOne
	private Echipa echipa;

	public Integer getIdMembruEchipa() {
		return idMembruEchipa;
	}

	public void setIdMembruEchipa(Integer idMembruEchipa) {
		this.idMembruEchipa = idMembruEchipa;
	}

	public RolMembru getRolmembru() {
		return rolmembru;
	}

	public void setRolmembru(RolMembru rolmembru) {
		this.rolmembru = rolmembru;
	}

	public MembruOrganizatie getMembruorg() {
		return membruorg;
	}

	public void setMembruorg(MembruOrganizatie membruorg) {
		this.membruorg = membruorg;
	}

	public Echipa getEchipa() {
		return echipa;
	}

	public void setEchipa(Echipa echipa) {
		this.echipa = echipa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idMembruEchipa == null) ? 0 : idMembruEchipa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MembruEchipa other = (MembruEchipa) obj;
		if (idMembruEchipa == null) {
			if (other.idMembruEchipa != null)
				return false;
		} else if (!idMembruEchipa.equals(other.idMembruEchipa))
			return false;
		return true;
	}

	public MembruEchipa(Integer idMembruEchipa, RolMembru rolmembru,
			MembruOrganizatie membruorg, Echipa echipa) {
		super();
		this.idMembruEchipa = idMembruEchipa;
		this.rolmembru = rolmembru;
		this.membruorg = membruorg;
		this.echipa = echipa;
	}

	public MembruEchipa() {
		super();
	}
	
	
	

}
