package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.service.GraphQLService;
import com.kompeteer.web.service.business.GroupBS;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

public class GroupQLType {
	public static GraphQLObjectType GroupType = newObject()
		  .name("group")
		  .field(newFieldDefinition()
		         .name("id")
		         .type(GraphQLLong)
		         .build())
		  .field(newFieldDefinition()
		         .name("name")
		         .type(GraphQLString)
		         .build())
		  .field(newFieldDefinition()
			         .name("games")
			         .type(new GraphQLList(GameType))
			         .dataFetcher(env -> {
			        	 Groups group = (Groups) env.getSource();
			        	 
			        	 return getGroupBS(env).getGames(group);
			         })
			         .build())
		  .build();

	private static GroupBS getGroupBS(final DataFetchingEnvironment env) {
		return getGraphQLService(env).getGroupBS();
	}
	
	private static GraphQLService getGraphQLService(final DataFetchingEnvironment env) {
		return (GraphQLService) env.getContext();
	}
}
