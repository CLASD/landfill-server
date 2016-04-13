package org.la.sanitation.landfill.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="Ime")
@DynamicUpdate
@SelectBeforeUpdate
public class Ime {
	
	@Id
    @Column(name="IMEPK")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	private Date readingDate;
	private String description;
	private Integer employeePK;
	private String value;
	private String imeNumber;
	@Transient
	private String pointType;
	@Transient 
	private String siteName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getReadingDate() {
		return readingDate;
	}
	public void setReadingDate(Date readingDate) {
		this.readingDate = readingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEmployeePK() {
		return employeePK;
	}
	public void setEmployeePK(Integer employeePK) {
		this.employeePK = employeePK;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getImeNumber() {
		return imeNumber;
	}
	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
