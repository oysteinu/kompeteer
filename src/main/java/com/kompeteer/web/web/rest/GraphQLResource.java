package com.kompeteer.web.web.rest;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@Transactional
@RequestMapping("/api")
public class GraphQLResource {
	private final Logger log = LoggerFactory.getLogger(GraphQLResource.class);
	
	private final GraphQLService service;
	
	public GraphQLResource(GraphQLService service) {
		this.service = service;
	}
	
	@GetMapping("/graphql")
	@Timed
	public ExecutionResult query(@RequestParam("query") String query) throws URISyntaxException {
		return service.query(query);
	}
	
	@PostMapping("/graphql")
	@Timed
	public ExecutionResult post(@RequestBody Map body) throws URISyntaxException {
		String query = (String) body.get("query");
	    
		Map<String, Object> variables = (Map<String, Object>) body.get("variables");
	    
		return service.query(query, null, variables);
		
		// return service.query(query.get("query").asText());
		
	}
}
