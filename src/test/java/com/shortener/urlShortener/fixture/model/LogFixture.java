package com.shortener.urlShortener.fixture.model;

import java.util.Date;

import com.shortener.urlShortener.model.Log;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class LogFixture implements TemplateLoader {

	private final String SITE1 = "http://www.site1.com";

	private final String SITE2 = "http://www.site2com";

	public static final String VALID_LOG_DATA = "validLogData";

	@Override
	public void load() {
		Fixture.of(Log.class).addTemplate(VALID_LOG_DATA, new Rule() {
			{
				add("id", random(Long.class, range(1L, 5L)));
				add("accessed", uniqueRandom(SITE1, SITE2));
				add("accessedAt", new Date());
			}
		});
	}
}
