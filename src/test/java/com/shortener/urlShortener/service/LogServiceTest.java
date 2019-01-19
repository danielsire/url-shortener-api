package com.shortener.urlShortener.service;

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

import com.shortener.urlShortener.fixture.BaseTestWithFixture;
import com.shortener.urlShortener.fixture.model.LogFixture;
import com.shortener.urlShortener.fixture.model.StatisticsFixture;
import com.shortener.urlShortener.model.Log;
import com.shortener.urlShortener.model.Statistics;
import com.shortener.urlShortener.repository.LogRepository;

import br.com.six2six.fixturefactory.Fixture;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("tests")
public class LogServiceTest extends BaseTestWithFixture {
	
	@Autowired
	@InjectMocks
	private LogService service;
	
	@Mock
	private LogRepository repository;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shoulReturnLogWhenSaved() {
		Log expected = Fixture.from(Log.class).gimme(LogFixture.VALID_LOG_DATA);
		
		Mockito.when(repository.save(new Log(expected.getAccessed()))).thenReturn(expected);
		
		Log saved = service.save(expected.getAccessed());
		
		Assert.assertNotNull(saved);
	}
	
	@Test
	public void shouldReturnStatistics() {
		List<Statistics> expectedStatistics = Fixture.from(Statistics.class).gimme(3, StatisticsFixture.VALID_STATISTICS_DATA);
		
		Mockito.when(repository.findStatistics()).thenReturn(expectedStatistics);
		
		List<Statistics> statistics = service.getStatistics();
		
		Assert.assertNotNull(statistics);
		
		Assert.assertTrue(expectedStatistics.size() ==  statistics.size());
		
	}
	
}
