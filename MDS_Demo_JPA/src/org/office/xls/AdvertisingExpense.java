package org.office.xls;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="ADVERTISING_EXPENSES")
public class AdvertisingExpense {
	@Id @Column(name="product_code") 
	private Integer productCode;
	
	@Temporal(TemporalType.DATE) @Column(name="period_start_date") 
	private Date periodStartDate;
	
	@Temporal(TemporalType.DATE) @Column(name="period_end_date") 
	private Date periodEndDate;
	
	@Column(name="adv_exp_categ")
	private String advExpCateg;
	
	@Column(name="adv_exp_amount")
	private Double advExpAmount;

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	public Date getPeriodStartDate() {
		return periodStartDate;
	}

	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}

	public Date getPeriodEndDate() {
		return periodEndDate;
	}

	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}

	public String getAdvExpCateg() {
		return advExpCateg;
	}

	public void setAdvExpCateg(String advExpCateg) {
		this.advExpCateg = advExpCateg;
	}

	public Double getAdvExpAmount() {
		return advExpAmount;
	}

	public void setAdvExpAmount(Double advExpAmount) {
		this.advExpAmount = advExpAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
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
		AdvertisingExpense other = (AdvertisingExpense) obj;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdvertisingExpense [productCode=" + productCode
				+ ", advExpCateg=" + advExpCateg + ", advExpAmount="
				+ advExpAmount + "]";
	}
	
	
}
