package com.shortener.urlShortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shortener.urlShortener.model.Log;
import com.shortener.urlShortener.model.Statistics;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

	@Query("SELECT new com.shortener.urlShortener.model.Statistics(l.accessed, COUNT(l)) FROM Log l "
			+ "GROUP BY l.accessed ORDER BY COUNT(l) DESC")
	List<Statistics> findStatistics(); 
}
