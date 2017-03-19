package com.kompeteer.web.service.business;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
