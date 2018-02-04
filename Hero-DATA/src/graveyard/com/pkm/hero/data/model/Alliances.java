package com.pkm.hero.data.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Alliances")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"teams", "sidekickName" , "partnerName", "mentorName"})
public class Alliances {

//    @XmlElementWrapper(name = "teams", required=true)
    @XmlElement(name = "teams", required=false)
    private List<String> teams;
    
    @XmlElement(name = "sidekick", required=false)
	private String sidekickName;
    
    @XmlElement(name = "parter", required=false)
	private String partnerName;

    @XmlElement(name = "mentor", required=false)
    private String mentorName;

	public List<String> getTeams() {
		return teams;
	}

	public void setTeams(List<String> teams) {
		this.teams = teams;
	}

	public String getSidekickName() {
		return sidekickName;
	}

	public void setSidekick(String sidekickName) {
		this.sidekickName = sidekickName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartner(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}
}
