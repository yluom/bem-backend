package com.bem.bembackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	public String name;

	protected Event() {
		// JPA requirement
	}

	public Event(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Event(String name) {
		super();
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
