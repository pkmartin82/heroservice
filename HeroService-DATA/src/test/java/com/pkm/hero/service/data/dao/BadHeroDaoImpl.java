package com.pkm.hero.service.data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.pkm.hero.service.data.entity.HeroEntity;

public class BadHeroDaoImpl implements HeroDao {

	public BadHeroDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getHeroNames() {
		throw new HibernateException(new UnsupportedOperationException("getHeroNames()"));
	}

	@Override
	public HeroEntity save(HeroEntity hero) {
		throw new HibernateException(new UnsupportedOperationException("save(HeroEntity)"));
	}

	@Override
	public List<HeroEntity> getAll() {
		throw new HibernateException(new UnsupportedOperationException("getAll(Class<HeroEntity>)"));
	}

	@Override
	public List<HeroEntity> getHeroes(String heroName, String secretIdentity) {
		throw new HibernateException(new UnsupportedOperationException("getHeroes(String, String)"));
	}

	@Override
	public HeroEntity get(Integer heroId) {
		throw new HibernateException(new UnsupportedOperationException("get(Integer)"));
	}

	@Override
	public HeroEntity delete(Integer heroId) {
		throw new HibernateException(new UnsupportedOperationException("delete(Class<HeroEntity>, Integer)"));
	}

	@Override
	public Long countAll() {
		throw new HibernateException(new UnsupportedOperationException("countAll(Class<HeroEntity>"));
	}

	@Override
	public Class<HeroEntity> getEntityClass() {
		return null;
	}
}
