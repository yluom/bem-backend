package com.bem.bembackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bem.bembackend.domain.Event;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends JpaRepository<Event, Long> {

	/**
 * Retrieves a list of Event entities with the specified name.
 *
 * @param name the name to filter events by
 * @return a list of events matching the given name
 */
List<Event> findByName(@Param("name") String name);

}