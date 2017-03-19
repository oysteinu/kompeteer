package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.PlayerQLType.PLAYER_TYPE;
import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLTypeReference;

public class PlayerRatingQLType {	
	public static GraphQLObjectType PlayerRatingType = newObject()
	  .name("playerRating")
	  .field(newFieldDefinition()
	         .name("player")
	         .type(new GraphQLTypeReference(PLAYER_TYPE))
	         .build())
	  .field(newFieldDefinition()
	         .name("rating")
	         .type(GraphQLInt)
	         .build())
	  .build();
}
