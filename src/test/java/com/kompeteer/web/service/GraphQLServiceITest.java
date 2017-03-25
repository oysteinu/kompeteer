package com.kompeteer.web.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.kompeteer.web.KompeteerApp;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.repository.GameRepository;
import com.kompeteer.web.repository.PlayerRepository;

import graphql.ExecutionResult;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KompeteerApp.class)
@Transactional
public class GraphQLServiceITest {
	@Autowired
    private GraphQLService service;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
    private PlayerRepository playerRepository;
	
	@Test
	public void create_game() {
		Player player1 = new Player();
		player1.setFirstName("Magnus");
		player1.setLastName("Carlsen");
		player1.setEmail("magnus@carlsen.no");
		
		player1 = playerRepository.save(player1);
		
		Player player2 = new Player();
		player2.setFirstName("Jon Ludvig");
		player2.setLastName("Hammer");
		player2.setEmail("jon@hammer.no");
		
		player2 = playerRepository.save(player2);
		
		Player player3 = new Player();
		player3.setFirstName("Vladimir");
		player3.setLastName("Kramnik");
		player3.setEmail("vladmimir@email.no");
		
		player3 = playerRepository.save(player3);
		
		String q = String.format("mutation { createGame (game: { player1Id: %s, player2Id: %s, status: COMPLETE, result: PLAYER1 }) { id } }", player1.getId(), player2.getId());
		Map<String, Object> gameData = getGameData(q);
		
		long gameId = (long) gameData.get("id");
		
		Game game = gameRepository.getOne(gameId);
		
		assertThat(game.getPlayer1().getId()).isEqualTo(player1.getId());
		assertThat(game.getPlayer2().getId()).isEqualTo(player2.getId());
		assertThat(game.getResult()).isEqualTo(GameResult.PLAYER1);
		
		// Change result of game
		q = String.format("mutation { updateGame (id: %s, game: { result: DRAW }) { id result } }", gameId);
		gameData = getGameData(q);
		
		gameId = (long) gameData.get("id");
		GameResult gameResult = GameResult.valueOf((String) gameData.get("result"));
		
		assertThat(gameId).isEqualTo(game.getId());
		assertThat(gameResult).isEqualTo(GameResult.DRAW);
		
		player1 = playerRepository.getOne(player1.getId());
		player2 = playerRepository.getOne(player2.getId());
		
		assertThat(player1.getPlayer1Games().size()).isEqualTo(1);		
		assertThat(player2.getPlayer2Games().size()).isEqualTo(1);
		
		// Change player2
		q = String.format("mutation { updateGame (id: %s, game: { player2Id: %s }) { id } }", game.getId(), player3.getId());
		getGameData(q);
		
		game = gameRepository.getOne(gameId);
		
		assertThat(game.getPlayer1().getId()).isEqualTo(player1.getId());
		assertThat(game.getPlayer2().getId()).isEqualTo(player3.getId());
		
		player1 = playerRepository.getOne(player1.getId());
		player2 = playerRepository.getOne(player2.getId());
		player3 = playerRepository.getOne(player3.getId());
		
		assertThat(player1.getPlayer1Games().size()).isEqualTo(1);		
		assertThat(player2.getPlayer2Games().size()).isEqualTo(0);
		assertThat(player3.getPlayer2Games().size()).isEqualTo(1);
		
		// Delete game
		q = String.format("mutation { deleteGame (id: %s) { id } }", game.getId());
		getGameData(q);
		
		game = gameRepository.findOne(gameId);
		
		assertThat(game).isNull();
		
		player1 = playerRepository.getOne(player1.getId());
		player2 = playerRepository.getOne(player2.getId());
		player3 = playerRepository.getOne(player3.getId());
		
		assertThat(player1.getPlayer1Games().size()).isEqualTo(0);		
		assertThat(player2.getPlayer2Games().size()).isEqualTo(0);
		assertThat(player3.getPlayer2Games().size()).isEqualTo(0);
	}
	
	@Test
	public void get_player_from_current_user() {
		String query = "query { me { player { firstName } }";
		
		ExecutionResult result = service.query(query);		
		Map data = (Map) result.getData();
		
		String s = "";
	}
	
	private Map<String, Object> getGameData(String query) {
		ExecutionResult result = service.query(query);		
		Map data = (Map) result.getData();
		
		if (data.get("createGame") != null) {
			return (Map) data.get("createGame");
		} else if (data.get("updateGame") != null) {
			return (Map) data.get("updateGame");
		} else {
			return (Map) data.get("deleteGame");
		}
	}
}
