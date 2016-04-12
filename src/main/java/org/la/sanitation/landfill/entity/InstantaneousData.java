package org.la.sanitation.landfill.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="InstantaneousData")
@DynamicUpdate
@SelectBeforeUpdate
public class InstantaneousData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8398045443579334587L;
	@Id
    @Column(name="InstantaneousDataPK")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer instantaneousDataPK;
	private Integer sitePK;
	private Integer employeePK;
	private Date startTime;
	private Date finishTime;
	private Integer InstrumentPK;
	private String maxCH4;
	private Integer samplingPointPK;

	public Integer getInstantaneousDataPK() {
		return instantaneousDataPK;
	}

	public void setInstantaneousDataPK(Integer instantaneousDataId) {
		this.instantaneousDataPK = instantaneousDataId;
	}

	public Integer getSitePK() {
		return sitePK;
	}

	public void setSitePK(Integer sitePK) {
		this.sitePK = sitePK;
	}

	public Integer getEmployeePK() {
		return employeePK;
	}

	public void setEmployeePK(Integer employeePK) {
		this.employeePK = employeePK;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getInstrumentPK() {
		return InstrumentPK;
	}

	public void setInstrumentPK(Integer instrumentPK) {
		InstrumentPK = instrumentPK;
	}

	public String getMaxCH4() {
		return maxCH4;
	}

	public void setMaxCH4(String maxCH4) {
		this.maxCH4 = maxCH4;
	}

	public Integer getSamplingPointPK() {
		return samplingPointPK;
	}

	public void setSamplingPointPK(Integer samplingPointPK) {
		this.samplingPointPK = samplingPointPK;
	}


}
