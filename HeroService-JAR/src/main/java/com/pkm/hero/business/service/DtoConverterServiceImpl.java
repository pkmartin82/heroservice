/**
 * 
 */
package com.pkm.hero.business.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pkm.hero.service.data.entity.HeroEntity;
import com.pkm.hero.service.data.entity.UniverseEntity;
import com.pkm.hero.dto.Hero;

@Service("heroServiceJar_dtoConverterService")
public class DtoConverterServiceImpl implements DtoConverterService {

	private Logger logger;

	/**
	 * Default Constructor
	 */
	public DtoConverterServiceImpl() {
		logger = Logger.getLogger(this.getClass());
	}

	/* (non-Javadoc)
	 * @see com.pkm.hero.data.services.DtoConverterService#convertEntityToDTO(com.pkm.hero.data.entity.HeroEntity)
	 */
	@Override
	public Hero convertEntityToDTO(HeroEntity heroEntity) {
		logger.trace("Converting " + heroEntity + " to " + Hero.class.getSimpleName());
		Hero hero = new Hero(heroEntity.getHeroId(), heroEntity.getName(), heroEntity.getSecretIdentity());
		hero.setNumOthersWhoKnowSecret(heroEntity.getNumOthersWhoKnowSecret());
		hero.setCatchphrase(heroEntity.getCatchphrase());
		hero.setUniverseId(heroEntity.getUniverse().getUniverseId());
		hero.setInsertTime(
				(heroEntity.getInsertTime() != null) ? new Date(heroEntity.getInsertTime().getTime()) : null);
		hero.setInsertUser(heroEntity.getInsertUser());
		hero.setUpdateTime(
				(heroEntity.getUpdateTime() != null) ? new Date(heroEntity.getUpdateTime().getTime()) : null);
		hero.setUpdateUser(heroEntity.getUpdateUser());
		return hero;
	}

	/* (non-Javadoc)
	 * @see com.pkm.hero.data.services.DtoConverterService#convertEntitiesToDTOs(java.util.List)
	 */
	@Override
	public List<Hero> convertEntitiesToDTOs(List<HeroEntity> heroEntities) {
		List<Hero> heroes = new ArrayList<Hero>();

		for (HeroEntity heroEntity : heroEntities) {
			heroes.add(this.convertEntityToDTO(heroEntity));
		}

		return (heroes);
	}

	/* (non-Javadoc)
	 * @see com.pkm.hero.data.services.DtoConverterService#convertDTOToEntity(com.pkm.hero.dto.Hero)
	 */
	@Override
	public HeroEntity convertDTOToEntity(Hero hero) {
		logger.trace("Converting " + hero + " to " + HeroEntity.class.getSimpleName());
		UniverseEntity universe = new UniverseEntity();
		universe.setUniverseId(hero.getUniverseId());

		HeroEntity heroEntity = new HeroEntity();
		heroEntity.setHeroId(hero.getHeroId());
		heroEntity.setName(hero.getHeroName());
		heroEntity.setSecretIdentity(hero.getSecretIdentity());
		heroEntity.setNumOthersWhoKnowSecret(hero.getNumOthersWhoKnowSecret());
		heroEntity.setCatchphrase(hero.getCatchphrase());
		heroEntity.setUniverse(universe);
		heroEntity.setInsertTime(
				(hero.getInsertTime() != null) ? new Timestamp(hero.getInsertTime().getTime()) : null);
		heroEntity.setInsertUser(hero.getInsertUser());
		heroEntity.setUpdateTime(
				(hero.getUpdateTime() != null) ? new Timestamp(heroEntity.getUpdateTime().getTime()) : null);
		heroEntity.setUpdateUser(heroEntity.getUpdateUser());
		return heroEntity;
	}

	/* (non-Javadoc)
	 * @see com.pkm.hero.data.services.DtoConverterService#convertDTOsToEntities(java.util.List)
	 */
	@Override
	public List<HeroEntity> convertDTOsToEntities(List<Hero> heroDTOs) {
		List<HeroEntity> heroEntities = new ArrayList<HeroEntity>();

		for (Hero hero : heroDTOs) {
			heroEntities.add(this.convertDTOToEntity(hero));
		}

		return (heroEntities);
	}

}
