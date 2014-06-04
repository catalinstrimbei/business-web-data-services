package org.office.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="PRODUCTS_IN_CATEGORIES")
public class ProductInCategories {
	@Id 
	private Integer pId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="product_categ")
	private String productCateg;
	
	@Column(name="product_subcateg")
	private String productSubCateg;

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCateg() {
		return productCateg;
	}

	public void setProductCateg(String productCateg) {
		this.productCateg = productCateg;
	}

	public String getProductSubCateg() {
		return productSubCateg;
	}

	public void setProductSubCateg(String productSubCateg) {
		this.productSubCateg = productSubCateg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pId == null) ? 0 : pId.hashCode());
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
		ProductInCategories other = (ProductInCategories) obj;
		if (pId == null) {
			if (other.pId != null)
				return false;
		} else if (!pId.equals(other.pId))
			return false;
		return true;
	}
	
	
}
