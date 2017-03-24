package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PLAYER_TYPE;
import static com.kompeteer.web.domain.graphql.types.PlayerRatingQLType.PlayerRatingType;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getGroupBS;
import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.List;

import com.google.common.collect.Lists;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.app.PlayerRating;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

public class GroupQLType {
	static DataFetcher ratingsDataFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment env) {
			Groups group = (Groups) env.getSource();
			
       	  	List<PlayerRating> ratings = getGroupBS(env).getRatings(group);
			
			Long playerId = env.getArgument("playerId");
			
			if (playerId != null) {
				for (PlayerRating rating : ratings) {
					if (playerId.longValue() == rating.getPlayer().getId().longValue()) {
						return Lists.newArrayList(rating);
					}
				}
				
				return null;
			}
			
			return ratings;
		}       
    };
	
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
	         .argument(newArgument()
	                 .name("playerId")
	                 .description("ID of player")
	                 .type(GraphQLLong)
	                 .build())
	         .dataFetcher(ratingsDataFetcher)
	         .build())
		  .build();	
}
