package com.pkm.hero.service.data.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.pkm.hero.data.dao.HeroDao;
import com.pkm.hero.service.data.entity.HeroEntity;
import com.pkm.hero.service.data.entity.UniverseEntity;
import com.pkm.hero.service.data.service.HeroDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/HeroServiceData_TestServiceApplicationConfig.xml")
public class HeroDataServiceTestFailuresTest {

	/** Default Universe Id */
	private final static int DEFAULT_UNIVERSE_ID = 616;

	/** Hero object with which to test */
	private static HeroEntity testHero;

	@Autowired
	private HeroDataService heroDataService;

//	@Autowired
//	private HeroDao actualHeroDao;

//	private SessionFactory mockedSessionFactory;
//	private Session mockedSession = Mockito.mock(Session.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// Set up the Test HeroEntity
		long time = System.currentTimeMillis();

		testHero = new HeroEntity();
		testHero.setName("TestHero_" + time);
		testHero.setSecretIdentity("TestIdentity_" + time);
		testHero.setNumOthersWhoKnowSecret(-1);

		UniverseEntity universe = new UniverseEntity();
		universe.setUniverseId(DEFAULT_UNIVERSE_ID);

		testHero.setUniverse(universe);

		// Set up the DataSource so it can be bound to the JNDI name, (in lieu
		// of the app server doing this)
		try {
			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
			DriverAdapterCPDS cpds = new DriverAdapterCPDS();
			cpds.setDriver("com.mysql.jdbc.Driver");
			cpds.setUrl("jdbc:mysql://localhost:3306/pkm");
			cpds.setUser("root");
			cpds.setPassword("heynow");

			SharedPoolDataSource dataSource = new SharedPoolDataSource();
			dataSource.setConnectionPoolDataSource(cpds);
			dataSource.setMaxActive(10);
			dataSource.setMaxWait(50);
			builder.bind("java:comp/env/jdbc/HeroDS", dataSource);
			builder.activate();
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
//	    mockedSessionFactory =  Mockito.mock(SessionFactory.class);
//	    mockedSession = Mockito.mock(Session.class);
//	    Mockito.when(mockedSessionFactory.getCurrentSession()).thenThrow(HibernateException.class);
//		
//		// Insert the Stub HeroDao into the HeroDataService for the purpose of
//		// Exception-Path testing
//		ReflectionTestUtils.setField((HeroDataServiceImpl) unwrapProxy(HeroDataServiceImpl.class, heroDataService),
//				"heroDao", new BadHeroDaoImpl(), HeroDao.class);
	}

	@After
	public void tearDown() throws Exception {
//		// Replace the Stub HeroDao with the Actual HeroDao in the
//		// HeroDataService after the test has completed
//		ReflectionTestUtils.setField((HeroDataServiceImpl) unwrapProxy(HeroDataServiceImpl.class, heroDataService),
//				"heroDao", actualHeroDao, HeroDao.class);
	}

	@SuppressWarnings("unused")
	@Test(expected = HibernateException.class)
	public void testGetHeroes() {
		assertNotNull(heroDataService);

		List<HeroEntity> heroes = heroDataService.getHeroes();
	}

	@SuppressWarnings("unused")
	@Test(expected = HibernateException.class)
	public void testGetHeroNames() {
		assertNotNull(heroDataService);

		List<String> heroNames = heroDataService.getHeroNames();
	}

	@SuppressWarnings("unused")
	@Test(expected = HibernateException.class)
	public void testSaveHero() {
		assertNotNull(heroDataService);

		HeroEntity hero = heroDataService.addHero(testHero);
	}

	@SuppressWarnings("unused")
	@Test(expected = HibernateException.class)
	public void testUpdateHero() {
		assertNotNull(heroDataService);

		HeroEntity hero = heroDataService.updateHero(testHero);
	}

	@SuppressWarnings("unused")
	@Test(expected = HibernateException.class)
	public void testDeleteHero() {
		assertNotNull(heroDataService);

		HeroEntity hero = heroDataService.deleteHero(testHero.getHeroId());
	}

	/**
	 * Unwraps a Spring Proxied-object so that ReflectionTestUtils can set
	 * private fields
	 * 
	 * @param clazz
	 * @param proxy
	 * @return
	 */
	@SuppressWarnings("unused")
	private Object unwrapProxy(Class<?> clazz, Object proxy) {
		if (AopUtils.isAopProxy(proxy) && proxy instanceof Advised) {
			try {
				Object target = ((Advised) proxy).getTargetSource().getTarget();
				return target;
			} catch (Exception e) {
			}
		}
		return proxy;
	}
}
