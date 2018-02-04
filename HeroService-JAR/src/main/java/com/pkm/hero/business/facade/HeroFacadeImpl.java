package com.pkm.hero.business.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkm.hero.business.service.DtoConverterService;
import com.pkm.hero.service.data.entity.HeroEntity;
import com.pkm.hero.service.data.service.HeroDataService;
import com.pkm.hero.dto.Hero;

@Service("heroServiceJar_heroFacade")
public class HeroFacadeImpl implements HeroFacade {

	@Autowired
	private HeroDataService heroDataService;

	@Autowired
	private DtoConverterService dtoConverterService;

	public HeroFacadeImpl() {
		
	}

	@Override
	public Hero addHero(Hero hero) {
		HeroEntity heroEntity = dtoConverterService.convertDTOToEntity(hero);
		HeroEntity savedHeroEntity = heroDataService.addHero(heroEntity);
		Hero savedHeroDTO = dtoConverterService.convertEntityToDTO(savedHeroEntity);		
		return (savedHeroDTO);
	}

	@Override
	public List<Hero> getHeroes() {
		List<Hero> heroes = dtoConverterService.convertEntitiesToDTOs(
				heroDataService.getHeroes());
		return (heroes);
	}

	@Override
	public List<String> getHeroNames() {
		return (heroDataService.getHeroNames());
	}

	@Override
	public List<Hero> getNamedAlikeHeroes(String heroName) {
		List<Hero> heroes = this.getHeroes();
		List<Hero> namedHeroes = new ArrayList<Hero>();
		for (Hero hero : heroes) {
			if (hero.getHeroName().equals(heroName)) {
				namedHeroes.add(hero);
			}
		}
		return namedHeroes;
	}

	@Override
	public Hero updateHero(Hero hero) {
		HeroEntity heroEntity = dtoConverterService.convertDTOToEntity(hero);
		HeroEntity updatedHeroEntity = heroDataService.updateHero(heroEntity);
		Hero updatedHeroDTO = dtoConverterService.convertEntityToDTO(updatedHeroEntity);		
		return (updatedHeroDTO);
	}

	@Override
	public Hero deleteHero(Integer heroId) {
		HeroEntity deletedHeroEntity = heroDataService.deleteHero(heroId);
		Hero deletedHeroDTO = dtoConverterService.convertEntityToDTO(deletedHeroEntity);
		return (deletedHeroDTO);
	}
}
