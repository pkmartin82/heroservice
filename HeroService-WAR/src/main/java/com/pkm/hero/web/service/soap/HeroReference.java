/**
 * 
 */
package com.pkm.hero.web.service.soap;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

//import com.pkm.hero.data.model.Alliances;
//import com.pkm.hero.data.model.LocationsFrequented;
//import com.pkm.hero.data.model.Powers;
import com.pkm.hero.dto.Hero;


/**
 * @author pmartin
 *
 */
@WebService
public interface HeroReference {

//  Do NOT use XmlElementWrapper to indicate plurality.  It makes the client code dorky.
//  Client would have to call:
//
//	List<String> names = service.getHeroNames().getHeroName();
//
//  SoapUI looks good with XmlElementWrapper, but code looks like above.  Go
//  for cleaner client code than good SoapUI return XML...
	
	
	@WebMethod
	//    @XmlElementWrapper(name = "heroes")
    @WebResult(name = "heroes")
	public List<Hero> getHeroes();
	
	@WebMethod
	@WebResult(name = "heroNames")
	public List<String> getHeroNames();

	@WebMethod
	@WebResult(name = "secretIdentity")
	public String getSecretIdentity(
			@WebParam(name="heroName") 
			@XmlElement(name="heroName", required=true) String heroName
	);
	
//	@WebMethod
//	public void addOrUpdateHero(
//			@WebParam(name = "heroName") 
//			@XmlElement(name="heroName", required=true) String heroName,
//			@WebParam(name = "secretIdentity") 
//			@XmlElement(name="secretIdentity", required=true) String secretIdentity,
//			@WebParam(name = "powers") Powers powers,
//			@WebParam(name = "alliances") Alliances alliances,
//			@WebParam(name = "locationsFrequented") LocationsFrequented locations
//	);

	@WebMethod
	@WebResult(name = "hero")
	public Hero getHero(
		@WebParam(name = "heroName") 
		@XmlElement(name="heroName", required=true) String heroName
	);	
}


/**
 * Note that WebResult applies to each item returned in the Set, so "hero" is appropriate here
 * @return
 */
