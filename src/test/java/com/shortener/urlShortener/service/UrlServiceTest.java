package com.shortener.urlShortener.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.shortener.urlShortener.fixture.BaseTestWithFixture;
import com.shortener.urlShortener.fixture.model.UrlFixture;
import com.shortener.urlShortener.model.Url;
import com.shortener.urlShortener.repository.UrlRepository;

import br.com.six2six.fixturefactory.Fixture;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("tests")
public class UrlServiceTest extends BaseTestWithFixture {

	@Autowired
	@InjectMocks
	private UrlService service;
	
	@Mock
	private UrlRepository repository;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldReturnEmptyWhenHashNotFound() {
		List<Url> expected = new ArrayList<Url>();
		
		String hash = "test";
		
		Mockito.when(repository.findByHash(hash)).thenReturn(expected);
		
		String url = service.getOriginalUrl(hash);
		
		Assert.assertNotNull(url);
		
		Assert.assertTrue(StringUtils.isEmpty(url));
	}
	
	@Test
	public void shouldReturnUrlWhenFoundByHash() {
		List<Url> expected = Fixture.from(Url.class).gimme(1, UrlFixture.VALID_URL_DATA);
		
		String hash = "test";
		
		Mockito.when(repository.findByHash(hash)).thenReturn(expected);
		
		String url = service.getOriginalUrl(hash);
		
		Assert.assertNotNull(url);
		
		Assert.assertFalse(StringUtils.isEmpty(url));
	}
	
	@Test
	public void shouldReturnEmptyNoUrlSended() {
		String url = service.getShortenedUrl("");
		
		String url2 = service.getShortenedUrl(null);
		
		Assert.assertNotNull(url);
		Assert.assertNotNull(url2);
		
		Assert.assertTrue(StringUtils.isEmpty(url));
		Assert.assertTrue(StringUtils.isEmpty(url2));
	}
	
	@Test
	public void shouldReturnShortenedUrlWhenUrlSended() {
		Url expected = Fixture.from(Url.class).gimme(UrlFixture.VALID_URL_DATA);
		
		Mockito.when(repository.save(new Url(expected.getOriginal(), expected.getHash()))).thenReturn(expected);
		
		String url = service.getShortenedUrl(expected.getOriginal());
		
		Assert.assertNotNull(url);
		
		Assert.assertFalse(StringUtils.isEmpty(url));
	}
	
}
