package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GroupQLType.GroupType;
import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

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
	  .build();
}
