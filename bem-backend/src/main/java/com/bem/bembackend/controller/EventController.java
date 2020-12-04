package com.bem.bembackend.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bem.bembackend.domain.Event;

@RestController
public class EventController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/event")
	public Event sampleEvent(@RequestParam(value = "name", defaultValue = "WBC") String name) {
		return new Event(counter.incrementAndGet(), name);
	}

	@RequestMapping("/")
	public String index() {
		return "Hello Springboooot";
	}
}