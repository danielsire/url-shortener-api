package com.shortener.urlShortener.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.RedirectView;

import com.shortener.urlShortener.fixture.BaseTestWithFixture;
import com.shortener.urlShortener.fixture.model.LogFixture;
import com.shortener.urlShortener.model.Log;
import com.shortener.urlShortener.service.LogService;
import com.shortener.urlShortener.service.UrlService;

import br.com.six2six.fixturefactory.Fixture;

@WebMvcTest(UrlController.class)
@RunWith(SpringRunner.class)
public class UrlControllerTest extends BaseTestWithFixture {

	private final String SITE = "http://www.site1.com";

	private final String HASH = "/P0klWPyd";
	
	private final String SHORT_SITE = "http://localhost".concat(HASH);

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	@InjectMocks
	private UrlController controller;

	@MockBean
	private UrlService urlService;
	
	@MockBean
	private LogService logService;

	private final String resource = "/shortener";

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void ShouldReturnDefaultUrlOk() throws Exception {
	
		mvc.perform(get("/")
				.contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE));
	}
	
	@Test
	public void ShouldShortenedUrlReturnOk() throws Exception {
		Mockito.when(urlService.getShortenedUrl(SITE)).thenReturn(SHORT_SITE);

		mvc.perform(post(resource)
				.contentType(MediaType.TEXT_PLAIN_VALUE)
				.characterEncoding("utf-8")
				.content(SITE))
				.andExpect(status().isOk())
				.andExpect(content().string(SHORT_SITE));
	}
	
	@Test
	public void ShouldRedirectToFail() throws Exception {
		
		Mockito.when(urlService.getOriginalUrl("")).thenReturn("");
		
		RedirectView redirect = controller.redirect("");
		
		Assert.assertTrue(redirect.getUrl().equals("/fail"));
		
		verify(urlService, times(1)).getOriginalUrl("");
	}
	
	@Test
	public void ShouldRedirect() throws Exception {
		Log expectedLog = Fixture.from(Log.class).gimme(LogFixture.VALID_LOG_DATA);
		
		Mockito.when(logService.save(SITE)).thenReturn(expectedLog);
		
		Mockito.when(urlService.getOriginalUrl(HASH)).thenReturn(SITE);
		
		//mvc.perform(get(HASH))
				//.andExpect(status().is3xxRedirection());
				//.andExpect(redirectedUrl(SITE));
		
		RedirectView redirect = controller.redirect(HASH);
		
		Assert.assertTrue(redirect.getUrl().equals(SITE));
		
		
		verify(urlService, times(1)).getOriginalUrl(HASH);
	}

}
