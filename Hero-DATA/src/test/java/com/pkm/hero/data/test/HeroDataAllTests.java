package com.pkm.hero.data.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HeroDataDaoTest.class, HeroDataServiceTest.class, 
	HeroDataEntityTest.class,
	HeroDataServiceTestFailuresTest.class })
public class HeroDataAllTests {

	/**
	 * Test Suite created for convenience when trying to assess Code Coverage.
	 * 
	 * When run via Maven pom.xml, use that process's default ability to scan
	 * for classes that end in *Test.java to determine what Tests to run.
	 */
}
