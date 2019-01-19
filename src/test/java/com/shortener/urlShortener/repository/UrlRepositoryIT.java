package com.shortener.urlShortener.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.shortener.urlShortener.model.Url;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("tests")
public class UrlRepositoryIT {

	private final String SITE1 = "http://www.site1.com";

	private final String SITE2 = "http://www.site2com";

	private final String HASH1 = "4MVn4M1I";
	
	private final String HASH2 = "P0klWPyd";
	
	@Autowired
	private UrlRepository repository;
	
	@Before
	public void init() {
		repository.deleteAll();
		
		repository.save(new Url(SITE1, HASH1));
		
		repository.save(new Url(SITE2, HASH2));
	}
	 
	@Test
	public void shouldReturnUrlByHash() {
		List<Url> urls = repository.findByHash(HASH1);
		
		Assert.assertNotNull(urls);
		
		Assert.assertTrue(urls.size() == 1);
		
		Assert.assertEquals(SITE1, urls.get(0).getOriginal());
	}
	
}
