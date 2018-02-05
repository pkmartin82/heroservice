/**
 * 
 */
package com.pkm.hero.web.service.soap;


import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;

//import com.pkm.hero.data.model.Alliances;
//import com.pkm.hero.data.model.LocationsFrequented;
//import com.pkm.hero.data.model.Powers;
import com.pkm.hero.dto.Hero;
import com.pkm.hero.business.facade.HeroFacade;

/**
 * @author pmartin
 *
 */
@WebService(
		endpointInterface = "com.pkm.hero.web.service.soap.HeroReference", 
		serviceName = "HeroWebService"
)
public class HeroReferenceImpl implements HeroReference {

	@Autowired
	private HeroFacade heroFacade;

	/* (non-Javadoc)
	 * @see com.pkm.heroes.HeroService#getHeroNames()
	 */
	@Override
	public List<String> getHeroNames() {
		return (heroFacade.getHeroNames());
	}

	/* (non-Javadoc)
	 * @see com.pkm.heroes.HeroService#getSecretIdentity(java.lang.String)
	 */
	@Override
	public String getSecretIdentity(String heroName) {
		throw new UnsupportedOperationException("Method not supported!");
	}

//	/*
//	 * (non-Javadoc)
//	 * @see com.pkm.heroes.service.HeroService#addHero(java.lang.String, java.lang.String, com.pkm.heroes.hero.Powers, com.pkm.heroes.hero.Alliances, com.pkm.heroes.hero.LocationsFrequented)
//	 */
//	@Override
//	@WebMethod
//	public void addOrUpdateHero(
//			String heroName,
//			String secretIdentity,
//			Powers powers,
//			Alliances alliances,
//			LocationsFrequented locations) {
//		Hero hero = new Hero(null, heroName, secretIdentity);
//		heroFacade.addHero(hero);
//	}

	@Override
	@WebMethod
	public Hero getHero(String heroName) {
		throw new UnsupportedOperationException("Method not supported!");
	}

	@Override
	@WebMethod
//  XmlElementWrapper has no effect here
//	@XmlElementWrapper(name = "heroes")
	@XmlElement(name = "hero")
	public List<Hero> getHeroes() {
		return (heroFacade.getHeroes());
	}
}
