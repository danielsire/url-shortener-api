package com.shortener.urlShortener.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shortener.urlShortener.component.UniqueHash;
import com.shortener.urlShortener.model.Url;
import com.shortener.urlShortener.repository.UrlRepository;

@Service
public class UrlService {

	@Autowired
	private UrlRepository repository;
	
	@Autowired
	private UniqueHash uniqueHash;
	
	public String getOriginalUrl(String hash) {
		List<Url> urls = repository.findByHash(hash);
		
		if (!CollectionUtils.isEmpty(urls)) {
			return urls.get(0).getOriginal();
		}
		
		return "";
	}
	
	public String getShortenedUrl(String originalUrl) {
		if (StringUtils.hasText(originalUrl)) {
			String hash = uniqueHash.getAlphanumericHash();
			if (saveUrl(originalUrl, hash)) {
				return getShortUrl(hash);
			}			
		}
		return "";
	}

	private Boolean saveUrl(String originalUrl, String hash) {
		Url url = new Url(originalUrl, hash);
		
		return repository.save(url) != null;
	}
	
	private String getShortUrl(String hash) {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
		builder.replacePath(hash);
		URI shortUri = builder.build().toUri();
		
		return shortUri.toString();
	}
	
	
	
}
