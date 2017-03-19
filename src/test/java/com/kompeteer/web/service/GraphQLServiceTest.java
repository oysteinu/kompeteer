package com.kompeteer.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;
import com.kompeteer.web.utils.AbstractEntityTestBase;

import graphql.ExecutionResult;

public class GraphQLServiceTest extends AbstractEntityTestBase {
	private GraphQLService service;

	@Before
	public void beforeEachTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		Groups group1 = group(1, "WC");
		
		Player player1 = player(1, "Magnus", "Carlsen");
		player1.addGroup(group1);
		
		Player player2 = player(2, "Jon Ludvig", "Hammer");
		player2.addGroup(group1);
		
		game(1, player1, player2, GameResult.PLAYER1);
		game(2, player1, player2, GameResult.DRAW);

		PlayerBS playerBS = new PlayerBS(mockPlayerRepository);
		GroupBS groupsBS = new GroupBS(mockGroupsRepository);
		
		service = new GraphQLService(playerBS, groupsBS);
	}

	@Test
	public void query_by_player_id() {
		ExecutionResult result = service.query("{ player(id: 1) { firstName lastName groups { name } }}");

		System.out.println(result.getData());
		
		assertThat(result.getErrors()).isEmpty();
		
		Map<String, Object> player = (Map<String, Object>) ((Map) result.getData()).get("player");
		
		assertThat(player).isNotNull();
		assertThat(player.get("firstName")).isEqualTo("Magnus");
		assertThat(player.get("lastName")).isEqualTo("Carlsen");
		
		assertThat(player.get("groups")).isNotNull();
		
		List groups = (List) player.get("groups");
		assertThat(groups.size()).isEqualTo(1);
	}
	
	@Test
	public void query_group_by_id() {
		ExecutionResult result = service.query("{ group(id: 1) { name games { player1 { id firstName } player2 { id firstName } result } }}");
		
		System.out.println(result.getErrors());
		
		System.out.println(result.getData());
	}	
}
