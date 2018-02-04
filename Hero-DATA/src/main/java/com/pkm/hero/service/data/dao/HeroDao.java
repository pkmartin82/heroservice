package com.pkm.hero.service.data.dao;

import java.util.List;

import com.pkm.genericdao.dao.GenericDao;
import com.pkm.hero.service.data.entity.HeroEntity;

public interface HeroDao extends GenericDao<HeroEntity, Integer> {

	//  ================================================================================================================
	//  READ
	//  ================================================================================================================

	public List<String> getHeroNames();

	/**
	 * Gets list of heroes with the heroName and secretIdentity specified
	 * @param heroName
	 * @param secretIdentity
	 * @return 
	 */
	public List<HeroEntity> getHeroes(String heroName, String secretIdentity);	
}
