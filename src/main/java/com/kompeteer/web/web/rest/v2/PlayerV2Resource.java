package com.kompeteer.web.web.rest.v2;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.kompeteer.web.domain.Game;
import com.kompeteer.web.domain.app.PlayerRating;
import com.kompeteer.web.service.business.GroupBS;
import com.kompeteer.web.service.business.PlayerBS;
import com.kompeteer.web.service.dto.GameDTO;
import com.kompeteer.web.service.mapper.GameMapper;
import com.kompeteer.web.service.mapper.PlayerMapper;

@RestController
@RequestMapping("/api/v2")
@Transactional
public class PlayerV2Resource {
private final Logger log = LoggerFactory.getLogger(GroupV2Resource.class);
	
	private final PlayerBS playerBS;
	private final GameMapper gameMapper;
	private final PlayerMapper playerMapper;
	
	public PlayerV2Resource(PlayerBS playerBS, PlayerMapper playerMapper, GameMapper gameMapper) {
		this.playerBS = playerBS;
		this.playerMapper = playerMapper;
		this.gameMapper = gameMapper;
	}

	@GetMapping("/players/{playerId}/games")
	@Timed
	public List<GameDTO> getGames(@PathVariable("playerId") long playerId) throws URISyntaxException {

		List<Game> games = playerBS.getGames(playerId);
				
		return gameMapper.gamesToGameDTOs(games);
	}
}
