package com.shortener.urlShortener.controller;

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
import org.springframework.web.servlet.view.RedirectView;

import com.shortener.urlShortener.service.LogService;
import com.shortener.urlShortener.service.UrlService;

@RestController
public class UrlController {

	@Autowired
	private UrlService urlService;

	@Autowired
	private LogService logService;

	@GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> showWelcome() {
		String welcome = "Welcome to URL Shotener";
		return ResponseEntity.status(HttpStatus.OK).body(welcome);
	}
	
	@GetMapping(value = "/fail", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> showError() {
		String error = "Was not possible to Redirect this URL";

		return ResponseEntity.status(HttpStatus.OK).body(error);
	}
	
	@PostMapping(value = "/shortener", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getShortened(@RequestBody final String original) {
		String shortenedUrl = urlService.getShortenedUrl(original);

		return ResponseEntity.status(HttpStatus.OK).body(shortenedUrl);
	}

	/*
	 * @GetMapping("/{hash}") public void redirect(@PathVariable(name = "hash")
	 * final String hash, final HttpServletResponse response) { String originalUrl =
	 * urlService.getOriginalUrl(hash);
	 * 
	 * if (StringUtils.hasText(originalUrl)) {
	 * 
	 * log.info("Redirecting"); logService.save(originalUrl);
	 * 
	 * response.setHeader("Location", originalUrl); response.setStatus(302);
	 * 
	 * return; } log.error("Was not possible to redirect"); }
	 */

	@GetMapping("/{hash}")
	public RedirectView redirect(@PathVariable(name = "hash") final String hash) {
		String originalUrl = urlService.getOriginalUrl(hash);

		if (StringUtils.hasText(originalUrl)) {
			logService.save(originalUrl);
			
			return new RedirectView(originalUrl);
		}
		
		return new RedirectView("/fail");
		
	}
}
