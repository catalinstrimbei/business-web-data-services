package org.office.xls;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.datanucleus.api.jpa.annotations.ColumnPosition;
import org.datanucleus.api.jpa.annotations.Extension;


@Entity @Table(name="ADVERTISING_EXPENSES")
@Extension(vendorName="datanucleus", key="include-column-headers", value="true")
public class AdvertisingExpense {
	@Id @Column(name="product_code") @ColumnPosition(0)
	private Integer productCode;
	
	@Temporal(TemporalType.DATE) @Column(name="per_start_date") @ColumnPosition(2) 
	private Date periodStartDate;
	
	@Temporal(TemporalType.DATE) @Column(name="per_end_date") @ColumnPosition(3)
	private Date periodEndDate;
	
	@Column(name="adv_exp_categ") @ColumnPosition(1)
	private String advExpCateg;
	
	@Column(name="adv_exp_amount") @ColumnPosition(4)
	private Double advExpAmount;

	@Column(name="product_sales") @ColumnPosition(5)
	private Double productSales;
	
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

	
	
	public Double getProductSales() {
		return productSales;
	}

	public void setProductSales(Double productSales) {
		this.productSales = productSales;
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
				+ ", periodStartDate=" + periodStartDate + ", periodEndDate="
				+ periodEndDate + ", advExpCateg=" + advExpCateg
				+ ", advExpAmount=" + advExpAmount + ", productSales="
				+ productSales + "]";
	}

	public AdvertisingExpense(Integer productCode, Date periodStartDate,
			Date periodEndDate, String advExpCateg, Double advExpAmount,
			Double productSales) {
		super();
		this.productCode = productCode;
		this.periodStartDate = periodStartDate;
		this.periodEndDate = periodEndDate;
		this.advExpCateg = advExpCateg;
		this.advExpAmount = advExpAmount;
		this.productSales = productSales;
	}

	public AdvertisingExpense() {
		super();
	}
	
	
}
