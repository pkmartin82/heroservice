package com.pkm.hero.service.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.dbcp.cpdsadapter.DriverAdapterCPDS;
import org.apache.commons.dbcp.datasources.SharedPoolDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pkm.hero.service.data.dao.HeroDao;
import com.pkm.hero.service.data.entity.HeroEntity;
import com.pkm.hero.service.data.entity.UniverseEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/HeroServiceData_ApplicationConfig.xml")
@Transactional(value = "heroTransactionManager", propagation = Propagation.SUPPORTS)
// @TransactionConfiguration(transactionManager = "heroTransactionManager",
// defaultRollback = true)
public class HeroDataDaoTest {

	/** Default Universe Id */
	private final static int DEFAULT_UNIVERSE_ID = 616;

	/** Hero object with which to test */
	private static HeroEntity testHero;

	@Autowired
	private HeroDao heroDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

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
		testHero = this.getNewHeroEntity();
	}

	@After
	public void tearDown() throws Exception {

		// Ensure that there are no remaining heroes in the repository
		// that were created by this Test class
		List<HeroEntity> remainingHeroes = heroDao.getAll();

		Iterator<HeroEntity> iter = remainingHeroes.iterator();

		while (iter.hasNext()) {
			HeroEntity hero = iter.next();
			if (hero.getInsertUser().equals(getUserId())) {
				heroDao.delete(hero.getHeroId());
			}
		}
	}

	@Test
	public void testGetHeroes() {
		assertNotNull(heroDao);
		try {
			List<HeroEntity> heroes = heroDao.getAll();

			assertNotNull(heroes);
			assertTrue(heroes instanceof List<?>);

		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testGetHeroNames() {
		assertNotNull(heroDao);
		try {
			heroDao.save(testHero);

			List<String> heroNames = heroDao.getHeroNames();

			assertNotNull(heroNames);
			assertTrue(heroNames instanceof List<?>);
			assertTrue(heroNames.contains(testHero.getName()));

			for (String heroName : heroNames) {
				System.out.println(heroName);
			}
			System.out.println();
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testGetHeroById() {
		assertNotNull(heroDao);
		try {

			// Save the test Hero Entity
			heroDao.save(testHero);

			// Assert that the test hero is not in the list of current heroes
			HeroEntity dbHero = heroDao.get(testHero.getHeroId());

			assertNotNull(dbHero);
			assertTrue(dbHero instanceof HeroEntity);
			assertTrue(dbHero.getName().equals(testHero.getName()));
			assertTrue(dbHero.getSecretIdentity().equals(testHero.getSecretIdentity()));
			assertTrue(dbHero.getNumOthersWhoKnowSecret().equals(testHero.getNumOthersWhoKnowSecret()));
			assertTrue(dbHero.getUniverse().getUniverseId().equals(testHero.getUniverse().getUniverseId()));
			assertNull(dbHero.getCatchphrase());
			assertNotNull(dbHero.getInsertTime());
			assertNotNull(dbHero.getInsertUser());
			assertNull(dbHero.getUpdateTime());
			assertNull(dbHero.getUpdateUser());
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testGetHeroByNameAndIdentity() {
		assertNotNull(heroDao);
		try {
			heroDao.save(testHero);

			// Assert that the test hero is not in the list of current heroes
			List<HeroEntity> heroes = heroDao.getHeroes(testHero.getName(), testHero.getSecretIdentity());

			assertNotNull(heroes);
			assertTrue(heroes instanceof List<?>);
			assertTrue(heroes.size() == 1);

			HeroEntity hero = heroes.get(0);

			assertNotNull(hero);
			assertTrue(hero instanceof HeroEntity);
			assertTrue(hero.getName().equals(testHero.getName()));
			assertTrue(hero.getSecretIdentity().equals(testHero.getSecretIdentity()));
			assertTrue(hero.getNumOthersWhoKnowSecret().equals(testHero.getNumOthersWhoKnowSecret()));
			assertTrue(hero.getUniverse().getUniverseId().equals(testHero.getUniverse().getUniverseId()));
			assertNull(hero.getCatchphrase());
			assertNotNull(hero.getInsertTime());
			assertNotNull(hero.getInsertUser());
			assertNull(hero.getUpdateTime());
			assertNull(hero.getUpdateUser());
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testSaveHero() {
		assertNotNull(heroDao);
		try {

			// Assert that the test hero is not in the list of current heroes
			List<HeroEntity> heroes_pre = heroDao.getAll();

			assertFalse(heroes_pre.contains(testHero));

			// Add the test hero, and assert that the hero returned-as-saved is
			// equivalent to the test hero
			HeroEntity savedHero = heroDao.save(testHero);

			assertNotNull(savedHero);
			assertTrue(savedHero instanceof HeroEntity);
			assertTrue(savedHero.getName().equals(testHero.getName()));
			assertTrue(savedHero.getSecretIdentity().equals(testHero.getSecretIdentity()));
			assertTrue(savedHero.getNumOthersWhoKnowSecret().equals(testHero.getNumOthersWhoKnowSecret()));
			assertTrue(savedHero.getUniverse().getUniverseId().equals(testHero.getUniverse().getUniverseId()));
			assertNull(savedHero.getCatchphrase());
			assertNotNull(savedHero.getInsertTime());
			assertNotNull(savedHero.getInsertUser());
			assertNull(savedHero.getUpdateTime());
			assertNull(savedHero.getUpdateUser());

			// Assert that that test hero is now in the list of current heroes
			List<HeroEntity> heroes_post = heroDao.getAll();

			assertTrue(heroes_post.contains(savedHero));

			// Save the savedHero in the testHero field
			testHero = savedHero;
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testUpdateHero() {
		assertNotNull(heroDao);
		try {

			heroDao.save(testHero);

			// Assert that the test hero is in the list of current heroes
			List<HeroEntity> heroes_pre = heroDao.getAll();

			assertTrue(heroes_pre.contains(testHero));

			// Update the test hero with a new catchphrase
			testHero.setCatchphrase("My Test Catchphrase!");
			testHero.setUpdateTime(getNowTimestamp());
			testHero.setUpdateUser(getUserId());

			HeroEntity updatedHero = heroDao.save(testHero);

			assertNotNull(updatedHero);
			assertTrue(updatedHero instanceof HeroEntity);
			assertTrue(updatedHero.getName().equals(testHero.getName()));
			assertTrue(updatedHero.getSecretIdentity().equals(testHero.getSecretIdentity()));
			assertTrue(updatedHero.getNumOthersWhoKnowSecret().equals(testHero.getNumOthersWhoKnowSecret()));
			assertTrue(updatedHero.getUniverse().getUniverseId().equals(testHero.getUniverse().getUniverseId()));
			assertNotNull(updatedHero.getInsertTime());
			assertNotNull(updatedHero.getInsertUser());
			assertNotNull(updatedHero.getUpdateTime());
			assertNotNull(updatedHero.getUpdateUser());

			// Assert the new Catchphrase
			assertNotNull(updatedHero.getCatchphrase());
			assertEquals(updatedHero.getCatchphrase(), testHero.getCatchphrase());

			// Assert that that test hero is now in the list of current heroes
			List<HeroEntity> heroes_post = heroDao.getAll();

			assertTrue(heroes_post.contains(updatedHero));
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testDeleteHero() {
		assertNotNull(heroDao);
		try {
			heroDao.save(testHero);

			// Assert that the test hero is in the list of current heroes
			List<HeroEntity> heroes_pre = heroDao.getAll();

			assertTrue(heroes_pre.contains(testHero));

			// Remove the test hero
			heroDao.delete(testHero.getHeroId());

			// Assert that that test hero is now in the list of current heroes
			List<HeroEntity> heroes_post = heroDao.getAll();

			assertFalse(heroes_post.contains(testHero));
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testDeleteHeroThatDoesNotExist() {
		assertNotNull(heroDao);
		try {

			// Get the list of current heroes
			List<HeroEntity> heroes = heroDao.getAll();

			Integer maxHeroId = 0;

			// Get the current maximum heroId
			if ((heroes != null) && (!heroes.isEmpty())) {
				for (HeroEntity hero : heroes) {
					maxHeroId = Math.max(maxHeroId.intValue(), hero.getHeroId());
				}
			}

			// Add 1 to the maximum heroId to get a heroId that does
			// not exist
			Integer bogusHeroId = maxHeroId += 1;

			// Remove the bogus heroId
			HeroEntity bogusHero = heroDao.delete(bogusHeroId);

			assertNull(bogusHero);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	@Test
	public void testCountAll() {

		Long initialCount = heroDao.countAll();
		assertNotNull(initialCount);

		heroDao.save(testHero);

		Long postSaveCount = heroDao.countAll();
		assertNotNull(postSaveCount);
		assertTrue(postSaveCount.intValue() == initialCount.intValue() + 1);
	}

	private HeroEntity getNewHeroEntity() {

		// Set up the Test HeroEntity
		long time = System.currentTimeMillis();

		HeroEntity newHero = new HeroEntity();
		newHero.setName("TestHero_" + time);
		newHero.setSecretIdentity("TestIdentity_" + time);
		newHero.setNumOthersWhoKnowSecret(-1);

		UniverseEntity universe = new UniverseEntity();
		universe.setUniverseId(DEFAULT_UNIVERSE_ID);

		newHero.setUniverse(universe);
		newHero.setInsertTime(getNowTimestamp());
		newHero.setInsertUser(getUserId());

		return (newHero);
	}

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
		return HeroDataDaoTest.class.getSimpleName();
	}
}
