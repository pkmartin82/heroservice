package com.pkm.hero.web.service.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;

import com.pkm.hero.dto.Hero;
import com.pkm.hero.business.facade.HeroFacade;
import com.pkm.hero.web.startup.ApplicationContextProvider;

@Path("/HeroReference")
@RequestScoped
public class HeroReference {

	/*
	 * Prefer JSON over XML, but put list XML for descriptive purposes
	 */

	//  ================================================================================================================
	//  CREATE
	//  ================================================================================================================

	/**
	 * Add a Hero
	 * 
	 * Note:  This @Path must necessarily be different from the @Path's of other methods
	 * @param hero
	 * @return
	 */
	@POST
	@Path("/AddHero")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Hero addHero(Hero hero) {

		//  Add the Hero
		HeroFacade heroFacade = this.getHeroFacade();
		Hero addedHero = heroFacade.addHero(hero);

		return (addedHero);
	}

	@POST
	@Path("/AddHeroSkeleton")
	public void addHero(
			@FormParam("heroName") String heroName,
			@FormParam("secretIdentity") String secretIdentity,
			@FormParam("universeid") Integer universeId) {

		//  Create a Hero DTO from the input params
		Hero hero = new Hero(null, heroName, secretIdentity);

		if (universeId != null) {
			hero.setUniverseId(universeId);
		}

		//  Add the Hero
		HeroFacade heroFacade = this.getHeroFacade();

		@SuppressWarnings("unused")
		Hero addedHero = heroFacade.addHero(hero);
	}

	//  ================================================================================================================
	//  READ
	//  ================================================================================================================

	@GET
	@Path("/Heroes")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Hero> getHeroes() {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getHeroes());
	}

	@GET
	@Path("/HeroesXML")
	@Produces({MediaType.APPLICATION_XML})
	public List<Hero> getHeroesXml() {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getHeroes());
	}

	@GET
	@Path("/Hero/{heroName}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Hero> getHero(@PathParam("heroName") String heroName) {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getNamedAlikeHeroes(heroName));
	}

	@GET
	@Path("/HeroXML/{heroName}")
	@Produces({MediaType.APPLICATION_XML})
	public List<Hero> getHeroXml(@PathParam("heroName") String heroName) {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getNamedAlikeHeroes(heroName));
	}

	@GET
	@Path("/HeroNames")
	@Produces({MediaType.APPLICATION_JSON})
	//  This method MUST produce JSON as it cannot render a 
	//  List of Strings (which have no XML definition)
	public List<String> getHeroNames() {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getHeroNames());
	}

	@GET
	@Path("/HeroNamesXML")
	@Produces({MediaType.APPLICATION_XML})
	//  This method MUST produce JSON as it cannot render a 
	//  List of Strings (which have no XML definition)
	//  This will throw an HTTP 500 error message
	public List<String> getHeroNamesXml() {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.getHeroNames());
	}

	//  ================================================================================================================
	//  UPDATE
	//  ================================================================================================================

	@PUT
	@Path("/UpdateHero")
    @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Hero updateHero(Hero hero) {

		//  Add the Hero
		HeroFacade heroFacade = this.getHeroFacade();
		Hero addedHero = heroFacade.updateHero(hero);

		return (addedHero);
	}

	//  ================================================================================================================
	//  DELETE
	//  ================================================================================================================

	@DELETE
	@Path("/RemoveHeroById/{heroId}")
	@Produces({MediaType.APPLICATION_JSON})
	public Hero removeHeroById(@PathParam("heroId") Integer heroId) {
		HeroFacade heroFacade = this.getHeroFacade();
		return (heroFacade.deleteHero(heroId));
	}

	//  ================================================================================================================
	//  MISCELLANEOUS
	//  ================================================================================================================

	/**
	 * Necessary as REST services are by default RequestScoped, meaning cannot Autowire a HeroService class attribute
	 * @return
	 */
	private HeroFacade getHeroFacade() {
		ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();
		HeroFacade heroFacade = appContext.getBean(HeroFacade.class);
		return (heroFacade);
	}
}
