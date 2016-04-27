package org.la.sanitation.landfill.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="ImeRepair")
@DynamicUpdate
@SelectBeforeUpdate
public class ImeRepair {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer imeRepairPK;
	private String type;
	private String description;
	private String crew;
	private Date dateTime;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="enteringEmployee", referencedColumnName="EmployeePK")
	private Employee enteringEmployee;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="IMEInspectionPK")
	private ImeInspection imeInspection;

	public Integer getImeRepairPK() {
		return imeRepairPK;
	}
	public void setImeRepairPK(Integer imeRepairPK) {
		this.imeRepairPK = imeRepairPK;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCrew() {
		return crew;
	}
	public void setCrew(String crew) {
		this.crew = crew;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Employee getEnteringEmployee() {
		return enteringEmployee;
	}
	public void setEnteringEmployee(Employee enteringEmployee) {
		this.enteringEmployee = enteringEmployee;
	}


}
