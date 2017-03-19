package com.kompeteer.web.web.rest;

import java.net.URISyntaxException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.service.GraphQLService;

import graphql.ExecutionResult;

@RestController
@Transactional
@RequestMapping("/api/graphql")
public class GraphQLResource {
	private final GraphQLService service;
	
	public GraphQLResource(GraphQLService service) {
		this.service = service;
	}
	
	@GetMapping()
	@Timed
	public ExecutionResult getGames(@RequestParam("query") String query) throws URISyntaxException {
		return service.query(query);
	}
}
