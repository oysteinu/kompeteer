package com.kompeteer.web.domain.graphql.utils;

import com.kompeteer.web.service.GraphQLService;
import com.kompeteer.web.service.business.GameBS;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;

import graphql.schema.DataFetchingEnvironment;

public class GraphQLResources {
	public static PlayerBS getPlayerBS(final DataFetchingEnvironment env) {
		return context(env).getPlayerBS();
	}
	
	public static GroupBS getGroupBS(final DataFetchingEnvironment env) {
		return context(env).getGroupBS();
	}
	
	public static GameBS getGameBS(final DataFetchingEnvironment env) {
		return context(env).getGameBS();
	}
	
	public static GraphQLService context(final DataFetchingEnvironment env) {
		return (GraphQLService) env.getContext();
	} 
}
