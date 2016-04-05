package org.la.sanitation.landfill.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="Site")
@DynamicUpdate
@SelectBeforeUpdate
public class Site {
	
	@Id
    @Column(name="SiteId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String name;
	private Integer gridCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGridCount() {
		return gridCount;
	}
	public void setGridCount(Integer gridCount) {
		this.gridCount = gridCount;
	}

}
