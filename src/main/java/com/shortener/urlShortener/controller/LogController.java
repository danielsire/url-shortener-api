package com.shortener.urlShortener.controller;

import java.util.List;

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
	
	@Autowired
	private LogService logService;
	
	@GetMapping(value="/statistics", produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Statistics>> getStatistics() {
			List<Statistics> statistics = logService.getStatistics();
			
			return ResponseEntity.status(HttpStatus.OK).body(statistics);
	}
}
