package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static com.kompeteer.web.domain.graphql.types.GroupQLType.GroupType;
import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import com.kompeteer.web.domain.Player;
import com.kompeteer.web.service.GraphQLService;
import com.kompeteer.web.service.business.PlayerBS;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

public class PlayerQLType {
	public static final String PLAYER_TYPE = "player";
	
	public static GraphQLObjectType PlayerType = newObject()
	  .name(PLAYER_TYPE)
	  .field(newFieldDefinition()
	         .name("id")
	         .type(GraphQLLong)
	         .build())
	  .field(newFieldDefinition()
	         .name("firstName")
	         .type(GraphQLString)
	         .build())
	  .field(newFieldDefinition()
		         .name("lastName")
		         .type(GraphQLString)
		         .build())
	  .field(newFieldDefinition()
		         .name("groups")
		         .type(new GraphQLList(GroupType))
		         .build())
	  .field(newFieldDefinition()
		         .name("games")
		         .description("Games that the player has played")
		         .type(new GraphQLList(GameType))
		         .dataFetcher(env -> {
		        	 Player player = (Player) env.getSource();
		        	 return getPlayerBS(env).getGames(player);
		         })
		         .build())
	  .build();
	
	private static PlayerBS getPlayerBS(final DataFetchingEnvironment env) {
		return getGraphQLService(env).getPlayerBS();
	}
	
	private static GraphQLService getGraphQLService(final DataFetchingEnvironment env) {
		return (GraphQLService) env.getContext();
	}
}
