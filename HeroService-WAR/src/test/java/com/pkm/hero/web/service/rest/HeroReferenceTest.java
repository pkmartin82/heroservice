package com.pkm.hero.web.service.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;

import com.pkm.hero.business.facade.HeroFacade;
import com.pkm.hero.dto.Hero;
import com.pkm.hero.web.startup.ApplicationContextProvider;

public class HeroReferenceTest {

	private static final Long COUNT_ALL_INIT_COUNT = 4L;

	private ApplicationContext mockedAc;
	private HeroFacade mockedHeroFacade;

	private int startId = 8200;
	private int idCount = 0;

	/** Hero object with which to test */
	private static Hero testHero;
	private List<Hero> testHeroes;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		testHero = getNewHero();

		if (testHeroes == null) {
			testHeroes = new ArrayList<Hero>();
		}

		for (int i = 0; i < COUNT_ALL_INIT_COUNT; i++) {
			Hero newHero = this.getNewHero();
			newHero.setHeroId(this.getNextId());
			testHeroes.add(newHero);
		}

		mockedAc = Mockito.mock(ApplicationContext.class);
		mockedHeroFacade = Mockito.mock(HeroFacade.class);
		
		Mockito.when(mockedAc.getBean(HeroFacade.class)).thenReturn(mockedHeroFacade);
		Mockito.doAnswer(new FacadeSaveHeroAnswer()).when(mockedHeroFacade).addHero(testHero);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddHeroHero() {
		HeroReference hr = new HeroReference();

		ApplicationContextProvider acp = new ApplicationContextProvider();
		acp.setApplicationContext(mockedAc);

		assert(testHero.getHeroId() == null);
		testHero = hr.addHero(testHero);
		assert(testHero.getHeroId() != null);
	}

	private Hero getNewHero() {
		Hero hero = new Hero();
		hero.setHeroName("NewHero_" + System.currentTimeMillis());
		hero.setNumOthersWhoKnowSecret(0);
		hero.setCatchphrase("Testing!!!");
		return (hero);
	}
	/**
	 * Returns a sequential identifier
	 * 
	 * @return
	 */
	private int getNextId() {
		return (startId + idCount++);
	}

	/**
	 * Returns the number of test Hero objects we are using
	 * @return
	 */
	@SuppressWarnings("unused")
	private long getNumberOfTestHeroes() {
		Long n = (long) testHeroes.size(); 
		return (n);
	}

	/**
	 * Answer for stubbing Hibernate's Session.saveOrUpdate(...) method
	 */
	private class FacadeSaveHeroAnswer implements Answer<Hero> {

		@Override
		public Hero answer(InvocationOnMock invocation) throws Throwable {
			Hero capturedHero = null;
			Object[] args = invocation.getArguments();
			if ((args[0] != null) && (args[0] instanceof Hero)) {
//				System.out.println("Captured Hero: " + args[0].toString());
				capturedHero = (Hero) args[0];

				// if Saving a new Hero
				if (capturedHero.getHeroId() == null) {
					capturedHero.setHeroId(getNextId());
					capturedHero.setInsertTime(new Date());
					capturedHero.setInsertUser("HeroReferenceTest");
					testHeroes.add(capturedHero);
				} else {
					// else Update the list by removing the old one...
					Iterator<Hero> iter = testHeroes.iterator();
					while (iter.hasNext()) {
						Hero ce = iter.next();
						if (ce.getHeroId().equals(capturedHero.getHeroId())) {
							iter.remove();
							break;
						}
					}
					// ...and add the new one
					capturedHero.setUpdateTime(new Date());
					capturedHero.setUpdateUser("HeroReferenceTest");
					testHeroes.add(capturedHero);
				}
			}
			return capturedHero;
		}
	}
}
