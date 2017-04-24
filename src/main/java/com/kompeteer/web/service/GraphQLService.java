package com.kompeteer.web.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kompeteer.web.domain.graphql.KompeteerGraphQLSchema;
import com.kompeteer.web.service.business.GameBS;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;

import graphql.ExecutionResult;
import graphql.GraphQL;

@Service
public class GraphQLService {
	private final GraphQL graphql;
	
	private final PlayerBS playerBS;
	private final GroupBS groupBS;
	private final GameBS gameBS;
	private final UserService userService;
	
	@Autowired
	public GraphQLService(
			PlayerBS playerBS,
			GroupBS groupBS,
			GameBS gameBS,
			UserService userService) throws Exception {
		
		graphql = new GraphQL(new KompeteerGraphQLSchema().getSchema());
		
		this.playerBS = playerBS;
		this.groupBS = groupBS;
		this.gameBS = gameBS;
		this.userService = userService;
	}

	public ExecutionResult query(String query) {
		ExecutionResult executionResult = graphql.execute(query, this);

		return executionResult;
	}
	
	public ExecutionResult query(String query, String operationName, Map<String, Object> variables) {
		if (variables != null) {
			return graphql.execute(query, operationName, this, variables);
		} else {
			return graphql.execute(query, this);
		}
	}
	
	public PlayerBS getPlayerBS() {
		return playerBS;
	}
	
	public GroupBS getGroupBS() {
		return groupBS;
	}
	
	public GameBS getGameBS() {
		return gameBS;
	}
	
	public UserService getUserService() {
		return userService;
	}
}
