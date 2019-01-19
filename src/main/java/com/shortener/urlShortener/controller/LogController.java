package com.shortener.urlShortener.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.urlShortener.model.Statistics;
import com.shortener.urlShortener.service.LogService;

@RestController
public class LogController {

	private static final Logger log = LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	
	@GetMapping(value="/statistics", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Statistics>> getStatistics() {
		try {
			List<Statistics> statistics = logService.getStatistics();
			
			return ResponseEntity.status(HttpStatus.OK).body(statistics);
		} catch (Exception e) {
			log.error(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
}
