package com.kompeteer.web.service.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.utils.AbstractEntityTestBase;

public class PlayerBSTest extends AbstractEntityTestBase {
	private PlayerBS bs;
	private Player p1;
	private Player p2;
	private Player p3;
	
	@Before
	public void beforeEachTest() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		p1 = player(1, "Magnus", "Carlsen");		
		p2 = player(2, "Jon Ludvig", "Hammer");
		p3 = player(3, "Vladimir", "Kramnik");
		
		bs = new PlayerBS(mockPlayerRepository);		
	}
	
	@Test
	public void getGames1() {
		List<Game> games = bs.getGames(p1);
		assertThat(games.size()).isZero();		
		
		game(1, p1, p2, GameResult.PLAYER1);
		game(2, p2, p1, GameResult.DRAW);
		game(3, p2, p3, GameResult.PLAYER2);
		game(3, p1, p2, GameResult.PLAYER1);
		
		games = bs.getGames(p2);
		assertThat(games.size()).isEqualTo(3);
	}
}
