package com.pkm.hero.service.data.test;

import static org.junit.Assert.*;

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

import com.pkm.hero.service.data.entity.HeroEntity;
import com.pkm.hero.service.data.entity.UniverseEntity;
import com.pkm.hero.service.data.service.HeroDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/HeroData_ApplicationConfig.xml")
public class HeroDataServiceTest {

	/** Default Universe Id */
	private final static int DEFAULT_UNIVERSE_ID = 616;

	/** Hero object with which to test */
	private static HeroEntity testHero;

	@Autowired
	private HeroDataService heroDataService;

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
			builder.bind("java:/datasources/HeroDS", dataSource);
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
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Tests all CRUD operations provided by the Hero Rest WebService
	 */
	@Test
	public void testAddUpdateDeleteHero() {

		testSaveHero();
		testUpdateHero();
		testDeleteHero();
	}

	@Test
	public void testGetHeroes() {
		assertNotNull(heroDataService);

		List<HeroEntity> heroes = heroDataService.getHeroes();

		assertNotNull(heroes);
		assertTrue(heroes instanceof List<?>);

		for (HeroEntity hero : heroes) {
			System.out.println(hero);
		}
		System.out.println();
	}

	@Test
	public void testGetHeroNames() {
		assertNotNull(heroDataService);

		List<String> heroNames = heroDataService.getHeroNames();

		assertNotNull(heroNames);
		assertTrue(heroNames instanceof List<?>);

		for (String heroName : heroNames) {
			System.out.println(heroName);
		}
		System.out.println();
	}

	public void testSaveHero() {

		try {

			// Assert that the test hero is not in the list of current heroes
			List<HeroEntity> heroes_pre = heroDataService.getHeroes();

			assertFalse(heroes_pre.contains(testHero));

			// Add the test hero, and assert that the hero returned-as-saved is
			// equivalent to the test hero
			HeroEntity savedHero = heroDataService.addHero(testHero);

			System.out.println(testHero);
			System.out.println(savedHero);

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
			List<HeroEntity> heroes_post = heroDataService.getHeroes();

			assertTrue(heroes_post.contains(savedHero));

			// Save the savedHero in the testHero field
			testHero = savedHero;
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	public void testUpdateHero() {

		try {

			// Assert that the test hero is in the list of current heroes
			List<HeroEntity> heroes_pre = heroDataService.getHeroes();

			assertTrue(heroes_pre.contains(testHero));

			// Update the test hero with a new catchphrase
			testHero.setCatchphrase("My Test Catchphrase!");

			HeroEntity updatedHero = heroDataService.updateHero(testHero);

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
			List<HeroEntity> heroes_post = heroDataService.getHeroes();

			assertTrue(heroes_post.contains(updatedHero));

			// Save the updatedHero in the testHero field
			testHero = updatedHero;
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

	public void testDeleteHero() {
		try {

			// Assert that the test hero is in the list of current heroes
			List<HeroEntity> heroes_pre = heroDataService.getHeroes();

			assertTrue(heroes_pre.contains(testHero));

			// Remove the test hero
			heroDataService.deleteHero(testHero.getHeroId());

			// Assert that that test hero is now in the list of current heroes
			List<HeroEntity> heroes_post = heroDataService.getHeroes();

			assertFalse(heroes_post.contains(testHero));
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail("Failed with RuntimeException: " + e);
		}
	}

}
