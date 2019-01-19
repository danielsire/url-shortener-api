package com.shortener.urlShortener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortener.urlShortener.model.Log;
import com.shortener.urlShortener.model.Statistics;
import com.shortener.urlShortener.repository.LogRepository;

@Service
public class LogService {

	@Autowired
	private LogRepository repository;
	
	public List<Statistics> getStatistics() {
		return repository.findStatistics();
	}
	
	public Log save(String accessed) {
		
		Log log = new Log(accessed);
		
		return repository.save(log);
	}	
}
