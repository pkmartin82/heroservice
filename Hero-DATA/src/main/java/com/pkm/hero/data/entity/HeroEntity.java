package com.pkm.hero.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.time.DateUtils;

@Entity
@Table(name = "Hero")
public class HeroEntity implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "heroId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer heroId;

	@Column(name = "name", length = 30, nullable = false)
	private String name;

	@Column(name = "secretIdentity", length = 30, nullable = false)
	private String secretIdentity;

	@Column(name = "numOthersWhoKnowSecret")
	private Integer numOthersWhoKnowSecret;

	@Column(name = "catchphrase", length = 100)
	private String catchphrase;

	@ManyToOne
	@JoinColumn(name = "universeId")
	private UniverseEntity universe;

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
	public HeroEntity() {
	}

	/**
	 * Returns a String representation of this class
	 */
	@Override
	public String toString() {
		String out = ToStringBuilder.reflectionToString(this);
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((catchphrase == null) ? 0 : catchphrase.hashCode());
		result = prime * result + ((heroId == null) ? 0 : heroId.hashCode());
		result = prime * result + ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((insertUser == null) ? 0 : insertUser.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numOthersWhoKnowSecret == null) ? 0 : numOthersWhoKnowSecret.hashCode());
		result = prime * result + ((secretIdentity == null) ? 0 : secretIdentity.hashCode());
		result = prime * result + ((universe == null) ? 0 : universe.hashCode());
		result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
		result = prime * result + ((updateUser == null) ? 0 : updateUser.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeroEntity other = (HeroEntity) obj;
		if (catchphrase == null) {
			if (other.catchphrase != null)
				return false;
		} else if (!catchphrase.equals(other.catchphrase))
			return false;
		if (heroId == null) {
			if (other.heroId != null)
				return false;
		} else if (!heroId.equals(other.heroId))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else {
			if (DateUtils.truncatedCompareTo(
					insertTime, other.insertTime, Calendar.SECOND) != 0) {
				return false;
			}
		}
		if (insertUser == null) {
			if (other.insertUser != null)
				return false;
		} else if (!insertUser.equals(other.insertUser))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numOthersWhoKnowSecret == null) {
			if (other.numOthersWhoKnowSecret != null)
				return false;
		} else if (!numOthersWhoKnowSecret.equals(other.numOthersWhoKnowSecret))
			return false;
		if (secretIdentity == null) {
			if (other.secretIdentity != null)
				return false;
		} else if (!secretIdentity.equals(other.secretIdentity))
			return false;
		if (universe == null) {

			// Should not get here!
			if (other.universe != null)
				return false;
		} else {
			Integer thisUniverseId = universe.getUniverseId();
			Integer otherUniverseId = other.universe.getUniverseId();

			if (thisUniverseId == null) {

				//  Should not get here!
				if (otherUniverseId != null) {
					return false;
				}
			} else {
				if (! thisUniverseId.equals(otherUniverseId)) {
					return false;
				}
			}
		}
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else {
			if (DateUtils.truncatedCompareTo(
					updateTime, other.updateTime, Calendar.SECOND) != 0) {
				return false;
			}
		}
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		return true;
	}

	/**
	 * @return the heroId
	 */
	public Integer getHeroId() {
		return heroId;
	}

	/**
	 * @param heroId
	 *            the heroId to set
	 */
	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the secretIdentity
	 */
	public String getSecretIdentity() {
		return secretIdentity;
	}

	/**
	 * @param secretIdentity
	 *            the secretIdentity to set
	 */
	public void setSecretIdentity(String secretIdentity) {
		this.secretIdentity = secretIdentity;
	}

	/**
	 * @return the numOthersWhoKnowSecret
	 */
	public Integer getNumOthersWhoKnowSecret() {
		return numOthersWhoKnowSecret;
	}

	/**
	 * @param numOthersWhoKnowSecret
	 *            the numOthersWhoKnowSecret to set
	 */
	public void setNumOthersWhoKnowSecret(Integer numOthersWhoKnowSecret) {
		this.numOthersWhoKnowSecret = numOthersWhoKnowSecret;
	}

	/**
	 * @return the catchphrase
	 */
	public String getCatchphrase() {
		return catchphrase;
	}

	/**
	 * @param catchphrase
	 *            the catchphrase to set
	 */
	public void setCatchphrase(String catchphrase) {
		this.catchphrase = catchphrase;
	}

	/**
	 * @return the universe
	 */
	public UniverseEntity getUniverse() {
		return universe;
	}

	/**
	 * @param universe
	 *            the universe to set
	 */
	public void setUniverse(UniverseEntity universe) {
		this.universe = universe;
	}

	/**
	 * @return the insertUser
	 */
	public String getInsertUser() {
		return insertUser;
	}

	/**
	 * @param insertUser
	 *            the insertUser to set
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
	 * @param insertTime
	 *            the insertTime to set
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
	 * @param updateUser
	 *            the updateUser to set
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
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}