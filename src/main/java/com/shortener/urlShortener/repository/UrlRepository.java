package com.shortener.urlShortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortener.urlShortener.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

	List<Url> findByHash(String hash);
}
