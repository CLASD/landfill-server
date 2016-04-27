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
public class ImeInspection {
	
	@Id
    @Column(name="ImeInspectionPK")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer imeInspectionPK;
	private Date inspectionDate;
	private String description;
	private String imeShape;
	@OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private Employee employee;
	private String value;
	private Ime ime;
	@OneToMany( mappedBy="imeInspection" , fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private List<ImeRepair> imeRepairs;
	public Integer getImeInspectionPK() {
		return imeInspectionPK;
	}
	public void setImeInspectionPK(Integer imeInspectionPK) {
		this.imeInspectionPK = imeInspectionPK;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImeShape() {
		return imeShape;
	}
	public void setImeShape(String imeShape) {
		this.imeShape = imeShape;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Ime getIme() {
		return ime;
	}
	public void setIme(Ime ime) {
		this.ime = ime;
	}
	public List<ImeRepair> getImeRepairs() {
		return imeRepairs;
	}
	public void setImeRepairs(List<ImeRepair> imeRepairs) {
		this.imeRepairs = imeRepairs;
	}

}
