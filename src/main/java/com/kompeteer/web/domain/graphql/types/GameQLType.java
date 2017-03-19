package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PLAYER_TYPE;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLEnumType.newEnum;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import com.kompeteer.web.domain.enumeration.GameResult;

import graphql.schema.GraphQLEnumType;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

public class GameQLType {
	public static GraphQLEnumType GameResultEnum = newEnum()
	    .name("GameResult")
	    .description("Result of a game")
	    .value(GameResult.PLAYER1.name(), GameResult.PLAYER1, "Game won by player1")
	    .value(GameResult.PLAYER2.name(), GameResult.PLAYER2, "Game won by player2")
	    .value(GameResult.DRAW.name(), GameResult.DRAW, "Game was drawn")
	    .build();
	
	public static GraphQLObjectType GameType = newObject()
		  .name("game")
		  .field(newFieldDefinition()
		         .name("id")
		         .type(GraphQLLong)
		         .build())
		  .field(newFieldDefinition()
		         .name("player1")
		         .type(new GraphQLTypeReference(PLAYER_TYPE))
		         .build())
		  .field(newFieldDefinition()
		         .name("player2")
		         .type(new GraphQLTypeReference(PLAYER_TYPE))
		         .build())
		  .field(newFieldDefinition()
		         .name("result")
		         .type(GameResultEnum)
		         .build())
		  .build();
}
