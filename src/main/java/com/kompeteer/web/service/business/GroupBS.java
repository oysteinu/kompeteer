package com.kompeteer.web.service.business;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Groups;
import com.kompeteer.web.domain.Player;

@Component
public class GroupBS {
	public Set<Game> getGames(Groups group) {
		Set<Player> players = group.getPlayers();

		Set<Game> games = new HashSet<>();

		for (Player player : players) {
			games.addAll(player.getWhiteGames().stream().filter(g -> players.contains(g.getBlack()))
					.collect(Collectors.toSet()));
		}

		return games;
	}
}
