package com.shortener.urlShortener.component;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class UniqueHash {

	public String getAlphanumericHash() {
		return RandomStringUtils.randomAlphanumeric(8);
	}
	
}
