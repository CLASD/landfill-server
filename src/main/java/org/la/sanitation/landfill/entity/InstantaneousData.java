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
    @Column(name="InstantaneousDataId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer instantaneousDataId;
	private Integer landfillId;
	private Integer inspectorId;
	private Date startTime;
	private Date finishTime;
	
	private Integer InstrumentSerial;
	private String maxCH;
	private String IMEId;
	private Integer gridNo;
	
	public Integer getInstantaneousDataId() {
		return instantaneousDataId;
	}
	public void setInstantaneousDataId(Integer instantaneousDataId) {
		this.instantaneousDataId = instantaneousDataId;
	}
	public Integer getLandfillId() {
		return landfillId;
	}
	public void setLandfillId(Integer landfillId) {
		this.landfillId = landfillId;
	}
	public Integer getInspectorId() {
		return inspectorId;
	}
	public void setInspectorId(Integer inspectorId) {
		this.inspectorId = inspectorId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return finishTime;
	}
	public void setEndTime(Date endTime) {
		this.finishTime = endTime;
	}
	public Integer getInstrumentSerial() {
		return InstrumentSerial;
	}
	public void setInstrumentSerial(Integer instrumentSerial) {
		InstrumentSerial = instrumentSerial;
	}
	public String getMaxCH() {
		return maxCH;
	}
	public void setMaxCH(String maxCH) {
		this.maxCH = maxCH;
	}
	public String getIMEId() {
		return IMEId;
	}
	public void setIMEId(String iMEId) {
		IMEId = iMEId;
	}
	public Integer getGridNo() {
		return gridNo;
	}
	public void setGridNo(Integer gridNo) {
		this.gridNo = gridNo;
	}

}
