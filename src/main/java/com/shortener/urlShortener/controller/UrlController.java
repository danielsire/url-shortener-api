package com.shortener.urlShortener.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shortener.urlShortener.service.LogService;
import com.shortener.urlShortener.service.UrlService;

@RestController
public class UrlController {
	
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);

	@Autowired
	private UrlService urlService;
	
	@Autowired
	private LogService logService;
	
	@PostMapping(value="/shortener", produces= MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getShortened(@RequestBody final String original) {
	
		try {
			String shortenedUrl = urlService.getShortenedUrl(original);
			
			return ResponseEntity.status(HttpStatus.OK).body(shortenedUrl);
		} catch (Exception e) {
			log.error(e.getMessage());
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	@GetMapping("/{hash}")
	public void redirect(@PathVariable(name = "hash") final String hash, final HttpServletResponse response) {
		try {
			String originalUrl = urlService.getOriginalUrl(hash);
			
			if (StringUtils.hasText(originalUrl)) {
				
				log.info("Redirecting");
				logService.save(originalUrl);
				
				response.setHeader("Location", originalUrl);
				response.setStatus(302);
				
				return;
			}
		    
			log.error("Was not possible to redirect");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
