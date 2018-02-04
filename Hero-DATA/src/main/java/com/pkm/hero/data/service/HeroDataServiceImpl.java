/**
 * 
 */
package com.pkm.hero.data.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pkm.hero.data.dao.HeroDao;
import com.pkm.hero.data.entity.HeroEntity;

@Service("heroData_heroDataService")
@Transactional(value = "heroTransactionManager", propagation = Propagation.SUPPORTS)
public class HeroDataServiceImpl implements HeroDataService {

	@Autowired
	private HeroDao heroDao;

	private Logger logger;

	/**
	 * 
	 */
	public HeroDataServiceImpl() {
		logger = Logger.getLogger(this.getClass());
	}

	@Override
	public HeroEntity addHero(HeroEntity hero) {

		hero.setInsertTime(getNowTimestamp());
		hero.setInsertUser(getUserId());

		HeroEntity savedHeroEntity = heroDao.save(hero);

		return (savedHeroEntity);
	}

	@Override
	public HeroEntity updateHero(HeroEntity hero) {

		hero.setUpdateTime(getNowTimestamp());
		hero.setUpdateUser(getUserId());

		HeroEntity savedHeroEntity = heroDao.save(hero);

		return (savedHeroEntity);
	}

	@Override
	public List<HeroEntity> getHeroes() {

		logger.debug("Get List of Hero Entities");
		List<HeroEntity> heroes = heroDao.getAll();

		logger.debug("Return list of Hero DTOs");
		return (heroes);
	}

	public List<String> getHeroNames() {
		logger.debug("Get List of Hero Names");
		List<String> heroNames = heroDao.getHeroNames();

		logger.debug("Return list of Hero Names");
		return (heroNames);
	}

	public HeroEntity deleteHero(Integer heroId) {
		logger.debug("Removing Hero with ID " + heroId);

		HeroEntity hero = heroDao.delete(heroId);

		return (hero);
	}

	// ================================================================================================================
	// MISCELLANEOUS
	// ================================================================================================================

	/**
	 * Gets the Timestamp for right now
	 * 
	 * @return Timestamp representing right now
	 */
	private static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * Gets the userName of the who is saving this information
	 * 
	 * @return String for the name of who will be saving this information
	 */
	private static String getUserId() {
		return "pkmartin82";
	}
}
