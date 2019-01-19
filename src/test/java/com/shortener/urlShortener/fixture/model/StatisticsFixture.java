package com.shortener.urlShortener.fixture.model;

import com.shortener.urlShortener.model.Statistics;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class StatisticsFixture implements TemplateLoader {

	private final String SITE1 = "http://www.site1.com";

	private final String SITE2 = "http://www.site2com";

	public static final String VALID_STATISTICS_DATA = "validLogData";

	@Override
	public void load() {
		Fixture.of(Statistics.class).addTemplate(VALID_STATISTICS_DATA, new Rule() {
			{
				add("accessed", uniqueRandom(SITE1, SITE2));
				add("times", random(Long.class, range(1L, 5L)));
			}
		});
	}
}
