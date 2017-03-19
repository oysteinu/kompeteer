package com.kompeteer.web.utils;

import static org.mockito.Mockito.when;

import org.mockito.Mock;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.repository.GroupsRepository;
import com.kompeteer.web.repository.PlayerRepository;

public class AbstractEntityTestBase {
	@Mock
	protected PlayerRepository mockPlayerRepository;
	
	@Mock
	protected GroupsRepository mockGroupsRepository;
	
	protected Groups group(long id, String name) {
		Groups group = new Groups();
		
		group.setId(id);
		group.setName(name);
		
		when(mockGroupsRepository.getOne(id)).thenReturn(group);
		
		return group;
	}

	protected Player player(long id, String firstName, String lastName) {
		Player player = new Player();

		player.setId(id);
		player.setFirstName(firstName);
		player.setLastName(lastName);
		
		when(mockPlayerRepository.getOne(id)).thenReturn(player);

		return player;
	}
	
	protected Game game(long id, Player player1, Player player2, GameResult result) {
		Game game = new Game();
		
		game.setId(id);
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.setResult(result);
		
		player1.addPlayer1Game(game);
		player2.addPlayer2Game(game);
		
		return game;
	}
}
