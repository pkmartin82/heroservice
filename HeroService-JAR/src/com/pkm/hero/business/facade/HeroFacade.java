package com.pkm.hero.business.facade;

import java.util.List;

import com.pkm.hero.dto.Hero;

public interface HeroFacade {

	public Hero addHero(Hero hero);

	public List<Hero> getHeroes();

	public List<String> getHeroNames();

	public List<Hero> getNamedAlikeHeroes(String heroName);

	public Hero updateHero(Hero hero);

	public Hero deleteHero(Integer heroId);
}
