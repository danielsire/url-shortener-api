package com.shortener.urlShortener.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Log implements Serializable {
	
	private static final long serialVersionUID = -5901116681724814183L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NonNull
	private String accessed;
	
	@Column(columnDefinition="TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date accessedAt;
	
	@PrePersist
	@PreUpdate
	void preInsert() {
	   if (this.accessedAt == null)
	       this.accessedAt = new Date();
	}
	

}
