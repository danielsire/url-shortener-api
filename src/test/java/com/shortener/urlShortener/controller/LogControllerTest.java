package com.shortener.urlShortener.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shortener.urlShortener.fixture.BaseTestWithFixture;
import com.shortener.urlShortener.fixture.model.StatisticsFixture;
import com.shortener.urlShortener.model.Statistics;
import com.shortener.urlShortener.service.LogService;

import br.com.six2six.fixturefactory.Fixture;

@WebMvcTest(LogController.class)
@RunWith(SpringRunner.class)
public class LogControllerTest extends BaseTestWithFixture {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private LogService service;
	
	private final String resource = "/statistics";
	
	@Test
	public void shouldReturnStatisticsJson() throws Exception {
		
		List<Statistics> expectedStatistics = Fixture.from(Statistics.class).gimme(3, StatisticsFixture.VALID_STATISTICS_DATA);
		
		Mockito.when(service.getStatistics()).thenReturn(expectedStatistics);
	
		mvc.perform(get(resource))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray());
	}
	
}
