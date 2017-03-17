package com.kompeteer.web.service.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.app.PlayerRating;
import com.kompeteer.web.domain.enumeration.GameResult;

@Component
public class GroupBS {
	private double K = 32;

	public Set<Game> getGames(Groups group) {
		Set<Player> players = group.getPlayers();

		Set<Game> games = new HashSet<>();

		for (Player player : players) {
			games.addAll(player.getWhiteGames().stream().filter(g -> players.contains(g.getBlack()))
					.collect(Collectors.toSet()));
		}

		return games;
	}

	public List<PlayerRating> getRatings(Groups group) {
		Set<Game> games = getGames(group);

		Map<Long, PlayerRating> ratings = new HashMap<>();
		
		for (Game game : games) {
			Player white = game.getWhite();
			Player black = game.getBlack();
			
			PlayerRating playerRating1 = ratings.getOrDefault(white.getId(), createInitial(white));
			PlayerRating playerRating2 = ratings.getOrDefault(black.getId(), createInitial(black));
			
			int[] adjustments = adjust(playerRating1.getRating(), playerRating2.getRating(), game.getResult());
			
			playerRating1.adjust(adjustments[0]);
			playerRating2.adjust(adjustments[1]);
			
			ratings.put(white.getId(), playerRating1);
			ratings.put(black.getId(), playerRating2);
		}
		
		return ratings.values()
				.stream()
				.sorted((p1, p2) -> Integer.compare(p2.getRating(), p1.getRating()))
				.collect(Collectors.toList());
	}
	
	private PlayerRating createInitial(Player player) {
		return new PlayerRating(player, 1200);
	}

	private int[] adjust(int originalPlayer1, int originalPlayer2, GameResult result) {
		double r1 = Math.pow(10, originalPlayer1 / 400);
		double r2 = Math.pow(10, originalPlayer2 / 400);
		
		double e1 = r1 / (r1 + r2);
		double e2 = r2 / (r1 + r2);
		
		double s1 = 0.5;
		double s2 = 0.5;
		
		if (GameResult.DRAW != result) {
			s1 = GameResult.WHITE == result ? 1 : 0;
			s2 = GameResult.WHITE == result ? 0 : 1;
		}
		
		double adjustment1 = K * (s1 - e1);
		double adjustment2 = K * (s2 - e2);
		
		return new int[] {(int) adjustment1, (int) adjustment2};
	}

}
