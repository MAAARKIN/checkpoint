package br.com.checkpoint.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class DefaultEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
	private Long id;
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
