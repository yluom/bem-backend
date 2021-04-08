package com.bem.bembackend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.bem.bembackend.domain.Event;

@Configuration
public class RepositoryConfiguration implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Event.class);

		// remove _embedded
//		config.setDefaultMediaType(MediaType.APPLICATION_JSON);
//		config.useHalAsDefaultJsonMediaType(false);
	}

	@Bean
	// This code enables logging requests
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA : ");
		return filter;
	}
}