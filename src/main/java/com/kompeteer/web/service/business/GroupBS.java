package com.kompeteer.web.service.business;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.domain.app.PlayerRating;
import com.kompeteer.web.domain.enumeration.GameResult;
import com.kompeteer.web.repository.GroupsRepository;

@Service
@Transactional
public class GroupBS {
	private double K = 32;

	public final GroupsRepository groupsRepository;
	
	public GroupBS(final GroupsRepository groupsRepository) {
		this.groupsRepository = groupsRepository;
	}
	
	public Groups getGroup(long groupId) {
		return groupsRepository.getOne(groupId);
	}
	
	public List<Player> getPlayers(Groups group) {
		Set<Player> players = group.getPlayers();
		
		return Lists.newArrayList(players);
	}
	
	public List<Game> getGames(long groupId) {
		Groups group = groupsRepository.getOne(groupId);
		
		return getGames(group);
	}
	
	public List<Game> getGames(Groups group) {
		Set<Player> players = group.getPlayers();

		Set<Game> games = new HashSet<>();

		for (Player player : players) {
			games.addAll(player.getPlayer1Games().stream().filter(g -> players.contains(g.getPlayer2()))
					.collect(Collectors.toSet()));
		}

		return Lists.newArrayList(games);
	}
	
	public List<PlayerRating> getRatings(long groupId) {
		List<Game> games = getGames(groupId);

		return getRatings(games);
	}
	
	public List<PlayerRating> getRatings(Groups group) {
		List<Game> games = getGames(group);

		return getRatings(games);
	}

	public List<PlayerRating> getRatings(List<Game> games) {
		Map<Long, PlayerRating> ratings = new HashMap<>();
		
		for (Game game : games) {
			Player player1 = game.getPlayer1();
			Player player2 = game.getPlayer2();
			
			PlayerRating playerRating1 = ratings.getOrDefault(player1.getId(), createInitial(player1));
			PlayerRating playerRating2 = ratings.getOrDefault(player2.getId(), createInitial(player2));
			
			int[] adjustments = adjust(playerRating1.getRating(), playerRating2.getRating(), game.getResult());
			
			playerRating1.adjust(adjustments[0]);
			playerRating2.adjust(adjustments[1]);
			
			ratings.put(player1.getId(), playerRating1);
			ratings.put(player2.getId(), playerRating2);
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
			s1 = GameResult.PLAYER1 == result ? 1 : 0;
			s2 = GameResult.PLAYER1 == result ? 0 : 1;
		}
		
		double adjustment1 = K * (s1 - e1);
		double adjustment2 = K * (s2 - e2);
		
		return new int[] {(int) adjustment1, (int) adjustment2};
	}

}
