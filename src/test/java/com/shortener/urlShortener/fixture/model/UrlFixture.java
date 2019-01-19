package com.shortener.urlShortener.fixture.model;

import java.util.Date;

import com.shortener.urlShortener.model.Url;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UrlFixture implements TemplateLoader {

	private final String SITE1 = "http://www.site1.com";

	private final String SITE2 = "http://www.site2com";

	private final String HASH1 = "4MVn4M1I";

	private final String HASH2 = "P0klWPyd";

	public static final String VALID_URL_DATA = "validUrlData";

	@Override
	public void load() {
		Fixture.of(Url.class).addTemplate(VALID_URL_DATA, new Rule() {
			{
				add("id", random(Long.class, range(1L, 5L)));
				add("original", uniqueRandom(SITE1, SITE2));
				add("hash", uniqueRandom(HASH1, HASH2));
				add("createdAt", new Date());
			}
		});
	}
	
}
