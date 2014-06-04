package org.erp;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="SALES_INVOICES")
public class SalesInvoices {
	@Id @Column(name="tranz_id") 
	private Integer tranzId;
	
	@Temporal(TemporalType.DATE) @Column(name="tranz_date") 
	private Date tranzDate;
	
	@Column(name="invoice_no")
	private Long invoiceNo;
	
	@Column(name="product_id") 
	private Integer productId;
	
	@Column(name="customer_id") 
	private Integer custId;
	
	private Double quantity;

	private Double unitPrice;
	
	@Column(name="vat_taxes")
	private Double vatTaxes;
	
	private Double amount;

	public Integer getTranzId() {
		return tranzId;
	}

	public void setTranzId(Integer tranzId) {
		this.tranzId = tranzId;
	}

	public Date getTranzDate() {
		return tranzDate;
	}

	public void setTranzDate(Date tranzDate) {
		this.tranzDate = tranzDate;
	}

	public Long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getVatTaxes() {
		return vatTaxes;
	}

	public void setVatTaxes(Double vatTaxes) {
		this.vatTaxes = vatTaxes;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tranzId == null) ? 0 : tranzId.hashCode());
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
		SalesInvoices other = (SalesInvoices) obj;
		if (tranzId == null) {
			if (other.tranzId != null)
				return false;
		} else if (!tranzId.equals(other.tranzId))
			return false;
		return true;
	}
	
	
	
}
