package com.pkm.hero.service.data.service;

import java.util.List;

import com.pkm.hero.service.data.entity.HeroEntity;

public interface HeroDataService {

	public HeroEntity addHero(HeroEntity hero);
	public HeroEntity updateHero(HeroEntity hero);
	public List<HeroEntity> getHeroes();
	public List<String> getHeroNames();
	public HeroEntity deleteHero(Integer heroId);
}
