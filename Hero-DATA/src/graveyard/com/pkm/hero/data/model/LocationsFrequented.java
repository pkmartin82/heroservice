package com.pkm.hero.data.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="LocationsFrequented")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"venue", "city" , "state", "country", "planet", "travelsFrequently", "travelsInterplanetarily" })
public class LocationsFrequented {

	@XmlElement(name="venue", required=true)
	private String venue;	

	@XmlElement(name="city", required=false)
	private String city;
	
	@XmlElement(name="state", required=false)
	private String state;

	@XmlElement(name="country", required=false)
	private String country;

	@XmlElement(name="planet", required=true)
	private String planet;

	@XmlElement(name="travelsFrequently", required=true)
	private boolean travelsFrequently;

	@XmlElement(name="travelsInterplanetarily", required=false)
	private boolean travelsInterplanetarily;

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPlanet() {
		return planet;
	}

	public void setPlanet(String planet) {
		this.planet = planet;
	}

	public boolean travelsFrequently() {
		return travelsFrequently;
	}

	public void setTravelsFrequently(boolean travelsFrequently) {
		this.travelsFrequently = travelsFrequently;
	}

	public boolean travelsInterplanetarily() {
		return travelsInterplanetarily;
	}

	public void setTravelsInterplanetarily(boolean travelsInterplanetarily) {
		this.travelsInterplanetarily = travelsInterplanetarily;
	}
}
