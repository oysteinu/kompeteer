package com.kompeteer.web.domain.graphql;

import static com.kompeteer.web.domain.graphql.types.GameQLInputType.createGame;
import static com.kompeteer.web.domain.graphql.types.GameQLInputType.deleteGame;
import static com.kompeteer.web.domain.graphql.types.GameQLInputType.updateGame;
import static com.kompeteer.web.domain.graphql.types.GroupQLType.GroupType;
import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PlayerType;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getGroupBS;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getPlayerBS;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getUserService;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;

import com.kompeteer.web.domain.Player;
import com.kompeteer.web.security.SecurityUtils;

import graphql.schema.GraphQLFieldDefinition.Builder;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

public class KompeteerGraphQLSchema {
	private final GraphQLSchema schema;
	
	public KompeteerGraphQLSchema() {
		schema = createSchema();
	}
	
	public GraphQLSchema getSchema() {
		return schema;
	}
	
	public GraphQLSchema createSchema() {
		return newSchema()				
			.query(createQueryType())
			.mutation(createMutationType())
			.build();
	}
	
	private GraphQLObjectType createQueryType() {
		return newObject()
	        .name("query")
	        .field(playerQuery())
	        .field(groupQuery())
	        .field(meQuery())
	        .build();
	}
	
	private GraphQLObjectType createMutationType() {
		return newObject()
	        .name("mutation")
	        .field(createGame)
	        .field(updateGame)
	        .field(deleteGame)
	        .build();
	}
	
	private Builder meQuery() { 
		return newFieldDefinition()
            .name("me")
            .type(PlayerType)
            .dataFetcher(env -> {
            	Player player = new Player();
           	 
           	 	String name = getUserService(env).getUserWithAuthorities().getLogin();
           	 
           	 	player.setFirstName(name);
           	 
           	 	return player;
            });
	}
	
	private Builder playerQuery() { 
		return newFieldDefinition()
            .name("player")
            .type(PlayerType)
            .argument(newArgument()
                    .name("id")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build())
            .dataFetcher(env -> {
            	long playerId = env.getArgument("id");	                	
            	return getPlayerBS(env).getPlayer(playerId);
            });
	}
	
	private Builder groupQuery() {
		return newFieldDefinition()
            .name("group")
            .type(GroupType)
            .argument(newArgument()
                    .name("id")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build())
            .dataFetcher(env -> {
            	long groupId = env.getArgument("id");	                	
            	return getGroupBS(env).getGroup(groupId);
            });
	}
}
