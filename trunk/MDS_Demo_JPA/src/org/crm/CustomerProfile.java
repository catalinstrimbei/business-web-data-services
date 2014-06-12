package org.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="CUSTOMERS_PROFILES")
public class CustomerProfile {
	@Id @Column(name="customer_id") 
	private Integer custId;
	
	private String name;
	
	private String region;
	
	private String address;
	
	private String country;
	
	@Column(name="custr_category")
	private String customerCategory;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custId == null) ? 0 : custId.hashCode());
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
		CustomerProfile other = (CustomerProfile) obj;
		if (custId == null) {
			if (other.custId != null)
				return false;
		} else if (!custId.equals(other.custId))
			return false;
		return true;
	}

	public CustomerProfile() {
		super();
	}

	public CustomerProfile(Integer custId, String name, String region,
			String address, String country, String customerCategory) {
		super();
		this.custId = custId;
		this.name = name;
		this.region = region;
		this.address = address;
		this.country = country;
		this.customerCategory = customerCategory;
	}

	@Override
	public String toString() {
		return "CustomerProfile [custId=" + custId + ", name=" + name
				+ ", region=" + region + ", address=" + address + ", country="
				+ country + ", customerCategory=" + customerCategory + "]";
	}
	
	
}
