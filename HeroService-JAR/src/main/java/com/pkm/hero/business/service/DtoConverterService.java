package com.pkm.hero.business.service;


import java.util.List;
import org.springframework.stereotype.Service;

import com.pkm.hero.data.entity.HeroEntity;
import com.pkm.hero.dto.Hero;

@Service("dtoConverterService")
public interface DtoConverterService {

	public Hero convertEntityToDTO(HeroEntity heroEntity);
	public List<Hero> convertEntitiesToDTOs(List<HeroEntity> heroEntities);
	
	public HeroEntity convertDTOToEntity(Hero hero);
	public List<HeroEntity> convertDTOsToEntities(List<Hero> heroDTOs);
	
}
