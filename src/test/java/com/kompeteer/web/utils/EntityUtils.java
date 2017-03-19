package com.kompeteer.web.utils;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameResult;

public class EntityUtils {
	public static Groups group(long id, String name) {
		Groups group = new Groups();
		
		group.setId(id);
		group.setName(name);
		
		return group;
	}

	public static Player player(long id, String firstName, String lastName) {
		Player player = new Player();

		player.setId(id);
		player.setFirstName(firstName);
		player.setLastName(lastName);

		return player;
	}
	
	public static Game game(long id, Player player1, Player player2, GameResult result) {
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
