package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PLAYER_TYPE;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLEnumType.newEnum;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.domain.enumeration.GameStatus;

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
	
	public static GraphQLEnumType GameStatusEnum = newEnum()
		    .name("GameStatus")
		    .description("Result of a game")
		    .value(GameStatus.COMPLETE.name(), GameStatus.COMPLETE, "Game is complete")
		    .value(GameStatus.PENDING.name(), GameStatus.PENDING, "Game is not complete")
		    .build();
	
	public static GraphQLObjectType GameType = newObject()
		  .name("game")
		  .field(newFieldDefinition()
	         .name("id")
	         .description("ID of the game")
	         .type(GraphQLLong)
	         .build())
		  .field(newFieldDefinition()
	         .name("status")
	         .description("Status of the game (COMPLETE or PENDING)")
	         .type(GameStatusEnum)
	         .build())
		  .field(newFieldDefinition()
	         .name("player1")
	         .description("Player #1 of the game (white player in chess")
	         .type(new GraphQLTypeReference(PLAYER_TYPE))
	         .build())
		  .field(newFieldDefinition()
	         .name("player2")
	         .description("Player #2 of the game (black player in chess")
	         .type(new GraphQLTypeReference(PLAYER_TYPE))
	         .build())
		  .field(newFieldDefinition()
	         .name("result")
	         .description("Result of the game")
	         .type(GameResultEnum)
	         .build())
		  .field(newFieldDefinition()
	         .name("winner")
	         .description("Winner of the game")
	         .type(new GraphQLTypeReference(PLAYER_TYPE))
	         .dataFetcher(env -> {
	        	 Game game = (Game) env.getSource();
	        	 
	        	 if (GameResult.DRAW == game.getResult()) {
	        		 return null;
	        	 } else {
	        		 return GameResult.PLAYER1 == game.getResult() ? game.getPlayer1() : game.getPlayer2();
	        	 }
	         })
	         .build())
		  .build();
}
