package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static com.kompeteer.web.domain.graphql.types.PlayerRatingQLType.PlayerRatingType;
import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PLAYER_TYPE;
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
import graphql.schema.GraphQLTypeReference;

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
			         .description("Games related to the group")
			         .type(new GraphQLList(GameType))
			         .dataFetcher(env -> {
			        	 Groups group = (Groups) env.getSource();			        	 
			        	 return getGroupBS(env).getGames(group);
			         })
			         .build())
		  .field(newFieldDefinition()
			         .name("players")
			         .description("Players of the group")
			         .type(new GraphQLList(new GraphQLTypeReference(PLAYER_TYPE)))
			         .dataFetcher(env -> {
			        	 Groups group = (Groups) env.getSource();			        	 
			        	 return getGroupBS(env).getPlayers(group);
			         })
			         .build())
		  .field(newFieldDefinition()
			         .name("ratings")
			         .description("Players and ratings of the group")
			         .type(new GraphQLList(PlayerRatingType))
			         .dataFetcher(env -> {
			        	 Groups group = (Groups) env.getSource();			        	 
			        	 return getGroupBS(env).getRatings(group);
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
