package com.kompeteer.web.domain.graphql.types;

import static com.kompeteer.web.domain.graphql.types.GameQLType.GameType;
import static com.kompeteer.web.domain.graphql.types.GroupQLType.GroupType;
import static com.kompeteer.web.domain.graphql.utils.GraphQLResources.getPlayerBS;
import static graphql.Scalars.GraphQLLong;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.Set;

import com.google.common.collect.Lists;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

public class PlayerQLType {
	public static final String PLAYER_TYPE = "player";
	
	static DataFetcher groupsDataFetcher = new DataFetcher() {
		@Override
		public Object get(DataFetchingEnvironment environment) {
			Player player = (Player) environment.getSource();
			
			Set<Groups> groups = player.getGroups();
			
			Long groupId = environment.getArgument("id");
			
			if (groupId != null) {
				for (Groups group : groups) {					
					if (groupId.longValue() == group.getId().longValue()) {
						return Lists.newArrayList(group);
					}
				}
				
				return null;
			}
			
			return groups;
		}       
    };
	
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
         .argument(newArgument()
                 .name("id")
                 .type(GraphQLLong)
                 .build())
         .dataFetcher(groupsDataFetcher)
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
}
