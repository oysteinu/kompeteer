package com.kompeteer.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kompeteer.web.domain.graphql.KompeteerGraphQLSchema;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;

import graphql.ExecutionResult;
import graphql.GraphQL;

@Service
public class GraphQLService {
	private final GraphQL graphql;
	
	private final PlayerBS playerBS;
	private final GroupBS groupBS;

	@Autowired
	public GraphQLService(
			PlayerBS playerBS,
			GroupBS groupBS) throws Exception {
		
		graphql = new GraphQL(new KompeteerGraphQLSchema().getSchema());
		
		this.playerBS = playerBS;
		this.groupBS = groupBS;
	}

	public ExecutionResult query(String query) {
		ExecutionResult executionResult = graphql.execute(query, this);

		return executionResult;
	}
	
	public PlayerBS getPlayerBS() {
		return playerBS;
	}
	
	public GroupBS getGroupBS() {
		return groupBS;
	}
}
