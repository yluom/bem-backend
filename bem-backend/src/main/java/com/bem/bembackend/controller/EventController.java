package com.bem.bembackend.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bem.bembackend.domain.Event;
import com.bem.bembackend.repository.EventRepository;

@RestController
public class EventController {

	@Resource
	public EventRepository repo;

	@GetMapping("/addEvent")
	public Event sampleEvent(@RequestParam(value = "name", defaultValue = "WBC") String name) {
		Event e = new Event(name);
		repo.save(e);
		return e;
	}

	@RequestMapping("/hello")
	public String index() {
		return "Hello Springboooot";
	}
}