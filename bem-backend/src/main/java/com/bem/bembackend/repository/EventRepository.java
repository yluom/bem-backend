package com.bem.bembackend.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bem.bembackend.domain.Event;

@RepositoryRestResource(collectionResourceRel = "event", path = "events")
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

	List<Event> findByName(@Param("name") String name);

	Event findById(long id);

}