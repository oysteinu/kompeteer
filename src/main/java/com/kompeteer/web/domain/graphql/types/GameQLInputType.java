package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameResultEnum;
import static com.kompeteer.web.domain.graphql.types.GameQLType.GameStatusEnum;
import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getGameBS;
import static graphql.Scalars.GraphQLLong;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLInputObjectField.newInputObjectField;
import static graphql.schema.GraphQLInputObjectType.newInputObject;

import java.util.Map;

import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.domain.enumeration.GameStatus;
import com.kompeteer.web.service.dto.GameDTO;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLFieldDefinition.Builder;
import graphql.schema.GraphQLInputObjectType;
import graphql.schema.GraphQLNonNull;

public class GameQLInputType {
	private static final String FIELD_PLAYER1_ID = "player1Id";
	private static final String FIELD_PLAYER2_ID = "player2Id";
	private static final String FIELD_STATUS = "status";
	private static final String FIELD_RESULT = "result";
	
	public static DataFetcher gameUpdateFetcher = env -> {
		Long gameId = (Long) env.getArgument("id");
		Map<String, Object> input = env.getArgument("game");
		
		Long player1Id = (Long) input.get(FIELD_PLAYER1_ID);
		Long player2Id = (Long) input.get(FIELD_PLAYER2_ID);
		GameStatus status = (GameStatus) input.get(FIELD_STATUS);
		GameResult result = (GameResult) input.get(FIELD_RESULT);
		
		GameDTO game = new GameDTO();
		
		game.setId(gameId);
		game.setPlayer1Id(player1Id);
		game.setPlayer2Id(player2Id);
		game.setStatus(status);
		game.setResult(result);
		
		return getGameBS(env).save(game);
	};
	
	public static DataFetcher gameDeleteFetcher = env -> {
		long gameId = env.getArgument("id");
		
		return getGameBS(env).delete(gameId);
	};	
		
	public static GraphQLInputObjectType GameInputType = newInputObject()
	        .name("gameInput")
	        .description("Input representation of a game")
	        .field(newInputObjectField()
	                .name(FIELD_PLAYER1_ID)
	                .description("ID of player 1")
	                .type(GraphQLLong))
	        .field(newInputObjectField()
	                .name(FIELD_PLAYER2_ID)
	                .description("ID of player 2")
	                .type(GraphQLLong))
	        .field(newInputObjectField()
	                .name(FIELD_STATUS)	                
	                .type(GameStatusEnum))
	        .field(newInputObjectField()
	                .name(FIELD_RESULT)	                
	                .type(GameResultEnum))
	        .build();
	
	public static Builder createGame = newFieldDefinition()
            .name("createGame")
            .type(GameType)
            .argument(newArgument()
                    .name("game")
                    .type(GameInputType)
                    .build())
            .dataFetcher(GameQLInputType.gameUpdateFetcher);
	
	public static Builder updateGame = newFieldDefinition()
            .name("updateGame")
            .type(GameType)
            .argument(newArgument()
                    .name("id")
                    .description("ID of game to update")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build())
            .argument(newArgument()
                    .name("game")
                    .type(GameInputType)
                    .build())
            .dataFetcher(GameQLInputType.gameUpdateFetcher);
	
	public static Builder deleteGame = newFieldDefinition()            
            .name("deleteGame")
			.type(GameType)
            .argument(newArgument()
                    .name("id")
                    .description("ID of the game to delete")
                    .type(new GraphQLNonNull(GraphQLLong))
                    .build())
            .dataFetcher(GameQLInputType.gameDeleteFetcher);
}
