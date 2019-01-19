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

import com.shortener.urlShortener.model.Log;
import com.shortener.urlShortener.model.Statistics;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("tests")
public class LogRepositoryIT {

	private final String SITE1 = "http://www.site1.com";
	
	private final String SITE2 = "http://www.site2com";
	
	@Autowired
	private LogRepository repository;
	
	@Before
	public void init() {
		repository.deleteAll();
		
		repository.save(new Log(SITE1));
		repository.save(new Log(SITE1));
		repository.save(new Log(SITE2));
	}
	
	@Test
	public void shouldReturnStatistics() {
		Long expectedOneTime = new Long(1l);
		Long expectedTwoTimes = new Long(2l);
		
		List<Statistics> findStatistics = repository.findStatistics();
		
		Assert.assertNotNull(findStatistics);
		
		Assert.assertTrue(findStatistics.size() == 2);
		
		Assert.assertEquals(SITE1, findStatistics.get(0).getAccessed());
		Assert.assertEquals(expectedTwoTimes, findStatistics.get(0).getTimes());
		
		Assert.assertEquals(SITE2, findStatistics.get(1).getAccessed());
		Assert.assertEquals(expectedOneTime, findStatistics.get(1).getTimes());
	}
}
