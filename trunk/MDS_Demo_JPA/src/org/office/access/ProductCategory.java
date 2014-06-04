package org.office.access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="CATEGORIES")
public class ProductCategory {
	@Id @Column(name="category_id") 
	private Integer categoryId;
	
	@Column(name="categ_name")
	private String categName;
	
	@Column(name="categ_description")
	private String categDescription;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategName() {
		return categName;
	}

	public void setCategName(String categName) {
		this.categName = categName;
	}

	public String getCategDescription() {
		return categDescription;
	}

	public void setCategDescription(String categDescription) {
		this.categDescription = categDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryId=" + categoryId + ", categName="
				+ categName + ", categDescription=" + categDescription + "]";
	}

	
	
}

