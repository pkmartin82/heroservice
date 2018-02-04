package com.pkm.hero.data.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pkm.hero.data.entity.HeroEntity;
import com.pkm.hero.data.entity.UniverseEntity;

public class HeroDataEntityTest {

	private static final String TEST_UNIVERSE_NAME = "TestUniverse";
	private static final Integer TEST_UNIVERSE_ID = Integer.MAX_VALUE;
	private static final String TEST_USER = "TestUser";
	private static final Timestamp TEST_TIME = new Timestamp((new Date()).getTime());
	private static final Integer TEST_HERO_ID = 34;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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

	@Test
	public void testUniverseEntity() {
		UniverseEntity universe = new UniverseEntity();
		assertTrue(universe instanceof UniverseEntity);

		universe.setUniverseId(TEST_UNIVERSE_ID);
		assertTrue(universe.getUniverseId().equals(TEST_UNIVERSE_ID));

		universe.setName(TEST_UNIVERSE_NAME);
		assertTrue(universe.getName().equals(TEST_UNIVERSE_NAME));

		universe.setInsertUser(TEST_USER);
		assertTrue(universe.getInsertUser().equals(TEST_USER));

		universe.setInsertTime(TEST_TIME);
		assertTrue(universe.getInsertTime().equals(TEST_TIME));

		universe.setUpdateUser(TEST_USER);
		assertTrue(universe.getUpdateUser().equals(TEST_USER));

		universe.setUpdateTime(TEST_TIME);
		assertTrue(universe.getUpdateTime().equals(TEST_TIME));
	}

	@Test
	public void testHeroEntity() {
		HeroEntity hero = new HeroEntity();
		HeroEntity antiHero = new HeroEntity();
		assertTrue(hero.equals(antiHero));

		antiHero.setHeroId(TEST_HERO_ID + 1);
		assertFalse(hero.equals(antiHero));
		
		hero.setHeroId(TEST_HERO_ID);
		assertTrue(hero.getHeroId().equals(TEST_HERO_ID));

		assertFalse(hero.equals(null));

		assertFalse(hero.equals(new UniverseEntity()));
		
		antiHero.setCatchphrase("your ass is grass");
		assertFalse(hero.equals(antiHero));

		
	}
}
