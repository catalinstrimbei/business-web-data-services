package org.erp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="PRODUCT_NOM")
public class ProductNom {
	@Id @Column(name="product_id") 
	//@GeneratedValue
	private Integer productId;
	
	private String name;
	
	private String description;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
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
		ProductNom other = (ProductNom) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	public ProductNom(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public ProductNom() {}

	public ProductNom(Integer productId, String name, String description) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ProductNom [productId=" + productId + ", name=" + name
				+ ", description=" + description + "]";
	}
	
	
}
