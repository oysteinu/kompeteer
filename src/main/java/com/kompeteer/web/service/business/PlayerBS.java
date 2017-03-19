package com.kompeteer.web.service.business;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.Player;
import com.kompeteer.web.repository.PlayerRepository;

@Service
@Transactional
public class PlayerBS {
	public final PlayerRepository playerRepository;

	public PlayerBS(final PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Player getPlayer(long playerId) {
		return playerRepository.getOne(playerId);
	}

	public List<Game> getGames(Player player) {
		Set<Game> games = player.getPlayer1Games();
		games.addAll(player.getPlayer2Games());

		return Lists.newArrayList(games);
	}
}
