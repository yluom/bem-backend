package com.bem.bembackend.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bem_events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	public String name;

//	@Column(name = "startDateTime", nullable = true)
//	private LocalDateTime startDateTime;

	@Column(nullable = true)
	public Double latitude;

	@Column(nullable = true)
	public Double longitude;

	protected Event() {
	};


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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", ");
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		builder.append("latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append("]");
		return builder.toString();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

//	public LocalDateTime getStartDateTime() {
//		return startDateTime;
//	}
//
//	public void setStartDateTime(LocalDateTime startDateTime) {
//		this.startDateTime = startDateTime;
//	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}
