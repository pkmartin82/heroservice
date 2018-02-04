/**
 * 
 */
package com.pkm.hero.service.data.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.pkm.genericdao.dao.GenericDaoImpl;
import com.pkm.hero.service.data.entity.HeroEntity;

/**
 * @author patrick
 *
 */
@Repository("heroData_heroDao")
public class HeroDaoImpl extends GenericDaoImpl<HeroEntity, Integer> implements HeroDao {

	@Autowired
	@Qualifier("heroSessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * Default Constructor
	 */
	public HeroDaoImpl() {
		super();
	}

	@Override
	public Class<HeroEntity> getEntityClass() {
		return (HeroEntity.class);
	}

	@Override
	protected SessionFactory getQualifiedSessionFactory() {
		return (sessionFactory);
	}

	// ================================================================================================================
	// CREATE, UPDATE
	// ================================================================================================================

	// ================================================================================================================
	// READ
	// ================================================================================================================

	/**
	 * Gets list of heroes with the heroName and secretIdentity specified
	 * 
	 * @param heroName
	 * @param secretIdentity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HeroEntity> getHeroes(String heroName, String secretIdentity) {
		logger.info("Returning Heroes with the name " + heroName + ", and Secret Identity " + secretIdentity);
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(HeroEntity.class);
		criteria.add(Restrictions.eq("name", heroName));
		criteria.add(Restrictions.eq("secretIdentity", secretIdentity));
		return (List<HeroEntity>) criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getHeroNames() {
		logger.info("Returning all Hero Names!");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(HeroEntity.class);
		criteria.setProjection(Projections.property("name")); 
		return ((List<String>) criteria.list());
	}

	// ================================================================================================================
	// DELETE
	// ================================================================================================================

}
