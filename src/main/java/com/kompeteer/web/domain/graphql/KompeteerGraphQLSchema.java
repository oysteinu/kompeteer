package com.kompeteer.web.domain.graphql;

import static com.kompeteer.web.domain.graphql.types.GroupQLType.GroupType;
import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PlayerType;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;
import static graphql.schema.GraphQLSchema.newSchema;

import com.kompeteer.web.service.GraphQLService;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;

import graphql.schema.DataFetchingEnvironment;
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
				.build();
	}
	
	private GraphQLObjectType createQueryType() {
		return newObject()
	        .name("query")
	        .field(playerQuery())
	        .field(groupQuery())
	        .build();
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
	
	public static PlayerBS getPlayerBS(final DataFetchingEnvironment env) {
		return context(env).getPlayerBS();
	}
	
	public static GroupBS getGroupBS(final DataFetchingEnvironment env) {
		return context(env).getGroupBS();
	}
	
	public static GraphQLService context(final DataFetchingEnvironment env) {
		return (GraphQLService) env.getSource();
	} 
}
