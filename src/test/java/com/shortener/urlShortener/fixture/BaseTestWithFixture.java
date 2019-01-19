package com.shortener.urlShortener.fixture;

import org.junit.BeforeClass;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class BaseTestWithFixture {

	protected static String FIXTURES_PATH = "com.shortener.urlShortener.fixture";

    @BeforeClass
    public static void beforeTestClass() {
        FixtureFactoryLoader.loadTemplates(FIXTURES_PATH);
    }
	
}
