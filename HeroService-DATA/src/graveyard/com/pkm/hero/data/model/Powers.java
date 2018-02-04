package com.pkm.hero.data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Powers")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"canFly", "strength" , "fightingAbility", 
			"durability", "agility", "needsToBreatheAir","stamina", "speed", 
			"energyProjection", "primaryPower" })
public class Powers {

	@XmlElement(name="canFly", required=true)
	private boolean canFly;
	@XmlElement(name="strength", required=true)
	private int strength;
	@XmlElement(name="fightingAbility", required=true)
	private int fightingAbility;
	@XmlElement(name="durability", required=false)
	private int durability;
	@XmlElement(name="agility", required=false)
	private int agility;
	@XmlElement(name="needsToBreatheAir", required=true)
	private boolean needsToBreatheAir;
	@XmlElement(name="stamina", required=false)
	private int stamina;
	@XmlElement(name="speed", required=false)
	private int speed;
	@XmlElement(name="energyProjection", required=false)
	private boolean energyProjection;
	@XmlElement(name="primaryPower", required=true)
	private String primaryPower;

	public boolean canFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getFightingAbility() {
		return fightingAbility;
	}

	public void setFightingAbility(int fightingAbility) {
		this.fightingAbility = fightingAbility;
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public boolean needsToBreatheAir() {
		return needsToBreatheAir;
	}

	public void setNeedsToBreatheAir(boolean needsToBreatheAir) {
		this.needsToBreatheAir = needsToBreatheAir;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isEnergyProjection() {
		return energyProjection;
	}

	public void setEnergyProjection(boolean energyProjection) {
		this.energyProjection = energyProjection;
	}

	public String getPrimaryPower() {
		return primaryPower;
	}

	public void setPrimaryPower(String primaryPower) {
		this.primaryPower = primaryPower;
	}
}
