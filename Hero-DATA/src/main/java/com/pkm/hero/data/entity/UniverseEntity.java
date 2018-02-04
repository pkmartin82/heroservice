/**
 * 
 */
package com.pkm.hero.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "Universe")
public class UniverseEntity implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "universeId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer universeId;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "insertUser", length = 25, nullable = false)
	private String insertUser;

	@Column(name = "insertTime", nullable = false)
	private Timestamp insertTime;

	@Column(name = "updateUser", length = 25, nullable = true)
	private String updateUser;

	@Column(name = "updateTime", nullable = true)
	private Timestamp updateTime;

	/**
	 * Default Constructor
	 */
	public UniverseEntity() {
	}

	/**
	 * Returns a String representation of this class
	 */
	@Override
	public String toString() {
		String out = ToStringBuilder.reflectionToString(this);
		return out;
	}

	/**
	 * @return the universeId
	 */
	public Integer getUniverseId() {
		return universeId;
	}

	/**
	 * @param universeId the universeId to set
	 */
	public void setUniverseId(Integer universeId) {
		this.universeId = universeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the insertUser
	 */
	public String getInsertUser() {
		return insertUser;
	}

	/**
	 * @param insertUser the insertUser to set
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	/**
	 * @return the insertTime
	 */
	public Timestamp getInsertTime() {
		return insertTime;
	}

	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateTime
	 */
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
