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

	/**
	 * Protected no-argument constructor required by JPA for entity instantiation.
	 */
	protected Event() {
		// JPA requirement
	}

	/**
	 * Constructs an Event with the specified id and name.
	 *
	 * @param id   the unique identifier for the event
	 * @param name the name of the event
	 */
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
